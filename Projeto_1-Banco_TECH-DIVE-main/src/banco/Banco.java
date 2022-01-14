package banco;

public class Banco {

    // É aqui onde se cria sua conta (e onde deverá ser incializado o projeto)
    public static void iniciar() {
        if (Conta.listaContas.size()==0){
            System.out.print("\nSeja bem-vindo à agência do banco! Para liberar qualquer operação, primeiramente você " +
                    "precisa criar uma conta. ");
            Conta.criarConta();
        }

    }


    public static void main(String[] args) {
        Banco.iniciar();
    }
}
