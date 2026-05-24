package ui;

import exception.PelerinDejaInscritException;
import exception.ReservationIntrouvableException;
import exception.VoyageCompletException;
import model.personne.Pelerin;
import model.reservation.Reservation;
import model.voyage.Voyage;
import service.PelerinService;
import service.ReservationService;
import service.VoyageService;
import util.ConsoleUtils;

import java.util.List;

public class MenuReservation {

    private ReservationService reservationService;
    private PelerinService     pelerinService;
    private VoyageService      voyageService;

    public MenuReservation(ReservationService rs, PelerinService ps,
                           VoyageService vs) {
        this.reservationService = rs;
        this.pelerinService     = ps;
        this.voyageService      = vs;
    }

    public void afficher() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n -GESTION DES RESERVATIONS: ");
            System.out.println("1. Nouvelle reservation");
            System.out.println("2. Annuler une reservation");
            System.out.println("3. Reservations d'un pelerin");
            System.out.println("4. Reservations d'un voyage");
            System.out.println("0. Retour");

            int choix = ConsoleUtils.lireEntier("Choix : ", 0, 4);
            switch (choix) {
                case 1: nouvelleReservation();       break;
                case 2: annulerReservation();        break;
                case 3: reservationsDePelerin();     break;
                case 4: reservationsDuVoyage();      break;
                case 0: retour = true;               break;
            }
        }
    }

    private void nouvelleReservation() {
        String idPelerin = ConsoleUtils.lireChaine("ID Pelerin : ");
        String idVoyage  = ConsoleUtils.lireChaine("ID Voyage  : ");

        Pelerin pelerin = pelerinService.rechercherParId(idPelerin);
        Voyage  voyage  = voyageService.rechercherParId(idVoyage);

        if (pelerin == null) { System.out.println("Pelerin introuvable."); return; }
        if (voyage  == null) { System.out.println("Voyage introuvable.");  return; }

        try {
            reservationService.creerReservation(pelerin, voyage);
        } catch (VoyageCompletException | PelerinDejaInscritException e) {
            System.out.println("[ERREUR] " + e.getMessage());
        }
    }

    private void annulerReservation() {
        String id = ConsoleUtils.lireChaine("ID Reservation : ");
        try {
            reservationService.annulerReservation(id);
            System.out.println("[OK] Reservation annulee.");
        } catch (ReservationIntrouvableException e) {
            System.out.println("[ERREUR] " + e.getMessage());
        }
    }

    private void reservationsDePelerin() {
        String id = ConsoleUtils.lireChaine("ID Pelerin : ");
        List<Reservation> liste = reservationService.getReservationsDePelerin(id);
        if (liste.isEmpty()) { System.out.println("Aucune reservation."); return; }
        for (Reservation r : liste)
            System.out.println(r.getIdReservation() + " | "
                    + r.getVoyage().getIdVoyage() + " | " + r.getStatut());
    }

    private void reservationsDuVoyage() {
        String id = ConsoleUtils.lireChaine("ID Voyage : ");
        List<Reservation> liste = reservationService.getReservationsDuVoyage(id);
        if (liste.isEmpty()) { System.out.println("Aucune reservation."); return; }
        for (Reservation r : liste)
            System.out.println(r.getIdReservation() + " | "
                    + r.getPelerin().getNomComplet() + " | " + r.getStatut());
    }
}