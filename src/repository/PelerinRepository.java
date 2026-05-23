package repository;

import model.personne.Pelerin;
import util.DateUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Gere la persistance des Pelerins dans le fichier CSV.
 * Format CSV : id;nom;prenom;cin;telephone;email;passeport;nationalite;
 *              dateNaissance;aDejaFaitHajj
 * Concept Java : BufferedReader, BufferedWriter, IOException.
 */
public class PelerinRepository implements IPersistable<Pelerin> {

    // Separateur CSV utilise dans tout le projet
    private static final String SEP = ";";

    @Override
    public void sauvegarder(List<Pelerin> pelerins, String fichier) {

        // Cree le dossier "data/" s'il n'existe pas
        new File("data").mkdirs();

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fichier, false))) { // false = écrase le fichier existant

            for (Pelerin p : pelerins) {

                // Construit la ligne CSV avec String.join
                String ligne = String.join(SEP,
                        p.getIdPersonne(),
                        p.getNom(),
                        p.getPrenom(),
                        p.getCin(),
                        p.getTelephone(),
                        p.getEmail(),
                        p.getPasseport(),
                        p.getNationalite(),
                        DateUtils.formatDate(p.getDateNaissance()), // LocalDate → String
                        String.valueOf(p.isADejaFaitHajj())         // boolean → String
                );

                writer.write(ligne);
                writer.newLine(); // passe à la ligne suivante
            }

            System.out.println("[OK] " + pelerins.size()
                    + " pelerin(s) sauvegardes dans " + fichier);

        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de sauvegarder : " + e.getMessage());
        }
    }

    @Override
    public List<Pelerin> charger(String fichier) {

        List<Pelerin> pelerins = new ArrayList<>();
        File f = new File(fichier);

        // Si le fichier n'existe pas encore, on retourne une liste vide
        if (!f.exists()) {
            System.out.println("[INFO] Fichier " + fichier
                    + " inexistant. Demarrage avec liste vide.");
            return pelerins;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {

            String ligne;
            int numeroLigne = 0;

            while ((ligne = reader.readLine()) != null) {
                numeroLigne++;

                // Ignorer les lignes vides ou les commentaires
                if (ligne.trim().isEmpty() || ligne.startsWith("#")) continue;

                // Découper la ligne en tableau de champs
                String[] champs = ligne.split(SEP, -1);

                // Vérifier qu'on a exactement 10 champs
                if (champs.length != 10) {
                    System.err.println("[WARN] Ligne " + numeroLigne
                            + " mal formee, ignoree : " + ligne);
                    continue;
                }

                try {
                    // Convertir les champs au bon type Java
                    LocalDate dateNaissance = DateUtils.parseDate(champs[8]);
                    boolean aDejaFaitHajj  = Boolean.parseBoolean(champs[9]);

                    Pelerin p = new Pelerin(
                            champs[0],      // idPersonne
                            champs[1],      // nom
                            champs[2],      // prenom
                            champs[3],      // cin
                            champs[4],      // telephone
                            champs[5],      // email
                            champs[6],      // passeport
                            champs[7],      // nationalite
                            dateNaissance,
                            aDejaFaitHajj
                    );

                    pelerins.add(p);

                } catch (Exception e) {
                    System.err.println("[WARN] Erreur ligne " + numeroLigne
                            + " : " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("[ERREUR] Lecture " + fichier + " : " + e.getMessage());
        }

        System.out.println("[OK] " + pelerins.size()
                + " pelerin(s) charges depuis " + fichier);
        return pelerins;
    }
}