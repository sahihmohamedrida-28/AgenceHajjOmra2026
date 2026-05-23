package exception;

public class PaiementInsuffisantException extends AgenceException {
    public PaiementInsuffisantException(double montantPaye, double prixTotal) {
        super("Montant paye " + montantPaye + " MAD insuffisant."
                + " Il manque " + (prixTotal - montantPaye) + " MAD.", "ERR004");
    }
}