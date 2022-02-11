package habilitipro.classes;

import java.time.LocalDate;
import java.util.*;

import static habilitipro.classes.Empresa.*;
import static habilitipro.util.Validacao.*;
import static habilitipro.classes.Modulo.*;
public class Trilha {

    private Empresa empresa;
    private String ocupacao;
    private String nome;
    private String apelido;
    private int nivelSatisfacao;
    private String anotacoes;
    private LinkedHashSet<Modulo> modulos = new LinkedHashSet<>();
    private static LinkedHashSet<Trilha> listaTrilhas = new LinkedHashSet<>();

    public Trilha() {
        setEmpresa();
        setOcupacao();
        setNome();
        setApelido();
        setModulos();
        listaTrilhas.add(this);
    }

    public static LinkedHashSet<Trilha> getListaTrilhas() {
        return listaTrilhas;
    }

    private void setEmpresa() {
        if(verificaListaVazia(getListaEmpresa(),
                "Não é possível definir uma trilha sem a existência de uma Empresa.")){
            new Empresa();
        }

        List<String> listaNomeEmpresas = new ArrayList<>();
        getListaEmpresa().forEach(e->listaNomeEmpresas.add(e.getNome()));
        int empresaEscolhida = escolherOpcoes("Para qual empresa será destinada esta trilha?",
                listaNomeEmpresas.toArray());
        this.empresa = getListaEmpresa().get(empresaEscolhida);
        getListaEmpresa().get(empresaEscolhida).getTrilhas().add(this);
    }

    private void setOcupacao() {
        this.ocupacao = validaString("Para qual ocupação é destinada esta trilha:");
    }

    private int getNumeroSequencial() {
        int length = listaTrilhas.stream()
                .filter(t -> t.empresa == this.empresa && t.ocupacao.equals(this.ocupacao))
                .toArray().length;
        return length>0?length+1:1;
    }

    private void setNome() {
        this.nome = this.ocupacao+this.empresa.getNome()+getNumeroSequencial()+ LocalDate.now().getYear();
    }

    private void setApelido() {
        this.apelido = getNumeroSequencial()+this.ocupacao;
    }

    private void setModulos() {
        getListaModulo().stream()
                .filter(m->m.getTrilha().equals(this))
                .forEach(m->modulos.add(m));
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getNome() {
        return this.nome;
    }
    public LinkedHashSet<Modulo> getModulos() {
        return this.modulos;
    }

    public void setNivelSatisfacao() {
        String nivelSatisfacao = "";
        if(modulos.stream().toList().get(modulos.size()-1)
                .getPrazoLimite().compareTo(LocalDate.now())>0) {
            nivelSatisfacao = validaString("Qual o nível de satisfação da trilha?","[1-5]",
                    "O nível de satisfação deve ser inserido com um valor entre 1 e 5");
        }
    }
}