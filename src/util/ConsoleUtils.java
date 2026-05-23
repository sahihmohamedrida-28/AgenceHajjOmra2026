package util;

import java.util.Scanner;

public class ConsoleUtils {

    private static Scanner scanner = new Scanner(System.in);

    // Lit une chaine non vide
    public static String lireChaine(String message) {
        System.out.print(message);
        String valeur = scanner.nextLine().trim();
        while (valeur.isEmpty()) {
            System.out.print("Ce champ est obligatoire. " + message);
            valeur = scanner.nextLine().trim();
        }
        return valeur;
    }

    // Lit un nombre entier entre min et max
    public static int lireEntier(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int valeur = Integer.parseInt(scanner.nextLine().trim());
                if (valeur >= min && valeur <= max) {
                    return valeur;
                }
                System.out.println("Entrez un nombre entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Ce n'est pas un nombre valide.");
            }
        }
    }

    // Lit un nombre decimal (montant)
    public static double lireDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Montant invalide. Exemple : 45000.00");
            }
        }
    }

    // Pose une question oui/non
    public static boolean lireOuiNon(String message) {
        System.out.print(message + " (o/n) : ");
        String reponse = scanner.nextLine().trim().toLowerCase();
        return reponse.equals("o") || reponse.equals("oui");
    }

    public static Scanner getScanner() { return scanner; }
}