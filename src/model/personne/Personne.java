package model.personne;

public abstract class Personne {
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public String getNomComplet() { return nom + " " + prenom; }
    
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
}
