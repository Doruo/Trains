package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class PassageEnGare extends Carte {
    public PassageEnGare() {
        super("Passage en gare", 1, -3,new ArrayList<>(List.of(TypeCarte.ACTION)));
    }
    @Override
    public void jouer(Joueur joueur) {joueur.piocherEnMain();}
}