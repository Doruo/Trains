package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Decharge extends Carte {
    public Decharge() {super("Décharge", -2, new ArrayList<>(List.of(TypeCarte.ACTION)) );}
    @Override
    public void jouer(Joueur joueur) {joueur.retirerCartesFerrailles();}
}