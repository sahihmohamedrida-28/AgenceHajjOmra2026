package exception;

public class PelerinDejaInscritException extends AgenceException {
    public PelerinDejaInscritException(String cin) {
        super("Un pelerin avec le CIN " + cin + " existe deja.", "ERR001");
    }
}