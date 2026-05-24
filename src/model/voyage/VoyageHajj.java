package model.voyage;

import model.Constantes;
import java.time.LocalDate;
import model.personne.Guide;

public class VoyageHajj extends Voyage {
    private boolean tentesMinaIncluses;
    private boolean transportMuzdalifaInclus;

    public VoyageHajj(String idVoyage, LocalDate dateDepart, LocalDate dateRetour,
                      double prixParPersonne, int capaciteMax, Guide guide,
                      boolean tentesMinaIncluses, boolean transportMuzdalifaInclus) {
        super(idVoyage, dateDepart, dateRetour, prixParPersonne, capaciteMax, guide);
        this.tentesMinaIncluses = tentesMinaIncluses;
        this.transportMuzdalifaInclus = transportMuzdalifaInclus;
    }

    @Override
    public String getTypeVoyage() {
        return Constantes.TYPE_HAJJ;
    }

    @Override
    public double calculerPrix() {
        double prix = getPrixParPersonne();
        if (tentesMinaIncluses) prix += 1500.0;
        if (transportMuzdalifaInclus) prix += 500.0;
        return prix;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Voyage Hajj: " + calculerPrix() + " MAD");
    }
}
