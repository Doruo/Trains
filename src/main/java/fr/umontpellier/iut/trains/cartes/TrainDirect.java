package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainDirect extends Carte {
    public TrainDirect() {
        super("Train direct", 3, -6,new ArrayList<>(List.of(TypeCarte.TRAIN)));
    }

}
