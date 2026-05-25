package service;

import model.paiement.IPaiement;
import model.paiement.PaiementEspeces;
import model.paiement.PaiementVirement;
import model.reservation.Reservation;

import java.time.LocalDate;

public class PaiementService {

    private ReservationService reservationService;
    private int compteurId;

    public PaiementService(ReservationService reservationService) {
        this.reservationService = reservationService;
        this.compteurId = 0;
    }

    public void payerParVirement(String idReservation, double montant,
                                 String banque, String reference) {
        Reservation r = reservationService.rechercherParId(idReservation);
        if (r == null) {
            System.out.println("[ERREUR] Reservation introuvable : " + idReservation);
            return;
        }
        compteurId++;
        String idPaiement = "PAI" + String.format("%03d", compteurId);
        IPaiement paiement = new PaiementVirement(
                idPaiement, montant, LocalDate.now(), banque, reference);
        r.enregistrerPaiement(paiement);
        paiement.genererRecu();
    }

    public void payerParEspeces(String idReservation, double montant,
                                String refCaisse) {
        Reservation r = reservationService.rechercherParId(idReservation);
        if (r == null) {
            System.out.println("[ERREUR] Reservation introuvable : " + idReservation);
            return;
        }
        compteurId++;
        String idPaiement = "PAI" + String.format("%03d", compteurId);
        IPaiement paiement = new PaiementEspeces(
                idPaiement, montant, LocalDate.now(), refCaisse);
        r.enregistrerPaiement(paiement);
        paiement.genererRecu();
    }

    public void afficherPaiements(String idReservation) {
        Reservation r = reservationService.rechercherParId(idReservation);
        if (r == null) {
            System.out.println("[ERREUR] Reservation introuvable.");
            return;
        }
        System.out.println(" -Paiements de " + idReservation + " : ");
        for (IPaiement p : r.getPaiements()) {
            System.out.println("  " + p.getModePaiement()
                    + " | " + p.getMontant() + " MAD | " + p.getDatePaiement());
        }
        System.out.println("Total paye   : " + r.getMontantTotalPaye() + " MAD");
        System.out.println("Reste a payer: " + r.getMontantRestant() + " MAD");
    }
}