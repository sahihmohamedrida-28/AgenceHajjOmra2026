package model.paiement;

import java.time.LocalDate;
import model.Constantes;

public class PaiementEspeces implements IPaiement {
    private String idPaiement;
    private double montant;
    private LocalDate datePaiement;
    private String referenceCaisse;

    public PaiementEspeces(String id, double m, LocalDate d, String refCaisse) {
        this.idPaiement = id;
        this.montant = m;
        this.datePaiement = d;
        this.referenceCaisse = refCaisse;
    }

    @Override public String getIdPaiement()      { return idPaiement; }
    @Override public double getMontant()          { return montant; }
    @Override public LocalDate getDatePaiement()  { return datePaiement; }
    @Override public String getModePaiement()     { return Constantes.PAIEMENT_ESPECES; }

    @Override
    public void genererRecu() {
        System.out.println("[RECU ESPECES] ID: " + idPaiement
                + " | Mnt: " + montant + " MAD");
    }
}