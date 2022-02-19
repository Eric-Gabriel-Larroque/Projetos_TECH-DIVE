package habilitipro.enums;

public enum Perfis {

    ADM("Administrativo"),
    OPERACIONAL("Operacional"),
    RH("RH");

    private String perfilDeAcesso;

    private Perfis(String perfilDeAcesso) {
        this.perfilDeAcesso = perfilDeAcesso;
    }

    public String getPerfilDeAcesso() {
        return this.perfilDeAcesso;
    }
}
