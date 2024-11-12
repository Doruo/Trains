package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Depotoir extends Carte {
    public Depotoir() {
        super("Dépotoir", 1, -5, new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterEffet(EffetTour.DEPOTOIR);
    }
}