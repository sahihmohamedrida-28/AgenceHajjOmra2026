package ui;

import service.*;
import util.ConsoleUtils;

public class ConsoleMenu {

    private MenuPelerin     menuPelerin;
    private MenuVoyage      menuVoyage;
    private MenuReservation menuReservation;
    private MenuPaiement    menuPaiement;

    public ConsoleMenu(PelerinService ps, VoyageService vs,
                       ReservationService rs, PaiementService pais) {
        this.menuPelerin     = new MenuPelerin(ps);
        this.menuVoyage      = new MenuVoyage(vs);
        this.menuReservation = new MenuReservation(rs, ps, vs);
        this.menuPaiement    = new MenuPaiement(pais, rs);
    }

    public void afficherMenuPrincipal() {
        boolean continuer = true;
        while (continuer) {
            System.out.println("      AGENCE HAJJ & OMRA         ");
            System.out.println("  1. Gestion des Pelerins      ");
            System.out.println("  2. Gestion des Voyages       ");
            System.out.println("  3. Gestion des Reservations  ");
            System.out.println("  4. Gestion des Paiements     ");
            System.out.println("  0. Quitter                   ");
            System.out.println("                                ");

            int choix = ConsoleUtils.lireEntier("Votre choix : ", 0, 4);
            switch (choix) {
                case 1: menuPelerin.afficher();     break;
                case 2: menuVoyage.afficher();      break;
                case 3: menuReservation.afficher(); break;
                case 4: menuPaiement.afficher();    break;
                case 0: continuer = false;          break;
            }
        }
        System.out.println("Au revoir !");
    }
}