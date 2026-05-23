package repository;

import model.personne.Guide;
import model.voyage.*;
import util.DateUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gere la persistance des Voyages (Hajj et Omra) dans le CSV.
 * Format Hajj : id;HAJJ;dateDepart;dateRetour;prix;capacite;statut;idGuide;anneeHijri;inclusMina;inclusMuzdalifa
 * Format Omra : id;OMRA;dateDepart;dateRetour;prix;capacite;statut;idGuide;dureeNuits;saisonOmra
 *
 * NOTE : Le repository a besoin de la liste des guides (charges avant)
 * pour reconstituer les objets Voyage avec leur Guide associe.
 */
public class VoyageRepository implements IPersistable<Voyage> {

    private static final String SEP = ";";

    // Necessaire pour retrouver l'objet Guide a partir de son ID
    private Map<String, Guide> guidesParId;

    public VoyageRepository(Map<String, Guide> guidesParId) {
        this.guidesParId = guidesParId;
    }

    @Override
    public void sauvegarder(List<Voyage> voyages, String fichier) {

        new File("data").mkdirs();

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fichier, false))) {

            for (Voyage v : voyages) {

                // Construire les 8 champs communs
                StringBuilder sb = new StringBuilder();
                sb.append(v.getIdVoyage())                              .append(SEP)
                        .append(v.getTypeVoyage())                            .append(SEP)
                        .append(DateUtils.formatDate(v.getDateDepart()))      .append(SEP)
                        .append(DateUtils.formatDate(v.getDateRetour()))      .append(SEP)
                        .append(v.getPrixParPersonne())                       .append(SEP)
                        .append(v.getCapaciteMax())                           .append(SEP)
                        .append(v.getStatut())                                .append(SEP)
                        .append(v.getGuide() != null ? v.getGuide().getIdPersonne() : "");

                // Ajouter les champs specifiques selon le type
                if (v instanceof VoyageHajj) {
                    VoyageHajj h = (VoyageHajj) v;
                    sb.append(SEP).append(h.getAnneeHijri())
                            .append(SEP).append(h.isInclusMina())
                            .append(SEP).append(h.isInclusMuzdalifa());

                } else if (v instanceof VoyageOmra) {
                    VoyageOmra o = (VoyageOmra) v;
                    sb.append(SEP).append(o.getDureeNuits())
                            .append(SEP).append(o.getSaisonOmra());
                }

                writer.write(sb.toString());
                writer.newLine();
            }

            System.out.println("[OK] " + voyages.size()
                    + " voyage(s) sauvegardes dans " + fichier);

        } catch (IOException e) {
            System.err.println("[ERREUR] Sauvegarde voyages : " + e.getMessage());
        }
    }

    @Override
    public List<Voyage> charger(String fichier) {

        List<Voyage> voyages = new ArrayList<>();
        File f = new File(fichier);

        if (!f.exists()) {
            System.out.println("[INFO] Fichier " + fichier
                    + " inexistant. Demarrage avec liste vide.");
            return voyages;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {

            String ligne;
            int numLigne = 0;

            while ((ligne = reader.readLine()) != null) {
                numLigne++;

                if (ligne.trim().isEmpty() || ligne.startsWith("#")) continue;

                String[] c = ligne.split(SEP, -1);

                // Il faut au minimum les 8 champs communs
                if (c.length < 8) {
                    System.err.println("[WARN] Ligne " + numLigne
                            + " trop courte, ignoree.");
                    continue;
                }

                try {
                    // Lire les 8 champs communs
                    String id               = c[0];
                    TypeVoyage type         = TypeVoyage.valueOf(c[1]);
                    LocalDate depart        = DateUtils.parseDate(c[2]);
                    LocalDate retour        = DateUtils.parseDate(c[3]);
                    double prix             = Double.parseDouble(c[4]);
                    int capacite            = Integer.parseInt(c[5]);
                    StatutVoyage statut     = StatutVoyage.valueOf(c[6]);
                    Guide guide             = guidesParId.get(c[7]); // peut être null si pas de guide

                    Voyage voyage = null;

                    // Créer le bon type d'objet selon le typeVoyage
                    if (type == TypeVoyage.HAJJ && c.length >= 11) {
                        int anneeHijri        = Integer.parseInt(c[8]);
                        boolean inclusMina    = Boolean.parseBoolean(c[9]);
                        boolean inclusMuzda   = Boolean.parseBoolean(c[10]);

                        voyage = new VoyageHajj(id, depart, retour, prix,
                                capacite, guide,
                                anneeHijri, inclusMina, inclusMuzda);

                    } else if (type == TypeVoyage.OMRA && c.length >= 10) {
                        int dureeNuits        = Integer.parseInt(c[8]);
                        SaisonOmra saison     = SaisonOmra.valueOf(c[9]);

                        voyage = new VoyageOmra(id, depart, retour, prix,
                                capacite, guide,
                                dureeNuits, saison);
                    }

                    // Appliquer le statut (le constructeur met PLANIFIE par défaut)
                    if (voyage != null) {
                        voyage.setStatut(statut);
                        voyages.add(voyage);
                    }

                } catch (Exception e) {
                    System.err.println("[WARN] Erreur ligne " + numLigne
                            + " : " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("[ERREUR] Lecture voyages : " + e.getMessage());
        }

        System.out.println("[OK] " + voyages.size()
                + " voyage(s) charges depuis " + fichier);
        return voyages;
    }
}