package model.personne;

import java.time.LocalDate;
import java.time.Period;

public class Pelerin extends Personne {
    private String passeport;
    private String nationalite;
    private LocalDate dateNaissance;
    private boolean aDejaFaitHajj;

    public Pelerin(String idPersonne, String nom, String prenom, String cin,
                   String telephone, String email, String passeport,
                   String nationalite, LocalDate dateNaissance, boolean aDejaFaitHajj) {
        super(idPersonne, nom, prenom, cin, telephone, email);
        this.passeport = passeport;
        this.nationalite = nationalite;
        this.dateNaissance = dateNaissance;
        this.aDejaFaitHajj = aDejaFaitHajj;
    }

    @Override
    public String getRole() { return "PELERIN"; }

    @Override
    public void afficherInfos() {
        System.out.println("Pelerin: " + getNomComplet() + " | Pass: " + passeport);
    }

    public int calculerAge() {
        if (dateNaissance == null) return 0;
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public boolean estEligibleHajj() { return !aDejaFaitHajj; }
    public String getPasseport()     { return passeport; }
    public String getNationalite()   { return nationalite; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public boolean isADejaFaitHajj() { return aDejaFaitHajj; }
}