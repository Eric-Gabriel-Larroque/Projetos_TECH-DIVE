package habilitipro.classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static habilitipro.util.Validacao.*;
import static habilitipro.classes.Empresa.getListaEmpresa;
import static habilitipro.classes.Trilha.*;
import static habilitipro.classes.Modulo.getListaModulo;
public class Trabalhador {

    private String nome;
    private String CPF;
    private Empresa empresa;
    private String setorArea;
    private String funcao;
    private LinkedHashSet<Trilha> trilhasEscolhidas = new LinkedHashSet<>();

    public Trabalhador() {
        setEmpresa();
        setNome();
        setCPF();
        setTrilhasEscolhidas();
    }

    private void setNome() {
        this.nome = validaString("Insira o nome do trabalhador");
    }
    private void setCPF() {
        final String CPF_TEMPLATE = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
        this.CPF = validaString("Insira o CPF (Formato XXX.XXX.XXX-XX):",
                CPF_TEMPLATE,"Formato de CPF inválido. Insira novamente:");
    }

    private void setEmpresa() {
        if(verificaListaVazia(getListaModulo(),"Não pode ser cadastrado um trabalhador sem ser vinculado" +
                " a pelo menos 1 Módulo")){
            new Modulo();
        }
        List<String> listaNomeEmpresas = new ArrayList<>();
        getListaEmpresa().forEach(e->listaNomeEmpresas.add(e.getNome()));
        int empresaEscolhida = escolherOpcoes("Qual empresa será vinculada ao trabalhador?",
                listaNomeEmpresas.toArray());
        this.empresa = getListaEmpresa().get(empresaEscolhida);
    }

    private void setTrilhasEscolhidas(){
        int resposta = -1;
        String[] opcoes = {"Sim","Não"};
        List<String> listaNomeTrilhas = new ArrayList<>();
        getEmpresa().getTrilhas().forEach(t->listaNomeTrilhas.add(t.getNome()));
        while(resposta!=1){
            int trilha = escolherOpcoes("Qual(is) trilha(s) o trabalhador seguirá?",
                    listaNomeTrilhas.toArray());
            trilhasEscolhidas.add(getEmpresa().getTrilhas().stream().toList().get(trilha));
            resposta = escolherOpcoes("Gostaria de adicionar mais trilhas?",opcoes);
            System.out.println(trilhasEscolhidas.size());
        }
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }
}
