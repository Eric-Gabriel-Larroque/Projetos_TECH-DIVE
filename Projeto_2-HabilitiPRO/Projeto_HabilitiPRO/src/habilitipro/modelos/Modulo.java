package habilitipro.modelos;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;

import static habilitipro.modelos.Trilha.getListaTrilhas;
import static habilitipro.util.Validacao.*;
import static habilitipro.util.DataHandler.*;
import static habilitipro.util.FileHandler.*;

public class Modulo {

    private Trilha trilha;
    private String nome;
    private String habilidades;
    private String tarefaDeValidacao;
    private LocalDate prazoLimite;
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int nota;
    private String anotacoes;
    private static LinkedHashSet<Modulo> listaModulo = new LinkedHashSet<>();

    public Modulo() {
        setTrilha();
        setNome();
        setHabilidades();
        setTarefaDeValidacao();
        setDataInicio();
        setDataFim();
        setPrazoLimite();
        setStatus();
        listaModulo.add(this);
    }

    private void setTrilha() {
        if(verificaListaVazia(getListaTrilhas(),
                "Não é possível a existência de um módulo sem a criação de uma trilha.")){
            new Trilha();
        }

        List<String> listaNomeTrilhas = new ArrayList<>();
        getListaTrilhas().forEach(t->listaNomeTrilhas.add(t.getNome()));
        int trilhaEscolhida = escolherOpcoes("Para qual trilha será destinado este módulo?",
                listaNomeTrilhas.toArray());
        this.trilha = getListaTrilhas().stream().toList().get(trilhaEscolhida);
        getListaTrilhas().stream().toList().get(trilhaEscolhida).getModulos().add(this);
    }

    private void setNome() {
        this.nome = validaString("Digite o nome do módulo:");
    }

    private void setHabilidades() {
        this.habilidades = validaString("Insira as habilidades trabalhadas no módulo:");
    }

    private void setTarefaDeValidacao() {
        this.tarefaDeValidacao = validaString("Insira a tarefa de validação do módulo:");
    }

    //DATA INICIAL DO CURSO DEFINIDA COMO O DIA SEGUINTE
    //PARA FINS DE VALIDAÇÃO DE STATUS
    private void setDataInicio() {

        this.dataInicio = LocalDate.now().plusDays(1);
        if(LocalDate.now().equals(dataInicio)){
        }
    }

    //DATA FINAL DO CURSO DEFINIDA COMO O DIA APÓS A DATA INICIAL
    //PARA FINS DE VALIDAÇÃO DE STATUS
    //SEM VIOLAR A [RN03-06]
    private void setDataFim() {
        this.dataFim = this.dataInicio.plusDays(1);
        JOptionPane.showMessageDialog(null,
                "Data final definida para o dia "+formatar(this.dataFim));
    }

    // PRAZO LIMITE DEFINIDO COMO 10 DIAS ÚTEIS APÓS A DATA FINAL DO CURSO
    //CONTANDO A PARTIR DO DIA APÓS A dataFim.
    private void setPrazoLimite() {
        String[] opcoes = {"Sim","Não"};
        int resposta = escolherOpcoes("Deseja manter o prazo limite padrão (10 dias úteis)?",opcoes);
        if(resposta==0) {
            this.prazoLimite = dataFim.plusDays(14) ;
        }else {
            int diasUteis = validaNumero("Insira a quantidade de dias para a finalização do módulo");
            this.prazoLimite = dataFim.plusDays(diasUteis);
        }

        JOptionPane.showMessageDialog(null,
                "Prazo limite para avaliação definido para dia "+formatar(this.prazoLimite));
    }

    private void setStatus() {
        Hashtable<Boolean, String> listaStatus = new Hashtable<>();
        listaStatus.put(LocalDate.now().compareTo(this.dataInicio)<=0,
                "Curso não iniciado");
        listaStatus.put(LocalDate.now().compareTo(this.dataInicio)>=0&&
                LocalDate.now().compareTo(this.dataFim)<0,"Curso em andamento");
        listaStatus.put(LocalDate.now().compareTo(this.dataFim)>0&&
                LocalDate.now().compareTo(this.prazoLimite)<=0,"Em fase de avaliação");
        listaStatus.put(LocalDate.now().compareTo(this.prazoLimite)>0,"Fase de avaliação finalizada");

        this.status = listaStatus.get(true);
        JOptionPane.showMessageDialog(null,"Status definido como: "+this.status);
    }

    public static LinkedHashSet<Modulo> getListaModulo() {
        return listaModulo;
    }

    public Trilha getTrilha() {
        return this.trilha;
    }

    public String getNome() {
        return this.nome;
    }

    public LocalDate getPrazoLimite() {
        return this.prazoLimite;
    }
}