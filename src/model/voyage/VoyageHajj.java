package model.voyage;

import java.time.LocalDate;
import model.Constantes;
import model.personne.Guide;

public class VoyageHajj extends Voyage {
    private int anneeHijri;
    private boolean tentesMinaIncluses;
    private boolean transportMuzdalifaInclus;

    public VoyageHajj(String idVoyage, LocalDate dateDepart, LocalDate dateRetour,
                      double prixParPersonne, int capaciteMax, Guide guide,
                      int anneeHijri, boolean mina, boolean muzdalifa) {
        super(idVoyage, dateDepart, dateRetour, prixParPersonne, capaciteMax, guide);
        this.anneeHijri = anneeHijri;
        this.tentesMinaIncluses = mina;
        this.transportMuzdalifaInclus = muzdalifa;
    }

    @Override
    public String getTypeVoyage() { return Constantes.TYPE_HAJJ; }

    @Override
    public double calculerPrix() {
        double total = getPrixParPersonne();
        if (tentesMinaIncluses) total += 1500.00;
        if (transportMuzdalifaInclus) total += 1000.00;
        return total;
    }

    @Override
    public void afficherDetails() {
        System.out.println("VOYAGE HAJJ [" + anneeHijri + " H] - ID: " + getIdVoyage());
        System.out.println("Prix Final : " + calculerPrix() + " MAD");
    }

    public int getAnneeHijri()          { return anneeHijri; }
    public boolean isInclusMina()       { return tentesMinaIncluses; }
    public boolean isInclusMuzdalifa()  { return transportMuzdalifaInclus; }
}