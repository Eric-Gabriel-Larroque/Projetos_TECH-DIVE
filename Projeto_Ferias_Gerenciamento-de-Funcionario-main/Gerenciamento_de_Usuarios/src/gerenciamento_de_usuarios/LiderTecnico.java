package gerenciamento_de_usuarios;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiderTecnico extends Funcionario {

    
    public int listarNomeRG(String label) {
        String funcionarios = "";
        int resposta = 0;
        for(int i = 0;i < listaFuncionarios.size();i++) {
            funcionarios += "\n"+(i+1)+") - "+listaFuncionarios.get(i).getNome()+" /RG - "+listaRG.get(i);
        }
        while(resposta>listaFuncionarios.size()||resposta<=0) {
            resposta = somenteNumeros(entradaVazia(
                    JOptionPane.showInputDialog(null,
                            label+funcionarios),label+funcionarios
            ),label+funcionarios);
        }
        return resposta;
    }


    public void detalhesFuncionario() {
        JOptionPane.showMessageDialog(null,detalhesFuncionario(listaFuncionarios.get(listarNomeRG(
                "Qual dos seguintes funcionários você gostaria de visualizar os detalhes?\n")-1)));
    }

    public void atualizarDetalhes() {
        List<Funcionario> funcionariosAtualizaveis = new ArrayList<>();
        String funcionarios = "";
        int indice = 0;

        for(Funcionario f: listaFuncionarios) {
            if(!(f instanceof GerenteDepartamento || f instanceof GerenteGeral) ) {
                funcionariosAtualizaveis.add(f);
            }
        }
        for(int i = 0;i < funcionariosAtualizaveis.size();i++) {
            funcionarios += "\n"+(i+1)+") - "+funcionariosAtualizaveis.get(i).getNome()+" /RG - "+listaRG.get(i);
        }
        String mensagemPadrao = "Qual o funcionário você gostaria de atualizar os dados?\n"+funcionarios;
        while(indice>funcionariosAtualizaveis.size()||indice<=0) {
            indice = somenteNumeros(entradaVazia(
                    JOptionPane.showInputDialog(null,mensagemPadrao),
                    mensagemPadrao),mensagemPadrao);
        }
        --indice;
        listaRG.remove(listaFuncionarios.get(indice).getRG());
        listaFuncionarios.get(indice).setRG();
        listaFuncionarios.get(indice).setNome();
        listaFuncionarios.get(indice).setDepartamento();
        operacoes();
    }


    public void listarTodosFuncionarios() {
            String funcionarios = "";
            for(Funcionario f: listaFuncionarios) {
                funcionarios+=f.detalhesFuncionario(f);
            }
            JOptionPane.showMessageDialog(null,funcionarios,"Relação de todos os funcionários",JOptionPane.INFORMATION_MESSAGE);
            operacoes();
        }

        public void listarPorSituacao(String opcao){
            String lista = "";
            for(Funcionario funcionario: listaFuncionarios) {
                if(funcionario.getSituacao()==opcao) {
                    lista+=detalhesFuncionario(funcionario);
                }
            }
            if(lista.isBlank()||lista.isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "Não há funcionários disponíveis na situação "+opcao);
            } else {
                JOptionPane.showMessageDialog(null, lista,
                        "Relação de funcionários "+opcao+"s",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            operacoes();
        }

    @Override
    public void operacoes() {
        int resposta = 0;
        Integer[] respostasPossiveis = {1,2,3,4,5,6};
        String opcoes =    "\n1 - Verificar dados de determinado funcionário" +
                           "\n2 - Listar todos os funcionários" +
                           "\n3 - Listar funcionários demitidos" +
                           "\n4 - Listar funcionáros empregados" +
                           "\n5 - Atualizar dados de um determinado funcionario" +
                           "\n6 - Voltar";
        while (!Arrays.asList(respostasPossiveis).contains(resposta)) {
            resposta = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,
                                    "Bem-vindo(a) " + getNome() + ", o que deseja fazer?" +
                                            opcoes,
                                    "Gerenciamento de Funcionário", JOptionPane.QUESTION_MESSAGE),
                    "Digite uma das opções abaixo:"+opcoes),
            "Digite um dos números para executar a operação desejada:"+opcoes);
        }
        switch (resposta) {
            case 1:
                detalhesFuncionario();
                operacoes();
                break;
            case 2:
                listarTodosFuncionarios();
                break;
            case 3:
                listarPorSituacao("Demitido");
                break;
            case 4:
                listarPorSituacao("Empregado");
                break;
            case 5:
                atualizarDetalhes();
                break;
            case 6:
                gui.iniciar();
                operacoes();
                break;
        }
    }

}
