package fr.umontpellier.iut.trains.cartes;

import java.util.ArrayList;
import java.util.List;

public class PoseDeRails extends Carte {
    public PoseDeRails() {
        super("Pose de rails", -3,new ArrayList<>(List.of(TypeCarte.RAIL)));
    }

}
