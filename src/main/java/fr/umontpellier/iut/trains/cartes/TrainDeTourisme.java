package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainDeTourisme extends Carte {
    public TrainDeTourisme() {super("Train de tourisme", 1, -4, new ArrayList<>(List.of(TypeCarte.TRAIN,TypeCarte.ACTION)));}
    @Override
    public void jouer(Joueur joueur) {joueur.incrementerScore(1);}
}
