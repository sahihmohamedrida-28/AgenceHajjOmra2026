package repository;

import model.personne.Guide;
import model.voyage.*;
import util.DateUtils;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VoyageRepository implements IPersistable<Voyage> {
    private static final String SEP = ";";
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
                StringBuilder sb = new StringBuilder();
                sb.append(v.getIdVoyage()).append(SEP)
                        .append(v.getTypeVoyage()).append(SEP)
                        .append(DateUtils.formatDate(v.getDateDepart())).append(SEP)
                        .append(DateUtils.formatDate(v.getDateRetour())).append(SEP)
                        .append(v.getPrixParPersonne()).append(SEP)
                        .append(v.getCapaciteMax()).append(SEP)
                        .append(v.getStatutVoyage()).append(SEP)
                        .append(v.getGuide() != null
                                ? v.getGuide().getIdPersonne() : "");

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
        } catch (IOException e) {
            System.err.println("[ERREUR] Sauvegarde voyages : " + e.getMessage());
        }
    }

    @Override
    public List<Voyage> charger(String fichier) {
        List<Voyage> voyages = new ArrayList<>();
        File f = new File(fichier);
        if (!f.exists()) return voyages;

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String ligne;
            int numLigne = 0;
            while ((ligne = reader.readLine()) != null) {
                numLigne++;
                if (ligne.trim().isEmpty() || ligne.startsWith("#")) continue;
                String[] c = ligne.split(SEP, -1);
                if (c.length < 8) continue;

                try {
                    String id       = c[0];
                    String type     = c[1];
                    LocalDate depart = DateUtils.parseDate(c[2]);
                    LocalDate retour = DateUtils.parseDate(c[3]);
                    double prix     = Double.parseDouble(c[4]);
                    int capacite    = Integer.parseInt(c[5]);
                    String statut   = c[6];
                    Guide guide     = guidesParId.get(c[7]);

                    Voyage voyage = null;
                    if ("HAJJ".equals(type) && c.length >= 11) {
                        voyage = new VoyageHajj(id, depart, retour, prix,
                                capacite, guide,
                                Integer.parseInt(c[8]),
                                Boolean.parseBoolean(c[9]),
                                Boolean.parseBoolean(c[10]));
                    } else if ("OMRA".equals(type) && c.length >= 10) {
                        voyage = new VoyageOmra(id, depart, retour, prix,
                                capacite, guide, c[9],
                                Integer.parseInt(c[8]));
                    }

                    if (voyage != null) {
                        voyage.setStatutVoyage(statut);
                        voyages.add(voyage);
                    }
                } catch (Exception e) {
                    System.err.println("[WARN] Erreur ligne "
                            + numLigne + " : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("[ERREUR] Lecture voyages : " + e.getMessage());
        }
        return voyages;
    }
}