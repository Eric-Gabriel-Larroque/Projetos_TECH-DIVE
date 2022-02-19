package habilitipro.util;

import javax.swing.*;
import java.util.*;

public class Validacao {

    private static Collection c;
    private static List<String> listaCPF = new ArrayList<>();
    private static List<String> listaCNPJ = new ArrayList<>();
    public static String validaString(String mensagem) {
        String entrada = "";
        boolean repetir = true;
        while (repetir) {
            entrada = JOptionPane.showInputDialog(null,mensagem);
            repetir = false;
            if(entrada==null||entrada.isEmpty()||entrada.isBlank()) {
                JOptionPane.showMessageDialog(null,"Entrada de dados vazia. Por favor," +
                        " tente novamente.","Dados vazios ou inválidos",JOptionPane.ERROR_MESSAGE);
                repetir = true;
            }
        }
        return entrada;
    }

    public static String validaString(String mensagem,String template, String mensagemTemplate) {
        String entrada="";
        boolean repetir = true;
        while (repetir) {
            entrada = JOptionPane.showInputDialog(null,mensagem);
            repetir = false;
            if(entrada==null||entrada.isEmpty()||entrada.isBlank()) {
                JOptionPane.showMessageDialog(null,"Entrada de dados vazia. Por favor," +
                        " tente novamente.","Dados vazios ou inválidos",JOptionPane.ERROR_MESSAGE);
                repetir = true;
            } else if (!entrada.matches(template)) {
                JOptionPane.showMessageDialog(null,mensagemTemplate,
                        "Formato inválido",JOptionPane.ERROR_MESSAGE);
                repetir = true;
            }
        }
        return entrada;
    }

    public static int escolherOpcoes(String mensagem, Object ...opcoes) {
        String listarOpcoes = "";
        for(int i = 0;i < opcoes.length;i++) {
            listarOpcoes+="\n"+(i+1)+" - "+opcoes[i];
        }
        int resposta = 0;

        do{
            resposta=validaNumero(mensagem+listarOpcoes);

            if(resposta<=0||resposta>opcoes.length) {
                JOptionPane.showMessageDialog(null,
                        "Escolha um número dentre os disponíveis",
                        "Número não permitido",JOptionPane.ERROR_MESSAGE);
            }

        }while(resposta<=0||resposta>opcoes.length);
        return resposta-1;
    }

    public static int validaNumero(String mensagem) {
        final String NUMERO_TEMPLATE = "^[1-9]+";
        String entrada;
        do{
            entrada = validaString(mensagem);

            if(!entrada.matches(NUMERO_TEMPLATE)) {
                JOptionPane.showMessageDialog(null,
                        "Somente números inteiros e positivos são permitidos.",
                        "Dados inválidos",JOptionPane.ERROR_MESSAGE);
            }else if(entrada.equals("0")){
                JOptionPane.showMessageDialog(null,
                        "O número não pode ser igual a zero.",
                        "Número igual a zero",JOptionPane.ERROR_MESSAGE);
            }
        }while(entrada==null||!entrada.matches(NUMERO_TEMPLATE));
        return Integer.parseInt(entrada);
    }

    public static boolean verificaListaVazia(Collection c, String mensagem) {

        if(c.isEmpty()) {
            JOptionPane.showMessageDialog(null,mensagem);
        }
        return c.isEmpty();
    }
}