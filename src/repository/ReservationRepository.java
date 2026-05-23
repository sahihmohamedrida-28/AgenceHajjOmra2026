package repository;

import model.personne.Pelerin;
import model.reservation.Reservation;
import model.reservation.StatutReservation;
import model.voyage.Voyage;
import util.DateUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gere la persistance des Reservations.
 * Format CSV : id;idPelerin;idVoyage;dateReservation;montantPaye;statut
 *
 * IMPORTANT : A besoin des maps de pelerins et de voyages deja charges.
 */
public class ReservationRepository implements IPersistable<Reservation> {

    private static final String SEP = ";";

    // Maps necessaires pour retrouver les objets a partir de leurs IDs
    private Map<String, Pelerin> pelerinsParId;
    private Map<String, Voyage>  voyagesParId;

    public ReservationRepository(Map<String, Pelerin> pelerinsParId,
                                 Map<String, Voyage>  voyagesParId) {
        this.pelerinsParId = pelerinsParId;
        this.voyagesParId  = voyagesParId;
    }

    @Override
    public void sauvegarder(List<Reservation> reservations, String fichier) {

        new File("data").mkdirs();

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fichier, false))) {

            for (Reservation r : reservations) {

                String ligne = String.join(SEP,
                        r.getIdReservation(),
                        r.getPelerin().getIdPersonne(), // objet → son ID
                        r.getVoyage().getIdVoyage(),    // objet → son ID
                        DateUtils.formatDate(r.getDateReservation()),
                        String.valueOf(r.getMontantPaye()),
                        r.getStatut().toString()
                );

                writer.write(ligne);
                writer.newLine();
            }

            System.out.println("[OK] " + reservations.size()
                    + " reservation(s) sauvegardees dans " + fichier);

        } catch (IOException e) {
            System.err.println("[ERREUR] Sauvegarde reservations : " + e.getMessage());
        }
    }

    @Override
    public List<Reservation> charger(String fichier) {

        List<Reservation> reservations = new ArrayList<>();
        File f = new File(fichier);

        if (!f.exists()) {
            System.out.println("[INFO] Fichier " + fichier
                    + " inexistant. Demarrage avec liste vide.");
            return reservations;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {

            String ligne;
            int numLigne = 0;

            while ((ligne = reader.readLine()) != null) {
                numLigne++;

                if (ligne.trim().isEmpty() || ligne.startsWith("#")) continue;

                String[] c = ligne.split(SEP, -1);

                if (c.length != 6) {
                    System.err.println("[WARN] Ligne " + numLigne
                            + " mal formee, ignoree.");
                    continue;
                }

                try {
                    String idResa  = c[0];

                    // Retrouver les objets via leurs IDs dans les maps
                    Pelerin pelerin = pelerinsParId.get(c[1]);
                    Voyage  voyage  = voyagesParId.get(c[2]);

                    // Si un des deux est introuvable, on ignore la ligne
                    if (pelerin == null || voyage == null) {
                        System.err.println("[WARN] Pelerin ou voyage introuvable"
                                + ", ligne " + numLigne + " ignoree.");
                        continue;
                    }

                    LocalDate dateResa        = DateUtils.parseDate(c[3]);
                    double montant            = Double.parseDouble(c[4]);
                    StatutReservation statut  = StatutReservation.valueOf(c[5]);

                    Reservation r = new Reservation(idResa, pelerin,
                            voyage, dateResa, montant);

                    // Même raison que pour Voyage : le constructeur
                    // met EN_ATTENTE par défaut, on rétablit le vrai statut
                    r.setStatut(statut);

                    reservations.add(r);

                } catch (Exception e) {
                    System.err.println("[WARN] Erreur ligne " + numLigne
                            + " : " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("[ERREUR] Lecture reservations : " + e.getMessage());
        }

        System.out.println("[OK] " + reservations.size()
                + " reservation(s) chargees depuis " + fichier);
        return reservations;
    }
}