package model.voyage;

import model.Constantes;
import java.time.LocalDate;
import model.personne.Guide;

public class VoyageOmra extends Voyage {
    private String saison;

    public VoyageOmra(String idVoyage, LocalDate dateDepart, LocalDate dateRetour,
                      double prixParPersonne, int capaciteMax, Guide guide,
                      String saison) {
        super(idVoyage, dateDepart, dateRetour, prixParPersonne, capaciteMax, guide);
        this.saison = saison;
    }

    @Override
    public String getTypeVoyage() {
        return Constantes.TYPE_OMRA;
    }

    @Override
    public double calculerPrix() {
        double prix = getPrixParPersonne();
        if (Constantes.SAISON_RAMADAN.equals(saison)) {
            prix *= 1.30;
        }
        return prix;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Voyage Omra: " + calculerPrix() + " MAD");
    }
}
