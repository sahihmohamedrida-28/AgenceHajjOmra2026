package service;

import exception.PelerinDejaInscritException;
import exception.PelerinIntrouvableException;
import model.personne.Pelerin;
import repository.PelerinRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PelerinService {

    private static final String FICHIER = "data/pelerins.csv";
    private List<Pelerin> pelerins;
    private PelerinRepository repository;
    private int compteurId;

    public PelerinService() {
        this.repository = new PelerinRepository();
        this.pelerins   = repository.charger(FICHIER);
        this.compteurId = pelerins.size();
    }

    public Pelerin ajouterPelerin(Pelerin p) throws PelerinDejaInscritException {
        if (rechercherParCin(p.getCin()) != null) {
            throw new PelerinDejaInscritException(p.getCin());
        }
        compteurId++;
        p.setIdPersonne("P" + String.format("%03d", compteurId));
        pelerins.add(p);
        sauvegarder();
        return p;
    }

    public void modifierPelerin(String id, Pelerin nouvelles)
            throws PelerinIntrouvableException {
        Pelerin existant = rechercherParId(id);
        if (existant == null) throw new PelerinIntrouvableException(id);
        existant.setNom(nouvelles.getNom());
        existant.setPrenom(nouvelles.getPrenom());
        existant.setTelephone(nouvelles.getTelephone());
        existant.setEmail(nouvelles.getEmail());
        sauvegarder();
    }

    public void supprimerPelerin(String id) throws PelerinIntrouvableException {
        Pelerin p = rechercherParId(id);
        if (p == null) throw new PelerinIntrouvableException(id);
        pelerins.remove(p);
        sauvegarder();
    }

    public Pelerin rechercherParId(String id) {
        for (Pelerin p : pelerins)
            if (p.getIdPersonne().equals(id)) return p;
        return null;
    }

    public Pelerin rechercherParCin(String cin) {
        for (Pelerin p : pelerins)
            if (p.getCin().equalsIgnoreCase(cin)) return p;
        return null;
    }

    public List<Pelerin> getTousPelerins() { return new ArrayList<>(pelerins); }

    public Map<String, Pelerin> getPelerinsParId() {
        Map<String, Pelerin> map = new HashMap<>();
        for (Pelerin p : pelerins) map.put(p.getIdPersonne(), p);
        return map;
    }

    public void afficherTousPelerins() {
        if (pelerins.isEmpty()) {
            System.out.println("Aucun pelerin enregistre.");
            return;
        }
        for (Pelerin p : pelerins) p.afficherInfos();
    }

    private void sauvegarder() {
        repository.sauvegarder(pelerins, FICHIER);
    }
}