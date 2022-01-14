package gerenciamento_de_usuarios;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GerenteGeral extends GerenteDepartamento implements Gerente{


    @Override
    public int listarNomeRG(String label) {
        String funcionarios = "";
        int resposta = 0;
        for(int i = 0;i < listaFuncionarios.size();i++) {
            funcionarios += "\n"+(i+1)+") - "+listaFuncionarios.get(i).getNome()+" /RG - "+listaRG.get(i);
        }
        while(resposta>listaFuncionarios.size()||resposta<=0) {
            resposta = somenteNumeros(entradaVazia(
                    JOptionPane.showInputDialog(null,
                            label+funcionarios),
                        label+funcionarios),
                    label+funcionarios);
        }
        return resposta;
    }

    @Override
    public void atualizarDetalhes() {
        int indice = listarNomeRG("Qual funcionário você gostaria que fosse atualizado?\n")-1;
        listaRG.remove(listaFuncionarios.get(indice).getRG());
        listaFuncionarios.get(indice).setRG();
        listaFuncionarios.get(indice).setNome();
        listaFuncionarios.get(indice).setDepartamento();
        operacoes();
    }

    @Override
    public void demitirFuncionario() {
        String funcionarios = "";
        List<Funcionario> funcionariosDemissiveis = new ArrayList<>();
        int resposta = 0;
        for(Funcionario f: listaFuncionarios) {
            if(f!=this&&f.getSituacao()!="Demitido"&&!f.getClass().getSimpleName().equals("GerenteGeral")) {
                funcionariosDemissiveis.add(f);
            }
        }
        for(int i = 0;i<funcionariosDemissiveis.size();i++) {
            funcionarios += "\n" + (i + 1) + ") - " +
                    funcionariosDemissiveis.get(i).getNome() +
                    " /RG - " + listaRG.get(i);
        }
        String mensagemPadrao = "Qual dos funcionários você gostaria de demitir?\n"+funcionarios;
        if(funcionariosDemissiveis.size()==0) {
            JOptionPane.showMessageDialog(null,"Por enquanto não há ninguém do seu departamento" +
                    " além de você para ser demitido.");
        } else{
            while(resposta<=0||resposta>funcionariosDemissiveis.size()) {
                resposta = somenteNumeros(entradaVazia(
                        JOptionPane.showInputDialog(null, mensagemPadrao),
                                mensagemPadrao),
                        mensagemPadrao);
            }
            Funcionario funcionario = listaFuncionarios.get((resposta-1));
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
        Integer[] respostasPossiveis = {1, 2, 3};
        String opcoes = "Em qual cargo você gostaria de adicionar um funcionário?\n" +
                        "1 - Colaborador\n2 - Líder Técnico\n3 - Gerente de Departamento";
        while (!Arrays.asList(respostasPossiveis).contains(resposta)) {
            resposta = somenteNumeros(entradaVazia(
                    JOptionPane.showInputDialog(null, opcoes),
                    opcoes),
            opcoes);
        }
        switch (resposta) {
            case 1:
                new Colaborador();
                break;
            case 2:
                new LiderTecnico();
                break;
            case 3:
                new GerenteDepartamento();
                break;
        }
        operacoes();
    }
}
