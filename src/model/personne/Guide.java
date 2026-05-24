package model.personne;

import java.util.List;
import java.util.ArrayList;

public class Guide extends Personne {

    private List<String> languesParlees;
    private int anneesExperience;
    private boolean disponible;

    public Guide(String idPersonne, String nom, String prenom,
                 String cin, String telephone, String email,
                 List<String> languesParlees, int anneesExperience,
                 boolean disponible) {
        super(idPersonne, nom, prenom, cin, telephone, email);
        this.languesParlees = languesParlees != null
                ? languesParlees : new ArrayList<>();
        this.anneesExperience = anneesExperience;
        this.disponible = disponible;
    }

    @Override
    public String getRole() { return "GUIDE"; }

    @Override
    public void afficherInfos() {
        System.out.println("========== GUIDE ==========");
        System.out.println("ID : " + getIdPersonne());
        System.out.println("Nom complet : " + getNomComplet());
        System.out.println("Langues : " + String.join(", ", languesParlees));
        System.out.println("Experience : " + anneesExperience + " ans");
        System.out.println("Telephone : " + getTelephone());
        System.out.println("Disponible : " + (disponible ? "Oui" : "Non"));
        System.out.println("===========================");
    }

    public boolean parleLangue(String langue) {
        for (String l : languesParlees) {
            if (l.equalsIgnoreCase(langue)) return true;
        }
        return false;
    }

    // Getters et Setters
    public List<String> getLanguesParlees() { return languesParlees; }
    public void setLanguesParlees(List<String> l) { this.languesParlees = l; }
    public int getAnneesExperience() { return anneesExperience; }
    public void setAnneesExperience(int n) { this.anneesExperience = n; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean d) { this.disponible = d; }
}