package exception;

public class PelerinIntrouvableException extends AgenceException {
    public PelerinIntrouvableException(String id) {
        super("Aucun pelerin trouve avec : " + id, "ERR005");
    }
}