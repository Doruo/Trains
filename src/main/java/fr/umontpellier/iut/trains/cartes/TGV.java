package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TGV extends Carte {
    public TGV() {super("TGV", 1, -2, new ArrayList<>(List.of(TypeCarte.TRAIN,TypeCarte.ACTION)));}

    @Override
    public void jouer(Joueur joueur) {
        if (joueur.getNomCartesTrainEnJeu().contains("Train omnibus")) joueur.solder(1);
    }
}
