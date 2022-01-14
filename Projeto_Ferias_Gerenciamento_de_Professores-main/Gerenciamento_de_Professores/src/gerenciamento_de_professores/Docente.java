package gerenciamento_de_professores;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static gerenciamento_de_professores.Turma.listaTurma;

public class Docente extends Validacao {

    private int identificacao = (int) Math.ceil(Math.random() * (99999 - 10000) + 10000);
    private String nome;
    public Turma turmaAtual;
    private List<String> listaIDConteudosDatas = new ArrayList<>();
    private List<String> turmasAssuntosDatas = new ArrayList<>();
    private List<Integer> listaIdentificacao = new ArrayList<>();
    public static List<Docente> listaDocentes = new ArrayList<>();
    private GUI gui = new GUI();
    private String diasDeAula = "Toda a semana";


    public Docente() {
        listaDocentes.add(this);
        this.identificacao = setIdentificacao();
        this.nome = setNome();
        setTurmaAtual();
    }


    public int getIdentificacao() {
        return identificacao;
    }

    public int setIdentificacao() {
        if (listaIdentificacao.contains(this.identificacao)) {
            this.identificacao = (int) Math.ceil(Math.random() * (99999 - 10000) + 10000);
        } else {
            JOptionPane.showMessageDialog(null,
                    "O número de identificação é um número gerado aleatóriamente\ne diferente de outros " +
                            "demais. A ID do docente é " + this.identificacao + ".");
        }
        listaIdentificacao.add(this.identificacao);
        return this.identificacao;
    }

    public String getNome() {
        return nome;
    }

    public String setNome() {
        String mensagemPadrao = "Insira o nome do docente:";
        this.nome = entradaVazia(
                JOptionPane.showInputDialog(null, mensagemPadrao),
                mensagemPadrao);

        return this.nome;
    }

    public void setDiasDeAula(String diasDeAula) {
        this.diasDeAula = diasDeAula;
    }

    public Turma getTurmaAtual() {
        return turmaAtual;
    }

    public void setTurmaAtual() {
        String turmas = "";
        String conteudos = "";
        int respostaTurma = 0;
        int respostaConteudos = 0;
        int qntdDocentes = 0;


        for(int i = 0;i<listaTurma.size();i++) {
            turmas+="\n"+(i+1)+" - Turma "+listaTurma.get(i).getNomeDaTurma();
        }

        String mensagemTurma = "Qual turma você gostaria de ministrar?"+turmas;

        while(respostaTurma<=0||respostaTurma>listaTurma.size()) {
            respostaTurma = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,
                                    mensagemTurma),
                    mensagemTurma),
            mensagemTurma);
        }
        --respostaTurma;
        this.turmaAtual = listaTurma.get(respostaTurma);

        for(int i = 0;i< listaTurma.get(respostaTurma).assuntos.size();i++){
            conteudos+= "\n"+(i+1)+" - "+listaTurma.get(respostaTurma).assuntos.get(i)+
                    "\n"+listaTurma.get(respostaTurma).listaDatasIniciais.get(i)
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+" - " +
                    listaTurma.get(respostaTurma).listaDatasFinais.get(i)
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"\n";
        }
        String mensagemConteudo = "Qual dos conteúdos da turma "+listaTurma.get(respostaTurma).getNomeDaTurma()+
                " você gostaria de ministrar?\n"+conteudos;

        while(respostaConteudos<=0||respostaConteudos>listaTurma.get(respostaTurma).assuntos.size()) {
            respostaConteudos = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,mensagemConteudo),
                    mensagemConteudo),
            mensagemConteudo);
        }
        --respostaConteudos;

    for(Docente d: listaDocentes) {

        if(qntdDocentes==1) {
            listaDocentes.get(listaDocentes.indexOf(d)-1).setDiasDeAula("Segundas e Terças");
            listaDocentes.get(listaDocentes.indexOf(d)).diasDeAula = "Quartas, Quintas e Sextas";
        }

        int finalRespostaTurma = respostaTurma;
        int finalRespostaConteudos = respostaConteudos;
        if(d.listaIDConteudosDatas.stream().anyMatch(c->c.equals(
                "\nID - "+  listaTurma.get(finalRespostaTurma).getIdentificacao() +
                "\nAssunto - "+listaTurma.get(finalRespostaTurma).assuntos.get(finalRespostaConteudos)+"\n"
                        +listaTurma.get(finalRespostaTurma).assuntos.get(finalRespostaConteudos)+" Até "+
                        listaTurma.get(finalRespostaTurma).listaDatasFinais.get(finalRespostaConteudos)
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        ))){
            ++qntdDocentes;
        }
    }

        if(listaIDConteudosDatas.size()!=0&&listaIDConteudosDatas.get(
                listaDocentes.indexOf(this)).endsWith(
                listaTurma.get(respostaTurma).listaDatasFinais.get(respostaConteudos)
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        )){
            JOptionPane.showMessageDialog(null,
                    "Você já tem um assunto cadastrado para esse período.");

        } else if(qntdDocentes==2){
        JOptionPane.showMessageDialog(null,
                "Há docentes o suficiente no assunto: "+listaTurma.get(respostaTurma).assuntos.get(respostaConteudos)+
                " da Turma "+listaTurma.get(respostaTurma).getNomeDaTurma()+".");
        listaDocentes.remove(this);
    }else {
        listaIDConteudosDatas.add(
                "\nID - "+  listaTurma.get(respostaTurma).getIdentificacao() +
                "\nAssunto - "+ listaTurma.get(respostaTurma).assuntos.get(respostaConteudos) + "\n"
                        +listaTurma.get(respostaTurma).assuntos.get(respostaConteudos)+" Até "+
                        listaTurma.get(respostaTurma).listaDatasFinais.get(respostaConteudos)
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
        JOptionPane.showMessageDialog(null,"Seu conteúdo a ser ministrado é "+
                listaTurma.get(respostaTurma).assuntos.get(respostaConteudos));

        turmasAssuntosDatas.add("Turma - "+listaTurma.get(respostaTurma).getNomeDaTurma()+"\n"+
                                "Assunto - "+listaTurma.get(respostaTurma).assuntos.get(respostaConteudos)+"\n"+
                                listaTurma.get(respostaTurma).listaDatasIniciais.get(respostaConteudos)
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - "+
                                listaTurma.get(respostaTurma).listaDatasFinais.get(respostaConteudos)
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    );
        this.turmaAtual = listaTurma.get(respostaTurma);
        }
    }

    public void operacoes() {
        String mensagemPadrao = "O que você deseja fazer?" +
                "\n1 - Definir turma para um determinado docente" +
                "\n2 - Listar todos os docentes" +
                "\n3 - Listar um determinado docente" +
                "\n4 - voltar";
        int resposta = 0;
        Integer[] opcoes = {1,2,3,4};
        List<Integer> respostasPossiveis = new ArrayList<>();
        Collections.addAll(respostasPossiveis,opcoes);

        while (!respostasPossiveis.contains(resposta)) {
            resposta = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,mensagemPadrao),
                            mensagemPadrao),
                    mensagemPadrao);
        }

        switch(resposta) {

            case 1:
                definirTurma();
                operacoes();
                break;
            case 2:
                String docentes = "";
                for(Docente docente: listaDocentes) {
                    docentes += docente.listarDocente(docente);
                }
                JOptionPane.showMessageDialog(null,docentes);
                operacoes();
                break;
            case 3:
                listarDeterminado();
                operacoes();
                break;
            case 4:
                gui.iniciar();
                break;
        }
    }

    public String listarDocente(Docente docente) {

        String turmasAssuntosMinistrados = "";
        for(String assunto: docente.turmasAssuntosDatas){
                turmasAssuntosMinistrados+= assunto + "\n" + listaDocentes.get(listaDocentes.indexOf(docente)).diasDeAula;
            }

        return "\n------------------------------------------------------------------------------------" +
                "\nNome - "+docente.getNome() +
                "\nID - "+docente.getIdentificacao() +
                "\n"+turmasAssuntosMinistrados +
                "\n-----------------------------------------------------------------------------------";

    }

    public void definirTurma() {
        String docentes = "";
        int resposta = 0;
        for(int i = 0; i < listaDocentes.size();i++) {
        docentes += "\n"+(i+1)+") Nome - "+ listaDocentes.get(i).getNome() + "/ ID - " + listaDocentes.get(i).getIdentificacao();
        }
        String mensagemPadrao = "Para qual dos docentes você gostaria de definir uma turma? "+docentes;
        while(resposta<=0||resposta>listaDocentes.size()) {
            resposta = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null, mensagemPadrao),
                    mensagemPadrao),
                mensagemPadrao);
        }
        --resposta;

        listaDocentes.get(resposta).setTurmaAtual();
    }

    public void listarDeterminado() {
        int resposta = 0;
        String docentes = "";
        for(int i = 0; i < listaDocentes.size();i++) {
            docentes+= "\n"+(i+1)+") Nome - "+listaDocentes.get(i).getNome()+"/ ID - "+
                    listaDocentes.get(i).getIdentificacao();
        }
        String mensagemPadrao = "Qual dos docentes você gostaria de listar ?" + docentes;
        while(resposta<=0||resposta>listaDocentes.size()) {
            resposta = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,mensagemPadrao),
                    mensagemPadrao),
            mensagemPadrao);
        }
        --resposta;

        JOptionPane.showMessageDialog(null,listarDocente(listaDocentes.get(resposta)));
    }
}
