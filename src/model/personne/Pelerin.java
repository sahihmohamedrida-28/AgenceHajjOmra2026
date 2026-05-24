package model.personne;

public class Pelerin extends Personne {
    private String numPasseport;

    public Pelerin(String nom, String prenom, String numPasseport) {
        super(nom, prenom);
        this.numPasseport = numPasseport;
    }
    public String getNumPasseport() { return numPasseport; }
}
