package habilitipro.enums;

public enum Segmento {

    ALIMENTOS_BEBIDAS("Alimentos e Bebidas"),
    CELULOSE("Celulose e Papel"),
    CONSTRUCAO("Construção"),
    EQUIP_ELETRICOS("Equipamentos elétricos"),
    SAUDE("Fármacos e Equipamentos de Saúde"),
    FUMO("Fumo"),
    AUTOMOTIVO("Indústria Automotiva"),
    CERAMICA("Indústria Cerâmica"),
    DIVERSA("Indústria Diversa"),
    EXTRATIVA("Indústria Extrativa"),
    GRAFICA("Indústria Gráfica"),
    MADEIRA_MOVEIS("Madeira e Móveis"),
    MAQUINAS_EQUIP("Máquinas e equipamentos"),
    METALURGICA("Metalmecânica e Metalurgia"),
    OLEO_GAS_ELETRICIDADE("Óleo, Gás e Eletricidade"),
    QUIMICOS_PLASTICOS("Produtos Químicos e Plásticos"),
    SANEAMENTO("Saneamento básico"),
    TIC("TIC"),
    CONFECCAO_TEXTIL("Têxtil, Confecção, Couro e Calçados");

    private String nome;

    Segmento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return  this.nome;
    }
}