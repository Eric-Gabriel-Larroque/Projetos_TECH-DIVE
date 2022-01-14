package banco;


public class ContaCorrente extends Conta {
    //Limite definido como 15% do valor da renda mensal
    private final double limite = -(super.getRendaMensal()/100)*15;

    public double getLimite() {
        return limite;
    }

}