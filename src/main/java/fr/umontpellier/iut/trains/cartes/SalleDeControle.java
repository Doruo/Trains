package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class SalleDeControle extends Carte {
    public SalleDeControle() {
        super("Salle de contrôle", -7,new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherEnMain(3);
    }
}
