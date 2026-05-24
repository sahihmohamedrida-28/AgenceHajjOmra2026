package model.paiement;

import model.Constantes;
import java.time.LocalDate;

public interface IPaiement {
    String getIdPaiement();
    double getMontant();
    LocalDate getDatePaiement();
    String getModePaiement();
    void genererRecu();
}
