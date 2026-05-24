package main;

import model.personne.Guide;
import service.*;
import ui.ConsoleMenu;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("                                 ");
        System.out.println(" BIENVENUE A VOTRE AGENCE DE PELERINAGE HAJJ & OMRA");
        System.out.println("                                ");

        Map<String, Guide> guidesParId = new HashMap<>();

        PelerinService pelerinService = new PelerinService();

        VoyageService voyageService = new VoyageService(guidesParId);

        ReservationService reservationService = new ReservationService(
                pelerinService.getPelerinsParId(),
                voyageService.getVoyagesParId()
        );

        PaiementService paiementService = new PaiementService(reservationService);

        ConsoleMenu menu = new ConsoleMenu(
                pelerinService, voyageService,
                reservationService, paiementService
        );
        menu.afficherMenuPrincipal();
    }
}