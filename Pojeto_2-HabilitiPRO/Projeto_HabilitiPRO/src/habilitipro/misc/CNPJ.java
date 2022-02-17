package habilitipro.misc;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static habilitipro.util.Validacao.validaString;

public class CNPJ {

    private String CNPJ;
    private static List<String> listaCNPJ = new ArrayList<>();

    public CNPJ() {
        setCNPJ();
        listaCNPJ.add(this.CNPJ);
    }

    private void setCNPJ() {
        String CNPJ_TEMPLATE = "\\d\\d.\\d\\d\\d.\\d\\d\\d/\\d\\d\\d\\d-\\d\\d";

        //ADICIONANDO CPF PARA POSSIBILITAR CAIR NA EXCEÇÃO
        listaCNPJ.add("11.111.111/1111-11");

        this.CNPJ = validaString("Insira o CNPJ (Formato XX.XXX.XXX/XXXX-XX):",
                CNPJ_TEMPLATE,"Formato de CNPJ inválido. Insira novamente:");

        while (listaCNPJ.contains(this.CNPJ)){
            JOptionPane.showMessageDialog(null,
                    "O CNPJ informado já se encontra em nossa base de dados.");
            this.CNPJ = validaString("Insira o CNPJ (Formato XX.XXX.XXX/XXXX-XX):",
                    CNPJ_TEMPLATE,"Formato de CNPJ inválido. Insira novamente:");
        }
    }
}
