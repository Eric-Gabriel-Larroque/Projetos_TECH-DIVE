package habilitipro;

import habilitipro.classes.Trabalhador;

import java.time.LocalDate;

public class Main {

    static LocalDate dataFim = LocalDate.now();
    static LocalDate prazoLimite = dataFim.plusDays(10);

    public static void main(String[] args) {
        Trabalhador trabalhador = new Trabalhador();
    }
}