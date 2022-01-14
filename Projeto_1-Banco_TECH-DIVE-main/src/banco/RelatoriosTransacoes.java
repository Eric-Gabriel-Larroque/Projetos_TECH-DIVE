package banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RelatoriosTransacoes {

    private final Scanner entrada = new Scanner(System.in);

    public static String validaEntradaVazia(String entrada) {
        Scanner sc = new Scanner(System.in);
        while (entrada.isEmpty() || entrada.isBlank()) {
            System.out.print("\nEntrada de dados vazia. Por favor, digite novamente: ");
            entrada = sc.nextLine();
        }
        return entrada;
    }

    public static double validaSomenteNumero(String entrada) {
        Scanner sc = new Scanner(System.in);
        final String NUMEROS_TEMPLATE = "[0-9]+";
        while(!entrada.matches(NUMEROS_TEMPLATE)){
            System.out.print("\nCaracteres inválidos. Insira somente números: ");
            entrada = sc.nextLine();
        }
        return Double.parseDouble(entrada);
    }

    public void listarContas() {
        List<Conta> contaCorrenteLista = new ArrayList<>();
        for(Conta contas: Conta.listaContas) {
            if(contas instanceof ContaCorrente) {
                contaCorrenteLista.add(contas);
            }
        }
        List<Conta> contaPoupancaLista = new ArrayList<>();
        for(Conta contas: Conta.listaContas) {
            if(contas instanceof ContaPoupanca) {
                contaPoupancaLista.add(contas);
            }
        }
        List<Conta> contaInvestimentoLista = new ArrayList<>();
        for(Conta contas: Conta.listaContas) {
            if(contas instanceof ContaInvestimento) {
                contaInvestimentoLista.add(contas);
            }
        }
        String[] opcoes = {"1","2","3"};
        List<String> listaOpcoes = new ArrayList<>();
        Collections.addAll(listaOpcoes,opcoes);
        String listar;
        while(!listaOpcoes.contains(opcoes)) {
            System.out.print("\nQuais contas você gostaria de listar?\n1 - Conta Poupança\n2 - Conta Corrente" +
                    "\n3 - Conta Investimento\nq - Sair\n--> ");
            listar = validaEntradaVazia(entrada.nextLine());

            switch (listar) {
                case "1":
                    if(contaPoupancaLista.size()==0){
                        System.out.println("Não há contas do tipo poupança disponíveis para listar");
                    }else{
                        for(Conta contas: contaPoupancaLista) {
                            contas.extrato();
                        }
                    }
                    break;
                case "2":
                    if(contaCorrenteLista.size()==0){
                        System.out.print("\nNão há contas corrente disponíveis para listar");
                    }else{
                        for(Conta contas: contaCorrenteLista) {
                            contas.extrato();
                        }
                    }
                    break;
                case "3":
                    if(contaInvestimentoLista.size()==0){
                        System.out.print("\nNão há contas de investimento disponíveis para listar");
                    }else{
                        for(Conta contas: contaInvestimentoLista) {
                            contas.extrato();
                        }
                    }
                    break;
                case "q": case "Q":
                    return;
                default:
                    System.out.print("\nNão entendi o que você quis dizer com isso.");
                    break;
            }
        }

    }

    public void contasNegativo() {
        List<Conta> contasNegativo = new ArrayList<>();
        for(Conta contas: Conta.listaContas) {
            if(contas.getSaldo()<0) {
                contasNegativo.add(contas);
            }
        }
        if(contasNegativo.size()==0) {
            System.out.print("Não há contas com saldo em negativo para serem mostradas");
        } else {
            for(Conta conta: contasNegativo) {
                conta.extrato();
            }
        }
    }

    public void totalValorInvestido() {
        double totalValor = 0;
        List<Conta> contaInvestimentoLista = new ArrayList<>();
        for(Conta contas: Conta.listaContas) {
            if(contas instanceof ContaInvestimento) {
                contaInvestimentoLista.add(contas);
            }
        }
        for(Conta conta: contaInvestimentoLista) {
            totalValor+= conta.getSaldo();
        }
        System.out.printf("\nO valor total investido é de %.2f",totalValor);
    }

    public void historicoTransacoes() {
        int resposta = 0;
        int transacoesDisponiveis = 0;

        for(Conta historico: Conta.listaContas) {
            transacoesDisponiveis += historico.transacoes.size();
        }
        if(transacoesDisponiveis==0) {
            System.out.print("\nNo momento não há históricos disponíveis para verificação.");
            return;
        }
        while(!Conta.contasUsuarios.contains(resposta)) {
            System.out.print("\nDigite o nº da conta que você gostaria de listar o histórico das transações\n" +
                    "Contas disponíveis para verificar histórico:\n");
            for(int i = 0;i<Conta.contasUsuarios.size();i++) {
                System.out.print((i+1)+") "+Conta.contasUsuarios.get(i)+"\n");
            }
            System.out.print("\n--> ");
            resposta = (int) validaSomenteNumero(validaEntradaVazia(entrada.nextLine()));
            if(!Conta.contasUsuarios.contains(resposta)) {
                System.out.println("Valor inválido ou número da conta inexistente.");
            } else if(Conta.listaContas.get(Conta.contasUsuarios.indexOf(resposta)).transacoes.size()==0) {
                System.out.println("Não há um histórico de transações disponíveis para essa conta, porém existe em pelo menos uma das contas" +
                        " listadas.");
            } else {
                for(String historico: Conta.listaContas.get(Conta.contasUsuarios.indexOf(resposta)).transacoes){
                    System.out.print(historico);
                }
            }
        }
    }
}