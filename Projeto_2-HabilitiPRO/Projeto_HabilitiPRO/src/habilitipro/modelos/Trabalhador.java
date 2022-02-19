package habilitipro.modelos;

import habilitipro.misc.CPF;

import javax.swing.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import static habilitipro.util.DataHandler.*;
import static habilitipro.util.Validacao.*;
import static habilitipro.modelos.Empresa.getListaEmpresa;
import static habilitipro.modelos.Modulo.getListaModulo;
import static habilitipro.util.FileHandler.escreverNoArquivo;
public class Trabalhador {

    private String nome;
    private CPF CPF;
    private String nomeArquivo;
    private Empresa empresa;
    private String setorArea;
    private String funcao;
    private LocalDate dataAdmissao;
    private LinkedHashMap<Modulo,String> anotacoes = new LinkedHashMap<>();
    private LinkedHashMap<Modulo,Integer> notas = new LinkedHashMap<>();
    private LinkedHashSet<Trilha> trilhasEscolhidas = new LinkedHashSet<>();


    public Trabalhador() {
        setEmpresa();
        setNome();
        this.CPF = new CPF();
        this.nomeArquivo = "registro_trabalhador_"+this.nome.toLowerCase()+"_"+this.CPF.getCPF()+".txt";
        setTrilhasEscolhidas();
        setSetorArea();
        setFuncao();
        setRegistro();
        registraModulo();
    }

    private void setNome() {
        this.nome = validaString("Insira o nome do trabalhador");
    }

    public void setEmpresa() {
        boolean repetir = true;
        int empresaEscolhida = 0;
        if(verificaListaVazia(getListaModulo(),
                "Não pode ser cadastrado um trabalhador sem ser vinculado" +
                " a pelo menos 1 Módulo")){
            new Modulo();
        }

        while(repetir){
            List<String> listaNomeEmpresas = new ArrayList<>();
            getListaEmpresa().forEach(e->listaNomeEmpresas.add(e.getNome()));
           if(this.empresa==null) {
               empresaEscolhida = escolherOpcoes("Qual empresa será vinculada ao trabalhador?",
                       listaNomeEmpresas.toArray());
           }else {
               empresaEscolhida = escolherOpcoes("Para qual empresa o trabalhador irá ser transferido?",
                       listaNomeEmpresas.toArray());
               String registro = "Trabalhador "+this.nome+" mudou para a Empresa "+this.getEmpresa().getNome()+".";
               escreverNoArquivo(nomeArquivo,registro);
           }
            repetir = false;
            this.empresa = getListaEmpresa().get(empresaEscolhida);
            if(this.empresa.getTrilhas().size()==-0){
                JOptionPane.showMessageDialog(null,
                        "Essa Empresa não possui Trilhas cadastradas.",
                        "Empresa sem Trilhas",JOptionPane.INFORMATION_MESSAGE);
                repetir = true;
            }
        }
        this.dataAdmissao = LocalDate.now();
    }

    private void setTrilhasEscolhidas(){
        int resposta = -1;
        String[] opcoes = {"Sim","Não"};
        List<String> listaNomeTrilhas = new ArrayList<>();
        getEmpresa().getTrilhas().forEach(t->listaNomeTrilhas.add(t.getNome()));
        while(resposta!=1||trilhasEscolhidas.size()==0){
            int trilha = escolherOpcoes("Qual(is) trilha(s) o trabalhador seguirá?",
                    listaNomeTrilhas.toArray());
            if(getEmpresa().getTrilhas().stream().toList().get(trilha).getModulos().size()==0){
                JOptionPane.showMessageDialog(null,
                        "Essa trilha não possui módulos");
            }else{
                trilhasEscolhidas.add(getEmpresa().getTrilhas().stream().toList().get(trilha));
                resposta = escolherOpcoes("Gostaria de adicionar mais trilhas?",opcoes);
            }
        }
    }

    //MODIFICADOR PÚBLICO PARA PERMITIR
    //MUNDANÇA DO SETOR/ÁREA E FUNÇÃO
    public void setSetorArea() {
        if(this.setorArea==null) {
            this.setorArea = validaString("Insira o Setor/Área em que o Trabalhador atua: ");
        }else{
            this.setorArea = validaString("Insira para qual área o Trabalhador irá migrar:");
            String registro = "Trabalhador "+this.nome+" mudou para Área/Setor "+this.setorArea+"\n";
                escreverNoArquivo(nomeArquivo,registro);
        }
    }

    public void setFuncao() {
        if(this.funcao==null) {
            this.funcao = validaString("Digite a função exercida pelo Trabalhador");
        }else{
            this.funcao = validaString("Digite para qual Função o Trabalhador irá migrar:");
            String registro = "Trabalhador "+this.nome+" mudou para a Função "+this.funcao+"\n";
            escreverNoArquivo(this.nomeArquivo,registro);
        }
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setAnotacoes() {
        this.getEmpresa().getTrilhas().forEach(t-> {
            t.getModulos().forEach(m -> {
                if (m.getStatus().equals("Em fase de avaliação")) {
                    String anotacao = validaString("Anotação para o módulo "+m.getNome()+":");
                    anotacoes.put(m,anotacao);
                }
            });
        });
    }

    public void setNotas() {
        this.getEmpresa().getTrilhas().forEach(t-> {
            t.getModulos().forEach(m -> {
                if (m.getStatus().equals("Em fase de avaliação")) {
                    final String  NOTA_TEMPLATE = "^[1-5]";
                    int nota = Integer.parseInt(validaString("nota para o módulo "+m.getNome(),NOTA_TEMPLATE,
                            "A nota deve ser um número inteiro positivo entre 1 e 5"));
                }
            });
        });
    }
    private void setRegistro() {
        String registro = "Trabalhador "+this.nome+" possui "+this.trilhasEscolhidas.size()+" Trilha(s) "+
                "executada(s) e vinculada(s)\nà Área/Setor "+this.setorArea+" ou Função "+this.funcao+"\n";
        escreverNoArquivo(nomeArquivo,registro);
    }

    public void registraModulo() {
        this.trilhasEscolhidas.forEach(trilha->{
            trilha.getModulos().forEach(m->{
                if(m.getStatus().equals("Curso em andamento")){
                    escreverNoArquivo(nomeArquivo,
                            "Módulo "+m.getNome()+" inciado por "+this.nome+
                            "às "+formatar(OffsetDateTime.now())+"\n");
                }else if(m.getStatus().equals("Em fase de avaliação")){
                    escreverNoArquivo(nomeArquivo,
                            "Módulo"+this.nome+" inciado por "+this.nome+
                                    "às"+formatar(OffsetDateTime.now())+"\n");
                }
            });
        });
    }
}