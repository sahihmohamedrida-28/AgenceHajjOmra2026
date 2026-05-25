package model.voyage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Constantes;
import model.personne.Guide;
import model.reservation.Reservation;

public abstract class Voyage {
    private String idVoyage;
    private LocalDate dateDepart;
    private LocalDate dateRetour;
    private double prixParPersonne;
    private int capaciteMax;
    private String statutVoyage;
    private Guide guideAccompagnateur;
    private List<Reservation> listeReservations;

    public Voyage(String idVoyage, LocalDate dateDepart, LocalDate dateRetour,
                  double prixParPersonne, int capaciteMax, Guide guide) {
        this.idVoyage           = idVoyage;
        this.dateDepart         = dateDepart;
        this.dateRetour         = dateRetour;
        this.prixParPersonne    = prixParPersonne;
        this.capaciteMax        = capaciteMax;
        this.guideAccompagnateur = guide;
        this.statutVoyage       = Constantes.STATUT_PLANIFIE;
        this.listeReservations  = new ArrayList<>();
    }

    public abstract String getTypeVoyage();
    public abstract double calculerPrix();
    public abstract void afficherDetails();

    public int getNombrePlacesDisponibles() {
        int activeResa = 0;
        for (Reservation r : listeReservations) {
            if (!Constantes.STATUT_RESA_ANNULEE.equals(r.getStatut())) {
                activeResa++;
            }
        }
        return capaciteMax - activeResa;
    }

    public boolean ajouterReservation(Reservation r) {
        if (getNombrePlacesDisponibles() > 0) {
            this.listeReservations.add(r);
            return true;
        }
        System.out.println("Erreur : Ce voyage a atteint sa capacite maximale.");
        return false;
    }

    public String getIdVoyage()           { return idVoyage; }
    public LocalDate getDateDepart()      { return dateDepart; }
    public LocalDate getDateRetour()      { return dateRetour; }
    public double getPrixParPersonne()    { return prixParPersonne; }
    public int getCapaciteMax()           { return capaciteMax; }
    public String getStatutVoyage()       { return statutVoyage; }
    public void setStatutVoyage(String st){ this.statutVoyage = st; }
    public Guide getGuide()               { return guideAccompagnateur; }
    public List<Reservation> getReservations() { return listeReservations; }
}