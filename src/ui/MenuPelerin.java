package ui;

import exception.PelerinDejaInscritException;
import exception.PelerinIntrouvableException;
import model.personne.Pelerin;
import service.PelerinService;
import util.ConsoleUtils;
import util.DateUtils;

import java.time.LocalDate;

public class MenuPelerin {

    private PelerinService pelerinService;

    public MenuPelerin(PelerinService pelerinService) {
        this.pelerinService = pelerinService;
    }

    public void afficher() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n  -GESTION DES PELERINS: ");
            System.out.println("1. Ajouter un pelerin");
            System.out.println("2. Modifier un pelerin");
            System.out.println("3. Supprimer un pelerin");
            System.out.println("4. Afficher tous les pelerins");
            System.out.println("5. Rechercher par CIN");
            System.out.println("0. Retour");

            int choix = ConsoleUtils.lireEntier("Choix : ", 0, 5);
            switch (choix) {
                case 1: ajouterPelerin();  break;
                case 2: modifierPelerin(); break;
                case 3: supprimerPelerin();break;
                case 4: pelerinService.afficherTousPelerins(); break;
                case 5: rechercherParCin();break;
                case 0: retour = true;     break;
            }
        }
    }

    private void ajouterPelerin() {
        String nom     = ConsoleUtils.lireChaine("Nom        : ");
        String prenom  = ConsoleUtils.lireChaine("Prenom     : ");
        String cin     = ConsoleUtils.lireChaine("CIN        : ");
        String tel     = ConsoleUtils.lireChaine("Telephone  : ");
        String email   = ConsoleUtils.lireChaine("Email      : ");
        String pass    = ConsoleUtils.lireChaine("Passeport  : ");
        String natio   = ConsoleUtils.lireChaine("Nationalite: ");
        LocalDate dateNaiss = null;
        while (dateNaiss == null) {
            dateNaiss = DateUtils.parseDate(
                    ConsoleUtils.lireChaine("Date naissance (yyyy-MM-dd) : "));
        }
        boolean dejaHajj = ConsoleUtils.lireOuiNon("A deja fait le Hajj ?");

        try {
            Pelerin p = new Pelerin("", nom, prenom, cin, tel,
                    email, pass, natio, dateNaiss, dejaHajj);
            pelerinService.ajouterPelerin(p);
            System.out.println("[OK] Pelerin ajoute.");
        } catch (PelerinDejaInscritException e) {
            System.out.println("[ERREUR] " + e.getMessage());
        }
    }

    private void modifierPelerin() {
        String id = ConsoleUtils.lireChaine("ID du pelerin : ");
        String nom    = ConsoleUtils.lireChaine("Nouveau nom     : ");
        String prenom = ConsoleUtils.lireChaine("Nouveau prenom  : ");
        String tel    = ConsoleUtils.lireChaine("Nouveau tel     : ");
        String email  = ConsoleUtils.lireChaine("Nouveau email   : ");
        try {
            Pelerin p = new Pelerin(id, nom, prenom, "", tel, email,
                    "", "", null, false);
            pelerinService.modifierPelerin(id, p);
            System.out.println("[OK] Pelerin modifie.");
        } catch (PelerinIntrouvableException e) {
            System.out.println("[ERREUR] " + e.getMessage());
        }
    }

    private void supprimerPelerin() {
        String id = ConsoleUtils.lireChaine("ID du pelerin : ");
        if (ConsoleUtils.lireOuiNon("Confirmer la suppression ?")) {
            try {
                pelerinService.supprimerPelerin(id);
                System.out.println("[OK] Pelerin supprime.");
            } catch (PelerinIntrouvableException e) {
                System.out.println("[ERREUR] " + e.getMessage());
            }
        }
    }

    private void rechercherParCin() {
        String cin = ConsoleUtils.lireChaine("CIN : ");
        Pelerin p  = pelerinService.rechercherParCin(cin);
        if (p != null) p.afficherInfos();
        else System.out.println("Aucun pelerin trouve.");
    }
}