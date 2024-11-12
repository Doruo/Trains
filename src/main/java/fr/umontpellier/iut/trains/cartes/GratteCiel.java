package fr.umontpellier.iut.trains.cartes;

import java.util.ArrayList;
import java.util.List;

public class GratteCiel extends Carte {
    public GratteCiel() {
        super("Gratte-ciel", -8, new ArrayList<>(List.of(TypeCarte.VICTOIRE)));
    }

}