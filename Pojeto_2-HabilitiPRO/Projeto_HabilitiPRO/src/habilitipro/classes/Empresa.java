package habilitipro.classes;

import habilitipro.enums.Regionais;
import habilitipro.enums.Segmento;

import java.util.*;

import static habilitipro.util.Validacao.*;
import static habilitipro.classes.Trilha.*;

public class Empresa {
    private String nome;
    private String CNPJ;
    private String filial;
    private Segmento segmento;
    private String estado;
    private String cidade;
    private Regionais regional;
    private LinkedHashSet<Trilha> trilhas = new LinkedHashSet<>();
    private static List<Empresa> listaEmpresa = new ArrayList<>();

    public Empresa() {
        this.setNome();
        this.setCNPJ();
        this.setMatrizFilial();
        this.setSegmento();
        this.setEstado();
        this.setCidade();
        this.setRegional();
        this.setTrilhas();
        listaEmpresa.add(this);
    }

    private void setNome(){
        this.nome = validaString("Insira o nome da empresa");
    }

    public String getNome() {
        return this.nome;
    }
    private void setCNPJ() {
        final String CNPJ_TEMPLATE = "\\d\\d.\\d\\d\\d.\\d\\d\\d/\\d\\d\\d\\d-\\d\\d";
        this.CNPJ = validaString("Insira o CNPJ (formato XX.XXX.XXX/XXXX-XX)", CNPJ_TEMPLATE,
                "Formato do CNPJ inválido. Insira corretamente:\n(XX.XXX.XXX/XXXX-XX)");
    }
    private void setMatrizFilial() {
        this.filial =
                escolherOpcoes("Sua empresa é:","Matriz","Filial")==0?
                "Matriz":validaString("Insira o nome da sua filial");
    }

    private void setSegmento() {
       List<String> segmentosDisponíveis = new ArrayList<>();
       List.of(Segmento.values()).forEach(s->segmentosDisponíveis.add(s.getNome()));
        int segmentoEscolhido = escolherOpcoes("Escolha dentre o seu segmentos dentre os disponíveis",
                segmentosDisponíveis.toArray());
        this.segmento = List.of(Segmento.values()).get(segmentoEscolhido);
    }

  private void setEstado() {
        this.estado = validaString("Insira seu Estado");
  }
  private void setCidade() {
        this.cidade = validaString("Nome da cidade da empresa");
  }
  private void setRegional() {
        List<String> regionaisDisponiveis = new ArrayList<>();
        List.of(Regionais.values()).forEach(r->regionaisDisponiveis.add(r.getNome()));
        int regionalEscolhida = escolherOpcoes("Com qual das regionais sua empresa está vinculada?",
                regionaisDisponiveis.toArray());
        this.regional = List.of(Regionais.values()).get(regionalEscolhida);
    }

  private void setTrilhas() {
        getListaTrilhas().stream()
                .filter(t->t.getEmpresa().equals(this))
                .forEach(t->trilhas.add(t));
  }

  public static List<Empresa> getListaEmpresa() {
        return listaEmpresa;
  }

  public LinkedHashSet<Trilha> getTrilhas() {
        return this.trilhas;
  }
}