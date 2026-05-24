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
        this.nom        = nom;
        this.prenom     = prenom;
        this.cin        = cin;
        this.telephone  = telephone;
        this.email      = email;
    }

    public abstract String getRole();
    public abstract void afficherInfos();

    public String getNomComplet() {
        return prenom + " " + nom.toUpperCase();
    }


    public String getIdPersonne() { return idPersonne; }
    public String getNom()        { return nom; }
    public String getPrenom()     { return prenom; }
    public String getCin()        { return cin; }
    public String getTelephone()  { return telephone; }
    public String getEmail()      { return email; }

    public void setIdPersonne(String idPersonne) { this.idPersonne = idPersonne; }
    public void setNom(String nom)               { this.nom = nom; }
    public void setPrenom(String prenom)         { this.prenom = prenom; }
    public void setCin(String cin)               { this.cin = cin; }
    public void setTelephone(String telephone)   { this.telephone = telephone; }
    public void setEmail(String email)           { this.email = email; }
}