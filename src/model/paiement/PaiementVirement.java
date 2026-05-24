package model.paiement;

import model.Constantes;
import java.time.LocalDate;

public class PaiementVirement implements IPaiement {
    private String idPaiement;
    private double montant;
    private LocalDate datePaiement;
    private String referenceCompte;

    public PaiementVirement(String idPaiement, double montant, LocalDate datePaiement, String referenceCompte) {
        this.idPaiement = idPaiement;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.referenceCompte = referenceCompte;
    }

    @Override
    public String getIdPaiement() { return idPaiement; }
    @Override
    public double getMontant() { return montant; }
    @Override
    public LocalDate getDatePaiement() { return datePaiement; }
    @Override
    public String getModePaiement() { return Constantes.PAIEMENT_VIREMENT; }

    @Override
    public void genererRecu() {
        System.out.println("Recu Virement: " + montant + " MAD (Compte: " + referenceCompte + ")");
    }
}
