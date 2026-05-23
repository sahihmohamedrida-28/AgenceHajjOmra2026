package model.personne;

import java.time.LocalDate;
import java.time.Period;

public class Pelerin extends Personne {

    private String    passeport;
    private String    nationalite;
    private LocalDate dateNaissance;
    private boolean   aDejaFaitHajj;

    public Pelerin(String idPersonne, String nom, String prenom,
                   String cin, String telephone, String email,
                   String passeport, String nationalite,
                   LocalDate dateNaissance, boolean aDejaFaitHajj) {
        super(idPersonne, nom, prenom, cin, telephone, email);
        this.passeport      = passeport;
        this.nationalite    = nationalite;
        this.dateNaissance  = dateNaissance;
        this.aDejaFaitHajj  = aDejaFaitHajj;
    }

    @Override
    public String getRole() {
        return "PELERIN";
    }

    @Override
    public void afficherInfos() {
        System.out.println("Les infos du pelerin: ");
        System.out.println("ID          : " + getIdPersonne());
        System.out.println("Nom complet : " + getNomComplet());
        System.out.println("CIN         : " + getCin());
        System.out.println("Passeport   : " + passeport);
        System.out.println("Nationalite : " + nationalite);
        System.out.println("Age         : " + calculerAge() + " ans");
        System.out.println("Telephone   : " + getTelephone());
        System.out.println("Email       : " + getEmail());
        System.out.println("Deja Hajj   : " + (aDejaFaitHajj ? "Oui" : "Non"));
        System.out.println(" ");
    }

    public int calculerAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    // Regle metier : un pelerin qui n'a pas fait le Hajj est eligible,il a une priorité
    public boolean estEligibleHajj() {
        return !aDejaFaitHajj;
    }

    // Getters
    public String    getPasseport()    { return passeport; }
    public String    getNationalite()  { return nationalite; }
    public LocalDate getDateNaissance(){ return dateNaissance; }
    public boolean   isADejaFaitHajj() { return aDejaFaitHajj; }

    // Setters
    public void setPasseport(String passeport)         { this.passeport = passeport; }
    public void setNationalite(String nationalite)     { this.nationalite = nationalite; }
    public void setDateNaissance(LocalDate d)          { this.dateNaissance = d; }
    public void setADejaFaitHajj(boolean aDejaFaitHajj){ this.aDejaFaitHajj = aDejaFaitHajj; }
}