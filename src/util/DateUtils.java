package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String dateStr) {
        try { return LocalDate.parse(dateStr, FORMAT); }
        catch (Exception e) { return null; }
    }

    public static String formatDate(LocalDate date) {
        return (date == null) ? "" : date.format(FORMAT);
    }
}