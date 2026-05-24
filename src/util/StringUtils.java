package util;

public class StringUtils {
    public static boolean estValide(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static String formaterMontant(double mnt) {
        return String.format("%.2f MAD", mnt);
    }
}