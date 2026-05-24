package model.personne;

public abstract class Personne {

    private String idPersonne;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String email;

    public Personne(String idPersonne, String nom, String prenom,
                    String cin, String telephone, String email) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
    }

    // Methodes abstraites : chaque sous-classe doit les implementer
    public abstract String getRole();
    public abstract void afficherInfos();

    // Methode concrete partagee par toutes les sous-classes
    public String getNomComplet() {
        return prenom + " " + nom.toUpperCase();
    }

    // Getters et Setters
    public String getIdPersonne() { return idPersonne; }
    public void setIdPersonne(String id) { this.idPersonne = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String tel) { this.telephone = tel; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + getNomComplet()
                + " | CIN: " + cin + " | Tel: " + telephone;
    }
}