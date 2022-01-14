package gerenciamento_de_professores;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUI extends Validacao {

    public void iniciar() {

      Integer[] opcoes = {1,2,3};
      int resposta = 0;
      String mensagemPadrao = "O que deseja fazer?" +
                              "\n1 - Cadastrar Turma" +
                              "\n2 - Cadastrar Docente" +
                              "\n3 -  Sair";
        List<Integer> respostasPossiveis = new ArrayList<>();
        Collections.addAll(respostasPossiveis,opcoes);
      while(!respostasPossiveis.contains(resposta)) {
          resposta = somenteNumeros(
                entradaVazia(
                  JOptionPane.showInputDialog(null,
                          "Bem-vindo(a)!"+mensagemPadrao),
                mensagemPadrao),
          mensagemPadrao);
      }
        switch (resposta) {
            case 1:
                Turma turma = new Turma();
                if(Docente.listaDocentes.size()==0){
                    validaDocenteVazio();
                }
                turma.operacoes();
                break;
            case 2:
               if(Turma.listaTurma.size()==0) {
                   validaTurmaVazia();
                   Turma turma1 = new Turma();
                   turma1.operacoes();
               } else{
                   Docente docente = new Docente();
                   docente.operacoes();
               }
                break;
            case 3:
                try {
                    JOptionPane.showMessageDialog(null,"Volte sempre! :)");
                    System.exit(0);
                }catch(Exception e) {

                }
        }
    }

}
