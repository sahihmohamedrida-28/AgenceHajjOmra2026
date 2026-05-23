package util;

public class StringUtils {

    // Genere un ID unique : "P001", "V003", "R012"
    // Prefixe = "P" pour pelerin, "V" pour voyage, etc.
    // Numero = le chiffre
    public static String genererIdUnique(String prefixe, int numero) {
        return prefixe + String.format("%03d", numero);
        // %03d = nombre sur 3 chiffres avec zeros : EX: 1 -> "001"
    }

    // Verifie qu'une chaine n'est pas vide
    public static boolean estValide(String chaine) {
        return chaine != null && !chaine.trim().isEmpty();
    }

    // Affiche un montant proprement
    public static String formaterMontant(double montant) {
        return String.format("%.2f MAD", montant);
    }

    // Met la premiere lettre en majuscule :  EX: "rida" -> "Rida"
    public static String capitaliser(String chaine) {
        if (!estValide(chaine)) return "";
        return chaine.substring(0, 1).toUpperCase() + chaine.substring(1).toLowerCase();
    }

    // Verifie basiquement si un email est valide
    public static boolean emailValide(String email) {
        return estValide(email) && email.contains("@") && email.contains(".");
    }
}