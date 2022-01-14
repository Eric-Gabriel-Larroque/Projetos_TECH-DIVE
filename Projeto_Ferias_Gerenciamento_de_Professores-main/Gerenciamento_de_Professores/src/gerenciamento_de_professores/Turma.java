package gerenciamento_de_professores;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static gerenciamento_de_professores.Docente.listaDocentes;

public class Turma extends Validacao{

    private int identificacao = (int) Math.ceil(Math.random()*(99999-10000)+10000);
    private final List<Integer> listaIdentificacao = new ArrayList<>();
    private String nomeDaTurma;
    private int qntdDeAlunos;
    private List<Assuntos> listaAssuntos = Arrays.asList(Assuntos.values());
    public List<String> assuntos = new ArrayList<>();
    private String inicioDasAulas;
    private String fimDaTurma;
    public List<LocalDateTime> listaDatasFinais = new ArrayList<>();
    public List<LocalDateTime> listaDatasIniciais = new ArrayList<>();
    public static List<Turma> listaTurma = new ArrayList<>();
    private GUI gui = new GUI();

    public Turma() {
        this.identificacao = setIdentificacao();
        this.nomeDaTurma = setNomeDaTurma();
        this.qntdDeAlunos = setQntdDeAlunos();
        setAssuntos();
        setInicioDasAulas();
        listaTurma.add(this);
    }


    public int getIdentificacao() {
        return identificacao;
    }

    public int setIdentificacao() {
        if(listaIdentificacao.contains(this.identificacao)) {
            this.identificacao = (int) Math.ceil(Math.random()*(99999-10000)+10000);
        } else {
            JOptionPane.showMessageDialog(null,
                    "O número de identificação é um número gerado aleatóriamente\n" +
                            "e diferente de outros " +
                            "demais. A ID da Turma é "+this.identificacao+".");
        }
        listaIdentificacao.add(this.identificacao);
        return this.identificacao;
    }

     public String getNomeDaTurma() {
        return nomeDaTurma;
    }

    public String setNomeDaTurma() {

        this.nomeDaTurma =  entradaVazia(
                JOptionPane.showInputDialog(null,"Insira o nome da Turma: "),
                "insira novamente o nome da turma:");
        return nomeDaTurma;
    }

    public int getQntdDeAlunos() {
        return qntdDeAlunos;
    }

    public int setQntdDeAlunos() {
        String msgPadrao = "Digite novamente o número de alunos da turma "+this.getNomeDaTurma()+":";

        while(qntdDeAlunos<=0||qntdDeAlunos>40) {
            this.qntdDeAlunos = somenteNumeros(
                    entradaVazia(JOptionPane.showInputDialog(null,
                                    "Insira a quantidade de alunos na turma "+this.getNomeDaTurma()+
                                            " (máximo 40 alunos):"),
                            msgPadrao),
                    msgPadrao);
            if (qntdDeAlunos <= 0 || qntdDeAlunos > 40) {

                qntdDeAlunos = somenteNumeros(
                        entradaVazia(
                                JOptionPane.showInputDialog(null,
                                        "Número de alunos com valor acima do limite, valor nulo " +
                                                "ou negativo. Insira um número válido: "),
                                msgPadrao),
                        msgPadrao);
            }
        }
        return this.qntdDeAlunos;
    }

    public String getAssuntos(int i) {
        return assuntos.get(i);
    }

        public void setAssuntos() {
        int resposta = 0;
        String assuntosListados = "";

        for(int i =0;i<listaAssuntos.size();i++) {
            assuntosListados += "\n" + (i + 1) + " - " + listaAssuntos.get(i).getNomeAssunto();
        }
            String mensagemPadrao = "Escolha um dos assuntos abaixo para a turma "+this.getNomeDaTurma()+":\n"+assuntosListados;
            while(resposta<=0||resposta>listaAssuntos.size()) {
                resposta = somenteNumeros(entradaVazia(
                 JOptionPane.showInputDialog(null,mensagemPadrao),
                                mensagemPadrao),
                        mensagemPadrao);
            }
        --resposta;

            assuntos.add(listaAssuntos.get(resposta).getNomeAssunto());
    }

    public String getInicioDasAulas() {
        return inicioDasAulas;
    }

    public void setInicioDasAulas() {

        String[] diasDaSemana = {"Segunda","Terça","Quarta","Quinta","Sexta","Sábado","Domingo"};
        int diaDaSemanadaAtual = LocalDateTime.now().getDayOfWeek().getValue();
        LocalDateTime proximaSegunda = LocalDateTime.now();
        LocalDateTime fimDasAulas = LocalDateTime.now();

        if(listaDatasFinais.size()!=0){
          fimDasAulas = listaDatasFinais.get(listaDatasFinais.size()-1).plusDays(7);
          proximaSegunda = listaDatasFinais.get(listaDatasFinais.size()-1).plusDays(2);
          DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          LocalDateTime fimDaTurmaFormatado = LocalDate.parse(fimDaTurma,formatador).atTime(LocalTime.from(LocalDateTime.now()));

          //Valida cadastro de assunto para além da data final da turma.
          if(fimDasAulas.compareTo(fimDaTurmaFormatado) > 0){
                JOptionPane.showMessageDialog(null, "Vocẽ não pode mais " +
                        "cadastrar assuntos para essa turma,\n pois todas as datas já estão preenchidas.");
                assuntos.remove(listaTurma.indexOf(this));
                operacoes();
          }


        } else {
             if(diasDaSemana[diaDaSemanadaAtual-1].equals("Segunda")) {
                proximaSegunda = LocalDateTime.now();
                this.inicioDasAulas = proximaSegunda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                fimDasAulas = LocalDateTime.now().plusDays(4);
                this.fimDaTurma = LocalDateTime.now().plusYears(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } else {
                for(int i = 1; !diasDaSemana[diaDaSemanadaAtual-1].equals("Segunda"); i++) {
                    diaDaSemanadaAtual = LocalDateTime.now().plusDays(i).getDayOfWeek().getValue();

                    if(diasDaSemana[diaDaSemanadaAtual-1].equals("Segunda")) {
                        proximaSegunda = LocalDateTime.now().plusDays(i);
                        this.inicioDasAulas = proximaSegunda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        fimDasAulas = LocalDateTime.now().plusDays(i+4);
                        this.fimDaTurma = LocalDateTime.now().plusDays(i).plusYears(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                }

            }
        }
        JOptionPane.showMessageDialog(null,
                "O início das aulas é no dia "+proximaSegunda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        +"\nPortanto, a turma se encerrará no dia "+fimDaTurma);

        listaDatasFinais.add(fimDasAulas);
        listaDatasIniciais.add(proximaSegunda);
    }

    public String listarTurma(Turma turma) {

        int qtdDocentes = 0;
        String todosAssuntosDatas = "";
        String docenteAtual = "";

        for(Docente docente: listaDocentes) {

            docenteAtual = qtdDocentes==1 ? docenteAtual.replaceAll(" ",", ") :
                                            docenteAtual;

            if(docente.turmaAtual.equals(this)) {
                ++qtdDocentes;
                docenteAtual+=docente.getNome()+" ";
            }
        }

        for(int i = 0;i<assuntos.size();i++) {
            todosAssuntosDatas+="\nAssunto - "+assuntos.get(i)+"\nDe - "
                    +listaDatasIniciais.get(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+
                    "  Até - "+listaDatasFinais.get(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"\n";
        }

          return  "-------------------------------------------------------------------------------------------\n"+
                  "Nome da Turma - "+turma.getNomeDaTurma() +
                  "\nID - "+turma.identificacao+
                  "\nQuantidade de Alunos - "+turma.getQntdDeAlunos() +
                  todosAssuntosDatas +
                  "Fim da Turma - " + turma.fimDaTurma +
                  "\nDocente(s) - " + docenteAtual +
                  "\n------------------------------------------------------------------------------------------\n";
    }

    public void operacoes() {
        String mensagemPadrao = "O que você deseja fazer?" +
                "\n1 - Listar todas as turmas" +
                "\n2 - Cadastrar um novo assunto" +
                "\n3 - voltar";
        int resposta = 0;
        Integer[] opcoes = {1,2,3};
        List<Integer> respostasPossiveis = new ArrayList<>();
        Collections.addAll(respostasPossiveis,opcoes);

        while (!respostasPossiveis.contains(resposta)) {
            resposta = somenteNumeros(
                    entradaVazia(
                            JOptionPane.showInputDialog(null,mensagemPadrao),
                            mensagemPadrao),
                    mensagemPadrao);
        }

        switch (resposta) {

            case 1:
                String todasAsTurmas = "";
                for(Turma turma: listaTurma) {
                    todasAsTurmas += turma.listarTurma(turma);
                }
                JOptionPane.showMessageDialog(null,todasAsTurmas);
                operacoes();
                break;
            case 2:
                setAssuntos();
                setInicioDasAulas();
                operacoes();
                break;
            case 3:
                new GUI().iniciar();
                break;
        }
    }
}