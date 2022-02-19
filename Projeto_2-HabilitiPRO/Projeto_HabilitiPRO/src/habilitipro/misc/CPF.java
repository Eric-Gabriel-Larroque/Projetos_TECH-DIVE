package habilitipro.misc;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static habilitipro.util.Validacao.validaString;

public class CPF {
    private String CPF;
    private static List<String> listaCpf = new ArrayList<>();
    private final String CPF_TEMPLATE = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";

    public CPF() {
        setCPF();
        listaCpf.add(this.CPF);
    }

    private void setCPF() {
            //ADICIONANDO CPF PARA POSSIBILITAR CAIR NA EXCEÇÃO
            listaCpf.add("111.111.111-11");

            this.CPF = validaString("Insira o CPF (Formato XXX.XXX.XXX-XX):",
                    this.CPF_TEMPLATE,"Formato de CPF inválido. Insira novamente:");

            while (listaCpf.contains(this.CPF)){
                JOptionPane.showMessageDialog(null,
                        "Esse CPF já se encontra em nossa base de dados.");
                this.CPF = validaString("Insira o CPF (Formato XXX.XXX.XXX-XX):",
                        CPF_TEMPLATE,"Formato de CPF inválido. Insira novamente:");
            }
    }

    public String getCPF() {
        return this.CPF;
    }
}