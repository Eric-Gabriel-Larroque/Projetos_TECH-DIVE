package habilitipro.enums;

public enum Regionais {

    NORTE_NORDESTE("Norte - Nordeste"),
    OESTE("Oeste"),
    SUDESTE("Sudeste"),
    CENTRO_NORTE("Centro - Norte"),
    VALE_ITAJAI("Vale do Itajaí"),
    VALE_ITAPOCU("Vale do Itapocú"),
    LITORAL_SUL("Litoral Sul"),
    ALTO_URUGUAI_CATARINENSE("Alto Uruguai Catarinense"),
    VALE_ITAJAI_MIRIM("Vale do Itajaí Mirim"),
    CENTRO_OESTE("Centro - Oeste"),
    PLANALTO_NORTE("Planalto Norte"),
    FOZ_RIO_ITAJAI("Foz do Rio Itajaí"),
    SUL("Sul"),
    SERRA("Serra Catarinense"),
    EXTREMO_OESTE("Extremo Oeste"),
    ALTO_VALE_ITAJAI("Alto Vale do Itajaí");

    private String nome;

    private Regionais(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
