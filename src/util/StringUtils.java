package util;

public class StringUtils {

    public static String genererIdUnique(String prefixe, int numero) {
        return prefixe + String.format("%03d", numero);
    }

    public static boolean estValide(String chaine) {
        return chaine != null && !chaine.trim().isEmpty();
    }

    public static String formaterMontant(double montant) {
        return String.format("%.2f MAD", montant);
    }

    public static String capitaliser(String chaine) {
        if (!estValide(chaine)) return "";
        return chaine.substring(0, 1).toUpperCase()
                + chaine.substring(1).toLowerCase();
    }

    public static boolean emailValide(String email) {
        return estValide(email) && email.contains("@") && email.contains(".");
    }
}