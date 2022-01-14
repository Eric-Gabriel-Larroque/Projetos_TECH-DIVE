package banco;

import static banco.RelatoriosTransacoes.*;

public class ContaPoupanca extends Conta{




    // Poupança com rentabilidade anual de 6,475%
    public void simularRenda(){
        final double rendimentoAnual = 6.475;
        final double rendimentoMensal = rendimentoAnual/12;
        double rendimentoCapital;
        int meses;
        System.out.print("Informe o número de meses que você quer simular o rendimento de seu capital: ");
        meses = (int) validaSomenteNumero(validaEntradaVazia(entrada.nextLine()));
        rendimentoCapital = (super.getRendaMensal()/100)*(rendimentoMensal*meses);
        System.out.print("Atualmente o rendimento anual da poupança é de "+rendimentoAnual+"%\n");
        System.out.printf("Com a renda mensal de %.2f, o rendimento de seu capital em %d meses renderá %.2f reais.",
                super.getRendaMensal(),meses,rendimentoCapital);
    }


    @Override
    public void iniciarAtendimento() {
        RelatoriosTransacoes reltransacoes = new RelatoriosTransacoes();
        String operacao = "";
        while(operacao!="q"||operacao!="Q") {
            System.out.print("\nBem-vindo! O que você deseja fazer ?\n1 - Criar outra conta\n2 - Saque" +
                    "\n3 - Depósito\n4 - Tranferência\n5 - Ver saldo\n6 - Extrato\n7 - Alterar Dados" +
                    "\n8 - Listar contas\n9 - Total do valor investido\n10 - Histórico de transações" +
                    "\n11 - Listar contas com saldo negativo"+
                    "\n12 - Simular Rendimento da poupança\nq - sair\n--> ");

            operacao = entrada.nextLine();
            switch(operacao) {
                case "1":
                    criarConta();
                    break;
                case "2":
                    saque();
                    break;
                case "3":
                    deposito();
                    break;
                case "4":
                    transferir();
                    break;
                case "5":
                    saldo();
                    break;
                case "6":
                    extrato();
                    break;
                case "7":
                    alterarDados();
                    break;
                case"8":
                    reltransacoes.listarContas();
                    break;
                case "9":
                    reltransacoes.totalValorInvestido();
                    break;
                case "10":
                    reltransacoes.historicoTransacoes();
                    break;
                case "11":
                    reltransacoes.contasNegativo();
                    break;
                case "12":
                    simularRenda();
                    break;
                case "q": case"Q":
                    System.out.print("\nVolte sempre! :)");
                    return;
                default:
                    System.out.print("\nNão entendi o que você quis dizer com isso.");
                    break;
            }
        }
    }

}
