package repository;

import model.personne.Pelerin;
import util.DateUtils;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PelerinRepository implements IPersistable<Pelerin> {
    private static final String SEP = ";";

    @Override
    public void sauvegarder(List<Pelerin> liste, String fichier) {
        new File("data").mkdirs();
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(fichier, false))) {
            for (Pelerin p : liste) {
                String ligne = String.join(SEP,
                        p.getIdPersonne(), p.getNom(), p.getPrenom(), p.getCin(),
                        p.getTelephone(), p.getEmail(), p.getPasseport(),
                        p.getNationalite(),
                        DateUtils.formatDate(p.getDateNaissance()),
                        String.valueOf(p.isADejaFaitHajj())
                );
                bw.write(ligne);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("[ERREUR CSV] : " + e.getMessage());
        }
    }

    @Override
    public List<Pelerin> charger(String fichier) {
        List<Pelerin> pelerins = new ArrayList<>();
        File file = new File(fichier);
        if (!file.exists()) return pelerins;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] token = ligne.split(SEP);
                if (token.length < 10) continue;
                LocalDate dNaiss = DateUtils.parseDate(token[8]);
                boolean dejaHajj = Boolean.parseBoolean(token[9]);
                pelerins.add(new Pelerin(
                        token[0], token[1], token[2], token[3],
                        token[4], token[5], token[6], token[7],
                        dNaiss, dejaHajj));
            }
        } catch (IOException e) {
            System.err.println("[ERREUR] Lecture impossible.");
        }
        return pelerins;
    }
}