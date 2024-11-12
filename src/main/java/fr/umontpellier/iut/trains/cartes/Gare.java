package fr.umontpellier.iut.trains.cartes;

import java.util.ArrayList;
import java.util.List;

public class Gare extends Carte {
    public Gare() {
        super("Gare", -3, new ArrayList<>(List.of(TypeCarte.GARE)));
    }
}
