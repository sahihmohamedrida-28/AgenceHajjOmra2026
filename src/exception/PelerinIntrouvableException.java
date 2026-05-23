package exception;

public class PelerinIntrouvableException extends AgenceException {
    public PelerinIntrouvableException(String identifiant) {
        super("Aucun pelerin trouve avec : " + identifiant, "ERR005");
    }
}