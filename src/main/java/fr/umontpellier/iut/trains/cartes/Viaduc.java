package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Viaduc extends Carte {
    public Viaduc() {super("Viaduc", -5,new ArrayList<>(List.of(TypeCarte.RAIL)));}

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterEffet(EffetTour.VIADUC);
    }
}
