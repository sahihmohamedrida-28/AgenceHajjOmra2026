package repository;

import java.util.List;

/**
 * Interface generique de persistance.
 * Concept POO : Interface, Generics.
 * T est le type de l'objet a persister (Pelerin, Voyage, Reservation).
 */
public interface IPersistable<T> {

    /**
     * Sauvegarde une liste d'objets dans un fichier CSV.
     * @param elements La liste des objets a sauvegarder
     * @param fichier  Le chemin du fichier CSV (ex: "data/pelerins.csv")
     */
    void sauvegarder(List<T> elements, String fichier);

    /**
     * Charge une liste d'objets depuis un fichier CSV.
     * @param fichier Le chemin du fichier CSV
     * @return La liste des objets charges
     */
    List<T> charger(String fichier);
}