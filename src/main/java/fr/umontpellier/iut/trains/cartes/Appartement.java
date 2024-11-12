package fr.umontpellier.iut.trains.cartes;

import java.util.ArrayList;
import java.util.List;

public class Appartement extends Carte {
    public Appartement() {
        super("Appartement", -3,  new ArrayList<>(List.of(TypeCarte.VICTOIRE)));
    }
}