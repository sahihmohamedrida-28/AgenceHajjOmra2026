package service;

import exception.VoyageIntrouvableException;
import model.Constantes;
import model.personne.Guide;
import model.voyage.Voyage;
import repository.VoyageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoyageService {

    private static final String FICHIER = "data/voyages.csv";
    private List<Voyage> voyages;
    private VoyageRepository repository;
    private int compteurId;

    public VoyageService(Map<String, Guide> guidesParId) {
        this.repository = new VoyageRepository(guidesParId);
        this.voyages    = repository.charger(FICHIER);
        this.compteurId = voyages.size();
    }

    public Voyage ajouterVoyage(Voyage v) {
        compteurId++;
        voyages.add(v);
        sauvegarder();
        return v;
    }

    public void annulerVoyage(String id) throws VoyageIntrouvableException {
        Voyage v = rechercherParId(id);
        if (v == null) throw new VoyageIntrouvableException(id);
        v.setStatutVoyage(Constantes.STATUT_ANNULE);
        sauvegarder();
    }

    public Voyage rechercherParId(String id) {
        for (Voyage v : voyages)
            if (v.getIdVoyage().equals(id)) return v;
        return null;
    }

    public List<Voyage> getVoyagesDisponibles() {
        List<Voyage> dispo = new ArrayList<>();
        for (Voyage v : voyages)
            if (v.getNombrePlacesDisponibles() > 0
                    && Constantes.STATUT_PLANIFIE.equals(v.getStatutVoyage()))
                dispo.add(v);
        return dispo;
    }

    public List<Voyage> getTousVoyages() { return new ArrayList<>(voyages); }

    public Map<String, Voyage> getVoyagesParId() {
        Map<String, Voyage> map = new HashMap<>();
        for (Voyage v : voyages) map.put(v.getIdVoyage(), v);
        return map;
    }

    public void afficherTousVoyages() {
        if (voyages.isEmpty()) {
            System.out.println("Aucun voyage enregistre.");
            return;
        }
        for (Voyage v : voyages) v.afficherDetails();
    }

    private void sauvegarder() {
        repository.sauvegarder(voyages, FICHIER);
    }
}