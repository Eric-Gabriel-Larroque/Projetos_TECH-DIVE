package gerenciamento_de_usuarios;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public enum Departamentos {

    FATURAMENTO("Faturamento"),
    ATENDIMENTO("Atendimento"),
    VENDAS("Vendas"),
    TRANSPORTE("Transporte");


    private final String nomeDepartamento;

    Departamentos(String nome) {
        this.nomeDepartamento = nome;
        toString();
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

}