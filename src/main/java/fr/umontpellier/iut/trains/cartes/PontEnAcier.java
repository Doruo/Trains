package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class PontEnAcier extends Carte {
    public PontEnAcier() {
        super("Pont en acier", -4,new ArrayList<>(List.of(TypeCarte.RAIL)));
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterEffet(EffetTour.PONTENACIER);
    }
}
