package model.reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Constantes;
import model.personne.Pelerin;
import model.voyage.Voyage;
import model.paiement.IPaiement;

public class Reservation {
    private String idReservation;
    private Pelerin pelerinAssocie;
    private Voyage voyageChoisi;
    private LocalDate dateDeReservation;
    private String statutReservation;
    private List<IPaiement> paiementsRealises;

    public Reservation(String idReservation, Pelerin pelerin,
                       Voyage voyage, LocalDate dateResa) {
        this.idReservation = idReservation;
        this.pelerinAssocie = pelerin;
        this.voyageChoisi = voyage;
        this.dateDeReservation = dateResa;
        this.statutReservation = Constantes.STATUT_RESA_EN_ATTENTE;
        this.paiementsRealises = new ArrayList<>();
    }

    public double getMontantTotalPaye() {
        double total = 0.0;
        for (IPaiement p : paiementsRealises) { total += p.getMontant(); }
        return total;
    }

    public double getMontantRestant() {
        return voyageChoisi.calculerPrix() - getMontantTotalPaye();
    }

    public void enregistrerPaiement(IPaiement paiement) {
        this.paiementsRealises.add(paiement);
        if (getMontantRestant() <= 0) {
            this.statutReservation = Constantes.STATUT_RESA_CONFIRMEE;
        }
    }

    public String getIdReservation()        { return idReservation; }
    public Pelerin getPelerin()             { return pelerinAssocie; }
    public Voyage getVoyage()               { return voyageChoisi; }
    public LocalDate getDateReservation()   { return dateDeReservation; }
    public String getStatut()               { return statutReservation; }
    public void setStatut(String st)        { this.statutReservation = st; }
    public List<IPaiement> getPaiements()   { return paiementsRealises; }
}