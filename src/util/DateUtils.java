package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    // Format de date utilise dans TOUT le projet : "YYYY-MM-DD"
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Convertit "YYYY-MM-DD" en LocalDate
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMAT);
        } catch (Exception e) {
            System.out.println("Date invalide : " + dateStr + " (format attendu : yyyy-MM-dd)");
            return null;
        }
    }

    // Convertit un LocalDate en "YYYY-MM-DD" pour sauvegarder dans le CSV
    public static String formatDate(LocalDate date) {
        if (date == null) return "";
        return date.format(FORMAT);
    }

    // Retourne la date d'aujourd'hui en texte
    public static String aujourdhui() {
        return LocalDate.now().format(FORMAT);
    }
}
