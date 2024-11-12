package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainOmnibus extends Carte {
    public TrainOmnibus() {
        super("Train omnibus", 1, -1,new ArrayList<>(List.of(TypeCarte.TRAIN)));
    }

}
