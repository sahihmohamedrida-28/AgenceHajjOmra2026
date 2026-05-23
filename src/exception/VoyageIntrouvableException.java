package exception;

public class VoyageIntrouvableException extends AgenceException {
    public VoyageIntrouvableException(String idVoyage) {
        super("Aucun voyage trouve avec l'ID : " + idVoyage, "ERR006");
    }
}