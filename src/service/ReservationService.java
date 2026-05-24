package service;

import exception.PelerinDejaInscritException;
import exception.ReservationIntrouvableException;
import exception.VoyageCompletException;
import model.Constantes;
import model.personne.Pelerin;
import model.reservation.Reservation;
import model.voyage.Voyage;
import repository.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationService {

    private static final String FICHIER = "data/reservations.csv";
    private List<Reservation> reservations;
    private ReservationRepository repository;
    private int compteurId;

    public ReservationService(Map<String, Pelerin> pelerinsParId,
                              Map<String, Voyage> voyagesParId) {
        this.repository   = new ReservationRepository(pelerinsParId, voyagesParId);
        this.reservations = repository.charger(FICHIER);
        this.compteurId   = reservations.size();
    }

    public Reservation creerReservation(Pelerin pelerin, Voyage voyage)
            throws VoyageCompletException, PelerinDejaInscritException {

        if (voyage.getNombrePlacesDisponibles() <= 0)
            throw new VoyageCompletException(voyage.getIdVoyage());

        // Verifier si pelerin deja inscrit a ce voyage
        for (Reservation r : reservations) {
            if (r.getPelerin().getIdPersonne().equals(pelerin.getIdPersonne())
                    && r.getVoyage().getIdVoyage().equals(voyage.getIdVoyage())
                    && !Constantes.STATUT_RESA_ANNULEE.equals(r.getStatut())) {
                throw new PelerinDejaInscritException(pelerin.getCin());
            }
        }

        compteurId++;
        String idResa = "R" + String.format("%03d", compteurId);
        Reservation r = new Reservation(idResa, pelerin, voyage, LocalDate.now());
        r.setStatut(Constantes.STATUT_RESA_CONFIRMEE);
        reservations.add(r);
        voyage.ajouterReservation(r);
        sauvegarder();
        System.out.println("[OK] Reservation creee : " + idResa);
        return r;
    }

    public void annulerReservation(String id) throws ReservationIntrouvableException {
        Reservation r = rechercherParId(id);
        if (r == null) throw new ReservationIntrouvableException(id);
        r.setStatut(Constantes.STATUT_RESA_ANNULEE);
        sauvegarder();
    }

    public Reservation rechercherParId(String id) {
        for (Reservation r : reservations)
            if (r.getIdReservation().equals(id)) return r;
        return null;
    }

    public List<Reservation> getReservationsDePelerin(String idPelerin) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations)
            if (r.getPelerin().getIdPersonne().equals(idPelerin)) result.add(r);
        return result;
    }

    public List<Reservation> getReservationsDuVoyage(String idVoyage) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations)
            if (r.getVoyage().getIdVoyage().equals(idVoyage)) result.add(r);
        return result;
    }

    public List<Reservation> getToutesReservations() {
        return new ArrayList<>(reservations);
    }

    private void sauvegarder() {
        repository.sauvegarder(reservations, FICHIER);
    }
}