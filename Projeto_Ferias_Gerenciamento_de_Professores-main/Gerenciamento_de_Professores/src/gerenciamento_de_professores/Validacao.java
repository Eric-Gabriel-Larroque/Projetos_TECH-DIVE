package gerenciamento_de_professores;

import javax.swing.*;

public class Validacao {

    public int somenteNumeros(String entrada, String errorLabel) {
        String SOMENTE_NUMEROS_TEMPLATE = "[0-9]+";
        while(!entrada.matches(SOMENTE_NUMEROS_TEMPLATE)){
            entrada = entradaVazia(JOptionPane.showInputDialog(null,
                            "Somente números são permitidos. "+errorLabel),
                    errorLabel);
        }
        entrada = entrada.length()>10?entrada.substring(0,4):entrada;
        return Integer.parseInt(entrada);
    }

    public String entradaVazia(String entrada, String errorLabel) {
        entrada = String.valueOf(entrada);
        while (entrada.isEmpty() || entrada.isBlank()) {
            entrada = JOptionPane.showInputDialog(null,
                    "Entrada vazia. "+errorLabel);
            entrada = String.valueOf(entrada);
        }
        return entrada;
    }

    public void validaTurmaVazia() {
        if(Turma.listaTurma.size()==0) {
            JOptionPane.showMessageDialog(null,
                    "Para aproveitar todos os recursos disponíveis, \nvocê precisa cadastrar" +
                            " pelo menos uma turma primeiro.");
            return;
        }
    }

    public void validaDocenteVazio() {
        if(Docente.listaDocentes.size()==0){
            JOptionPane.showMessageDialog(null,
                    "Para aproveitar todos os recursos em sua totalidade," +
                            "\né necessário cadastrar pelo menos um " +
                             "docente antes.");
            return;
        }
    }

}
