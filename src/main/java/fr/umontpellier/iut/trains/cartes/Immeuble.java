package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Immeuble extends Carte {
    public Immeuble() {
        super("Immeuble", -5,new ArrayList<>(List.of(TypeCarte.VICTOIRE)));
    }
}
