package exception;

public class VoyageCompletException extends AgenceException {
    public VoyageCompletException(String idVoyage) {
        super("Le voyage " + idVoyage + " est complet.", "ERR002");
    }
}