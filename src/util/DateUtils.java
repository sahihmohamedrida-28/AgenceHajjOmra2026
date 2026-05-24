package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMAT);
        } catch (Exception e) {
            System.out.println("Date invalide : " + dateStr);
            return null;
        }
    }

    public static String formatDate(LocalDate date) {
        if (date == null) return "";
        return date.format(FORMAT);
    }

    public static String aujourdhui() {
        return LocalDate.now().format(FORMAT);
    }
}