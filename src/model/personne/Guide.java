package model.personne;

import java.util.ArrayList;
import java.util.List;

public class Guide extends Personne {
    private List<String> languesParlees;
    private int anneesExperience;

    public Guide(String idPersonne, String nom, String prenom, String cin,
                 String telephone, String email, int anneesExperience) {
        super(idPersonne, nom, prenom, cin, telephone, email);
        this.anneesExperience = anneesExperience;
        this.languesParlees = new ArrayList<>();
    }

    @Override
    public String getRole() { return "GUIDE"; }

    @Override
    public void afficherInfos() {
        System.out.println("Guide Expriment: " + getNomComplet()
                + " (" + anneesExperience + " ans d'exp)");
    }

    public void ajouterLangue(String langue) { this.languesParlees.add(langue); }
    public List<String> getLanguesParlees()  { return languesParlees; }
    public int getAnneesExperience()         { return anneesExperience; }
}