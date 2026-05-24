package model.voyage;

import model.Constantes;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import model.personne.Guide;
import model.reservation.Reservation;

public abstract class Voyage {
    private String idVoyage;
    private LocalDate dateDepart;
    private LocalDate dateRetour;
    private double prixParPersonne;
    private int capaciteMax;
    private String statut;
    private Guide guide;
    private List<Reservation> reservations;
    private String typeChambre;
    private boolean dejeunerInclus;
    private boolean transportHaramInclus;

    public Voyage(String idVoyage, LocalDate dateDepart, LocalDate dateRetour,
                  double prixParPersonne, int capaciteMax, Guide guide) {
        this.idVoyage = idVoyage;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.prixParPersonne = prixParPersonne;
        this.capaciteMax = capaciteMax;
        this.guide = guide;
        this.statut = Constantes.STATUT_VOYAGE_PLANIFIE;
        this.reservations = new ArrayList<>();
        this.typeChambre = Constantes.CHAMBRE_DOUBLE;
        this.dejeunerInclus = false;
        this.transportHaramInclus = false;
    }

    public abstract String getTypeVoyage();
    public abstract double calculerPrix();
    public abstract void afficherDetails();

    public int getNombrePlacesDisponibles() {
        long actives = reservations.stream()
            .filter(r -> !Constantes.STATUT_RESA_ANNULEE.equals(r.getStatut()))
            .count();
        return capaciteMax - (int) actives;
    }

    public void ajouterReservation(Reservation r) {
        reservations.add(r);
    }
    
    public void setTypeChambre(String typeChambre) { this.typeChambre = typeChambre; }
    public String getTypeChambre() { return typeChambre; }
    public void setDejeunerInclus(boolean dejeunerInclus) { this.dejeunerInclus = dejeunerInclus; }
    public boolean isDejeunerInclus() { return dejeunerInclus; }
    public void setTransportHaramInclus(boolean transportHaramInclus) { this.transportHaramInclus = transportHaramInclus; }
    public boolean isTransportHaramInclus() { return transportHaramInclus; }
    public double getPrixParPersonne() { return prixParPersonne; }
}
