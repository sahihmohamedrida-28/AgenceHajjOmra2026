package ui;

import service.PaiementService;
import service.ReservationService;
import util.ConsoleUtils;

public class MenuPaiement {

    private PaiementService    paiementService;
    private ReservationService reservationService;

    public MenuPaiement(PaiementService ps, ReservationService rs) {
        this.paiementService    = ps;
        this.reservationService = rs;
    }

    public void afficher() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n -GESTION DES PAIEMENTS: ");
            System.out.println("1. Payer par virement");
            System.out.println("2. Payer en especes");
            System.out.println("3. Voir paiements d'une reservation");
            System.out.println("0. Retour");

            int choix = ConsoleUtils.lireEntier("Choix : ", 0, 3);
            switch (choix) {
                case 1: payerVirement(); break;
                case 2: payerEspeces();  break;
                case 3: voirPaiements(); break;
                case 0: retour = true;   break;
            }
        }
    }

    private void payerVirement() {
        String id      = ConsoleUtils.lireChaine("ID Reservation : ");
        double montant = ConsoleUtils.lireDouble("Montant (MAD)  : ");
        String banque  = ConsoleUtils.lireChaine("Banque         : ");
        String ref     = ConsoleUtils.lireChaine("Reference      : ");
        paiementService.payerParVirement(id, montant, banque, ref);
    }

    private void payerEspeces() {
        String id      = ConsoleUtils.lireChaine("ID Reservation : ");
        double montant = ConsoleUtils.lireDouble("Montant (MAD)  : ");
        String caisse  = ConsoleUtils.lireChaine("Ref. Caisse    : ");
        paiementService.payerParEspeces(id, montant, caisse);
    }

    private void voirPaiements() {
        String id = ConsoleUtils.lireChaine("ID Reservation : ");
        paiementService.afficherPaiements(id);
    }
}