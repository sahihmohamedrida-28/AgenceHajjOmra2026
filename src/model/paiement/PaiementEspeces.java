package model.paiement;

import model.Constantes;
import java.time.LocalDate;

public class PaiementEspeces implements IPaiement {
    private String idPaiement;
    private double montant;
    private LocalDate datePaiement;
    private String referenceCaisse;

    public PaiementEspeces(String idPaiement, double montant, LocalDate datePaiement, String referenceCaisse) {
        this.idPaiement = idPaiement;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.referenceCaisse = referenceCaisse;
    }

    @Override
    public String getIdPaiement() { return idPaiement; }
    @Override
    public double getMontant() { return montant; }
    @Override
    public LocalDate getDatePaiement() { return datePaiement; }
    @Override
    public String getModePaiement() { return Constantes.PAIEMENT_ESPECES; }

    @Override
    public void genererRecu() {
        System.out.println("Recu Especes: " + montant + " MAD (Caisse: " + referenceCaisse + ")");
    }
}
