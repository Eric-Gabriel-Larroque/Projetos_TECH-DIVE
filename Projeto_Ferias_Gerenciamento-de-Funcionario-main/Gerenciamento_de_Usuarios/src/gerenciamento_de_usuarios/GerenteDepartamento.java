package gerenciamento_de_usuarios;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GerenteDepartamento extends LiderTecnico implements Gerente {

    @Override
    public void atualizarDetalhes() {
        List<Funcionario> funcionariosAtualizaveis = new ArrayList<>();
        String funcionarios = "";
        int indice = 0;

        for(Funcionario f: listaFuncionarios) {
            if(!(f instanceof GerenteGeral)) {
                funcionariosAtualizaveis.add(f);
            }
        }
        for(int i = 0;i < funcionariosAtualizaveis.size();i++) {
            funcionarios += "\n"+(i+1)+") - "+funcionariosAtualizaveis.get(i).getNome()+" /RG - "+listaRG.get(i);
        }

        String mensagemPadrao = "Qual funcionário você gostaria de atualizar os dados?\n"+funcionarios;
        while(indice>funcionariosAtualizaveis.size()||indice<=0) {
            indice = somenteNumeros(entradaVazia(
                    JOptionPane.showInputDialog(null,mensagemPadrao),
                    mensagemPadrao),
            mensagemPadrao);
        }
        --indice;
        listaRG.remove(listaFuncionarios.get(indice).getRG());
        listaFuncionarios.get(indice).setRG();
        listaFuncionarios.get(indice).setNome();
        listaFuncionarios.get(indice).setDepartamento();
        operacoes();
    }



    @Override
    public int listarNomeRG(String label) {
        String funcionarios = "";
        int resposta = 0;
        List<Funcionario> funcionariosDepto = new ArrayList<>();
        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.getDepartamento().equals(this.getDepartamento())) {
                funcionariosDepto.add(funcionario);
            }
        }
            for (int i = 0; i < funcionariosDepto.size(); i++) {
                funcionarios += "\n" + (i + 1) + ") - " + funcionariosDepto.get(i).getNome() + " /RG - " + listaRG.get(i);
            }
            while (resposta > listaFuncionarios.size() || resposta <= 0) {
                resposta = somenteNumeros(entradaVazia(
                        JOptionPane.showInputDialog(null,
                                label + funcionarios),
                        label+funcionarios),
                label+funcionarios
                );
            }
        if(funcionariosDepto.size()==0) {
            return  ++resposta;
        }else {
            return resposta;
        }
    }

    @Override
    public void demitirFuncionario() {
        String funcionarios = "";
        List<Funcionario> funcionarioDeptos = new ArrayList<>();
        int resposta = 0;
        for(Funcionario f: listaFuncionarios) {
            if(f.getDepartamento().equals(this.getDepartamento())
                    &&f!=this&&f.getSituacao()!="Demitido") {
                    funcionarioDeptos.add(f);
            }
        }
        for(int i = 0;i<funcionarioDeptos.size();i++) {
            funcionarios += "\n" + (i + 1) + ") - " +
                            funcionarioDeptos.get(i).getNome() +
                            " /RG - " + listaRG.get(i);
        }
        String mensagemPadrao = "Qual dos funcionários você gostaria de demitir?\n"+funcionarios;
        if(funcionarioDeptos.size()==0) {
            JOptionPane.showMessageDialog(null,"Por enquanto não há ninguém do seu departamento" +
                    " além de você para ser demitido.");
        } else{
            while(resposta<=0||resposta>funcionarioDeptos.size()) {
                resposta = somenteNumeros(entradaVazia(
                        JOptionPane.showInputDialog(null, mensagemPadrao),
                                mensagemPadrao),
                        mensagemPadrao);
            }
            Funcionario funcionario = listaFuncionarios.get(resposta-1);
            funcionario.setSituacao("Demitido");
            JOptionPane.showMessageDialog(null,
                    "Funcionário "+funcionario.getNome()+" demitido com sucesso.",
                    "Demissão de"+funcionario.getNome(),JOptionPane.INFORMATION_MESSAGE);
        }
        operacoes();
    }

    @Override
    public void adicionarFuncionario() {
        int resposta = 0;
        Integer[] respostasPossiveis = {1, 2};
        String opcoes = "Em qual cargo você gostaria de adicionar um funcionário?\n" +
                        "1 - Colaborador\n2 - Líder Técnico";
        while (!Arrays.asList(respostasPossiveis).contains(resposta)) {
            resposta = somenteNumeros(entradaVazia(
                    JOptionPane.showInputDialog(null,
                            opcoes),opcoes
            ),opcoes);
        }
        switch (resposta) {
            case 1:
                new Colaborador();
                break;
            case 2:
                new LiderTecnico();
        }
    }
    @Override
    public void operacoes() {
        int resposta = 0;
        Integer[] respostasPossiveis = {1,2,3,4,5,6,7,8};
        String opcoes =  "\n1 - Verificar dados de determinado funcionário" +
                         "\n2 - Listar todos os funcionários" +
                         "\n3 - Listar funcionários demitidos" +
                         "\n4 - Listar funcionáros empregados" +
                         "\n5 - Atualizar dados de um determinado funcionario" +
                         "\n6 - Demitir um funcionário" +
                         "\n7 - Adicionar um funcionário" +
                         "\n8 - Voltar";

        while (!Arrays.asList(respostasPossiveis).contains(resposta)) {
            resposta = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,
                                    "Bem-vindo(a) " + getNome() + ", o que deseja fazer?" +
                                            opcoes,
                                    "Gerenciamento de Funcionário", JOptionPane.QUESTION_MESSAGE),
                            "Digite uma das opções abaixo:"+opcoes),
                    "Digite um dos números para executar a operação desejada:"+opcoes
            );
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
                demitirFuncionario();
                break;
            case 7:
                adicionarFuncionario();
                break;
            case 8:
                gui.iniciar();
                operacoes();
                break;
        }
    }
}
