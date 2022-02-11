package habilitipro.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHandler {

    public static String formatar(LocalDate data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}