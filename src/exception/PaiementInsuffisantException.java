package exception;

public class PaiementInsuffisantException extends AgenceException {
    public PaiementInsuffisantException(double montantPaye, double prixDu) {
        super("Montant paye " + montantPaye + " MAD insuffisant."
                + " Reste : " + (prixDu - montantPaye) + " MAD.", "ERR004");
    }
}