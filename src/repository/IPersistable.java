package repository;

import java.util.List;

public interface IPersistable<T> {
    void sauvegarder(List<T> donnees, String cheminFichier);
    List<T> charger(String cheminFichier);
}