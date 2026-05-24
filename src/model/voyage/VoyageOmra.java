package model.voyage;

import java.time.LocalDate;
import model.Constantes;
import model.personne.Guide;

public class VoyageOmra extends Voyage {
    private String saisonOmra;
    private int dureeNuits;

    public VoyageOmra(String idVoyage, LocalDate dateDepart, LocalDate dateRetour,
                      double prixParPersonne, int capaciteMax, Guide guide,
                      String saisonOmra, int dureeNuits) {
        super(idVoyage, dateDepart, dateRetour, prixParPersonne, capaciteMax, guide);
        this.saisonOmra = saisonOmra;
        this.dureeNuits = dureeNuits;
    }

    @Override
    public String getTypeVoyage() { return Constantes.TYPE_OMRA; }

    @Override
    public double calculerPrix() {
        double prix = getPrixParPersonne();
        if (Constantes.SAISON_RAMADAN.equalsIgnoreCase(saisonOmra)) {
            prix *= 1.30;
        }
        return prix;
    }

    @Override
    public void afficherDetails() {
        System.out.println("VOYAGE OMRA (" + saisonOmra + ") - ID: " + getIdVoyage());
        System.out.println("Tarif Definitif : " + calculerPrix() + " MAD");
    }

    public String getSaisonOmra() { return saisonOmra; }
    public int getDureeNuits()    { return dureeNuits; }
}