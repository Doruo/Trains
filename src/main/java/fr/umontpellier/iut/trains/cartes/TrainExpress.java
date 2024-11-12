package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainExpress extends Carte {
    public TrainExpress() {
        super("Train express", 2, -3,new ArrayList<>(List.of(TypeCarte.TRAIN)));
    }

}
