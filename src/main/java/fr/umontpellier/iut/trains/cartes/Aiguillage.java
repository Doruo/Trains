package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Aiguillage extends Carte {
    public Aiguillage() {super("Aiguillage", -5,  new ArrayList<>(List.of(TypeCarte.ACTION)));}
    @Override
    public void jouer(Joueur joueur) {joueur.piocherEnMain(2);}
}
