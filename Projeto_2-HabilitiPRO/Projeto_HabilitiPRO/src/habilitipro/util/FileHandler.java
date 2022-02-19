package habilitipro.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static void escreverNoArquivo(String caminho, String mensagem) {

        try (FileWriter fw = new FileWriter(caminho, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(mensagem);
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: "+ e.getMessage());
        }
    }

}
