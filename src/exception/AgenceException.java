package exception;

public abstract class AgenceException extends Exception {

    private String codeErreur;

    public AgenceException(String message, String codeErreur) {
        super(message);
        this.codeErreur = codeErreur;
    }

    public String getCodeErreur() { return codeErreur; }

    public void afficherErreur() {
        System.out.println("[ERREUR " + codeErreur + "] " + getMessage());
    }
}