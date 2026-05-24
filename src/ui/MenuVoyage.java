package ui;

import model.voyage.Voyage;
import service.VoyageService;
import util.ConsoleUtils;

import java.util.List;

public class MenuVoyage {

    private VoyageService voyageService;

    public MenuVoyage(VoyageService voyageService) {
        this.voyageService = voyageService;
    }

    public void afficher() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n -GESTION DES VOYAGES:    ");
            System.out.println("1. Afficher tous les voyages");
            System.out.println("2. Afficher voyages disponibles");
            System.out.println("3. Annuler un voyage");
            System.out.println("0. Retour");

            int choix = ConsoleUtils.lireEntier("Choix : ", 0, 3);
            switch (choix) {
                case 1: voyageService.afficherTousVoyages(); break;
                case 2: afficherDisponibles();               break;
                case 3: annulerVoyage();                     break;
                case 0: retour = true;                       break;
            }
        }
    }

    private void afficherDisponibles() {
        List<Voyage> dispo = voyageService.getVoyagesDisponibles();
        if (dispo.isEmpty()) {
            System.out.println("Aucun voyage disponible.");
            return;
        }
        for (Voyage v : dispo) v.afficherDetails();
    }

    private void annulerVoyage() {
        String id = ConsoleUtils.lireChaine("ID du voyage : ");
        try {
            voyageService.annulerVoyage(id);
            System.out.println("[OK] Voyage annule.");
        } catch (exception.VoyageIntrouvableException e) {
            System.out.println("[ERREUR] " + e.getMessage());
        }
    }
}