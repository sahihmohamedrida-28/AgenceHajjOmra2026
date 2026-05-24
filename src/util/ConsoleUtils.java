package util;

import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static String lireChaine(String message) {
        System.out.print(message);
        String valeur = scanner.nextLine().trim();
        while (valeur.isEmpty()) {
            System.out.print("Champ obligatoire. " + message);
            valeur = scanner.nextLine().trim();
        }
        return valeur;
    }

    public static int lireEntier(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int valeur = Integer.parseInt(scanner.nextLine().trim());
                if (valeur >= min && valeur <= max) return valeur;
                System.out.println("Entrez un nombre entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Entree invalide.");
            }
        }
    }

    public static double lireDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(
                        scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Montant invalide. Exemple : 45000.00");
            }
        }
    }

    public static boolean lireOuiNon(String message) {
        System.out.print(message + " (o/n) : ");
        String rep = scanner.nextLine().trim().toLowerCase();
        return rep.equals("o") || rep.equals("oui");
    }

    public static Scanner getScanner() { return scanner; }
}