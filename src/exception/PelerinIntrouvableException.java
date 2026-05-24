package exception;

public class PelerinIntrouvableException extends AgenceException {
    public PelerinIntrouvableException(String id) {
        super("Aucun pelerin trouve avec l'identifiant : " + id, "ERR005");
    }
}