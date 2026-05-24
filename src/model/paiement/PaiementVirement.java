package model.paiement;

import java.time.LocalDate;
import model.Constantes;

public class PaiementVirement implements IPaiement {
    private String idPaiement;
    private double montant;
    private LocalDate datePaiement;
    private String banqueEmettrice;
    private String referenceTransaction;

    public PaiementVirement(String id, double m, LocalDate d,
                            String bq, String ref) {
        this.idPaiement = id;
        this.montant = m;
        this.datePaiement = d;
        this.banqueEmettrice = bq;
        this.referenceTransaction = ref;
    }

    @Override public String getIdPaiement()      { return idPaiement; }
    @Override public double getMontant()          { return montant; }
    @Override public LocalDate getDatePaiement()  { return datePaiement; }
    @Override public String getModePaiement()     { return Constantes.PAIEMENT_VIREMENT; }

    @Override
    public void genererRecu() {
        System.out.println("[RECU VIREMENT] ID: " + idPaiement
                + " | Mnt: " + montant + " MAD");
    }
}