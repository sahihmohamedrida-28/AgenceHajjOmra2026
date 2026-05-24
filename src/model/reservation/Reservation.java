package model.reservation;

import model.Constantes;
import model.personne.Pelerin;
import model.voyage.Voyage;
import model.paiement.IPaiement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String idReservation;
    private LocalDate dateReservation;
    private String statut;
    private Voyage voyage;
    private Pelerin pelerin;
    private List<IPaiement> paiements;

    public Reservation(String idReservation, Voyage voyage, Pelerin pelerin) {
        this.idReservation = idReservation;
        this.dateReservation = LocalDate.now();
        this.statut = Constantes.STATUT_RESA_EN_ATTENTE;
        this.voyage = voyage;
        this.pelerin = pelerin;
        this.paiements = new ArrayList<>();
    }

    public void ajouterPaiement(IPaiement paiement) {
        this.paiements.add(paiement);
    }

    public double getMontantTotalPaye() {
        return paiements.stream().mapToDouble(IPaiement::getMontant).sum();
    }

    public double getMontantRestant() {
        return voyage.calculerPrix() - getMontantTotalPaye();
    }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    
    public void afficherDetails() {
        System.out.println("Reservation ID: " + idReservation + ", Statut: " + statut);
    }
}
