package gerenciamento_de_usuarios;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Funcionario extends Validacao {

    private String cargo;
    private String nome;
    private String departamento;
    private String situacao;
    public GUI gui = new GUI();
    private long RG;
    public static List<Funcionario> listaFuncionarios = new ArrayList<>();
    public static List<Long> listaRG = new ArrayList<>();
    public static List<String> listaNomes = new ArrayList<>();


    public Funcionario() {
        this.situacao = setSituacao("Empregado");
        this.RG = setRG();
        this.nome = setNome();
        this.cargo = setCargo();
        this.departamento = setDepartamento();
        listaFuncionarios.add(this);
        operacoes();
    }

    public String getNome() {
        return nome;
    }

     String setNome() {
        this.nome = entradaVazia(JOptionPane.showInputDialog("Insira o nome: "),"Digite novamente o nome:");
        listaNomes.add(nome);
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

     String setDepartamento() {
        if(this instanceof GerenteGeral) {
           return "Todos";
        }

        List<String> listaDeptos = new ArrayList<>();
        for (Departamentos dep : Departamentos.values()) {
            listaDeptos.add(dep.getNomeDepartamento().toLowerCase());
        }

        String resposta = entradaVazia(
                JOptionPane.showInputDialog(null, "Insira seu departamento: " + getTodosDepartamentos())
        ,"Insira o nome do departamento"+getTodosDepartamentos());
        while (!listaDeptos.contains(resposta.toLowerCase())) {
            resposta = entradaVazia(
                    JOptionPane.showInputDialog(null,
                            "Departamento não encontrado. Temos os seguintes departamentos disponíveis: " +
                                    getTodosDepartamentos()),
                    "Digite novamente o nome do departamento "+getTodosDepartamentos()

            );
        }
        String finalResposta = resposta;
        if(listaFuncionarios.stream().
                anyMatch(f->f.getDepartamento().equals(finalResposta)
                &&f.getSituacao().equals("Empregado")
                &&f.getClass().getSimpleName().equals("GerenteDepartamento")
                &&f!=this)) {
            Funcionario gerenteDepto = this;
            for(Funcionario funcionario: listaFuncionarios) {
                if(funcionario instanceof GerenteDepartamento) {
                    gerenteDepto=funcionario;
                }
            }
            JOptionPane.showMessageDialog(null,
                    "Só pode haver um gerente por departamento.",
                    gerenteDepto.getNome()+" é o gerente do departamento de "+gerenteDepto.getDepartamento(),
                    JOptionPane.INFORMATION_MESSAGE);
            setDepartamento();
        }
        return this.departamento = resposta.toLowerCase();
    }

    public long getRG() {
        return RG;
    }

    public long setRG() {

        this.RG = validaRG(
                entradaVazia(
                        JOptionPane.showInputDialog(null, "Insira o RG:"),
                "Digite novamente o RG:")
        );
        while (listaRG.contains(RG)) {
            this.RG = validaRG(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,
                                    "Este RG já consta em nosso banco de dados. Insira outro: "),
                            "Insira o RG:")
                );
        }
        listaRG.add(RG);
        return RG;
    }

    public String getSituacao() {
        return situacao;
    }

     String setSituacao(String situacao) {
        this.situacao = situacao;
        return situacao;
    }

    public String getCargo() {
        return cargo;
    }

     String setCargo() {
        String cargo = "";
        switch(this.getClass().getSimpleName()) {
            case "Colaborador":
                cargo = "Colaborador";
                break;
            case "LiderTecnico":
                cargo = "Líder Técnico";
                break;
            case "GerenteDepartamento":
                cargo = "Gerente de Departamento";
                break;
            case "GerenteGeral":
                cargo = "Gerente Geral";
                break;
        }
        return cargo;
    }

    public String getTodosDepartamentos() {
        String todosDeptos = "\n";
        for (Departamentos dep : Departamentos.values()) {
            todosDeptos += "- " + dep.getNomeDepartamento() + "\n";
        }
        return todosDeptos;
    }

    public String detalhesFuncionario(Funcionario funcionario) {

         String label = "----------------------" + funcionario.getNome() + "----------------------\n" +
                "\nFUNCIONÁRIO - " + funcionario.getNome() +
                "\nDEPARTAMENTO - " + funcionario.getDepartamento() +
                "\nCARGO - " + funcionario.getCargo() +
                "\nSITUAÇÃO - " + funcionario.getSituacao() +
                "\nRG - " + funcionario.getRG() + "\n";
         return label;
    }

    public void operacoes() {
        int resposta = 0;
        Integer[] respostasPossiveis = {1, 2, 3};
        String opcoes = "\n1 - Verificar dados\n2 - Atualizar seus dados\n3 - Voltar";
        while (!Arrays.asList(respostasPossiveis).contains(resposta)) {
            resposta = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,
                                    "Bem-vindo(a) " + getNome() + ", o que deseja fazer?" +
                                            opcoes,
                                    "Gerenciamento de Funcionário", JOptionPane.QUESTION_MESSAGE),
                    "Insira uma das opcoes"+opcoes),
            "Digite um dos números para executar a operação desejada:"+opcoes);
        }
        switch (resposta) {
            case 1:
                JOptionPane.showMessageDialog(null,detalhesFuncionario(this));
                operacoes();
                break;
            case 2:
                listaRG.remove(this.RG);
                setRG();
                setNome();
                setDepartamento();
                operacoes();
                break;
            case 3:
                gui.iniciar();
                operacoes();
                break;
        }
    }
}