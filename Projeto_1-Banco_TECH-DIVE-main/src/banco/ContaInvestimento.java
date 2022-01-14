package banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static banco.RelatoriosTransacoes.*;


public class ContaInvestimento extends Conta {

    public void simularInvestimento() {
        double rendimento;
        String resposta = "";
        String[] respostas = {"1","2","3","q","Q"};
        List<String> respostasPossiveis = new ArrayList<>();
        Collections.addAll(respostasPossiveis,respostas);
        while(!respostasPossiveis.contains(resposta)) {
            System.out.print("\nQual dos investimentos de renda fixa abaixo você gostaria de fazer a simulação de rendimento?" +
                    "\n1 - Selic\n2 - CDI\n3 - CDB\nq - sair\n--> ");
            resposta = validaEntradaVazia(entrada.nextLine());
            switch (resposta){
                case "1":
                    rendimento = (super.getRendaMensal()/100)*9.25;
                    System.out.printf("\nA taxa selic rende atualmente 9.25 por cento ao ano. Em um ano renderá %.2f " +
                            "reais com base em sua renda mensal.",rendimento);
                    break;
                case "2":
                    rendimento = (super.getRendaMensal()/100)*9.15;
                    System.out.printf("\nO rendimento da CDI atualmente é de 9.15 por cento. Em um ano lhe renderá %.2f " +
                            "reais com base em sua renda mensal.",rendimento);
                    break;
                case "3":
                    rendimento = (super.getRendaMensal()/100)*(9.15+(9.15/100*12));
                    System.out.printf("\nAtualmente o melhor Contrato de depósito bancário pode lhe render 112 por cento do CDI.\n" +
                            "Com esse investimento, seu dinheiro renderá anualmente %.2f reais com" +
                            " base em seu rendimento mensal.",rendimento);
                    break;
                case "q": case "Q":
                    return;
                default:
                    System.out.println("Não entendi o que você quis dizer com isso.");
            }
        }
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
                "\n12 - Simular Rendimento do investimento\nq - sair\n--> ");

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
                simularInvestimento();
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
