package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Ferronnerie extends Carte {
    public Ferronnerie() {super("Ferronnerie", 1, -4, new ArrayList<>(List.of(TypeCarte.ACTION)));}
    @Override
    public void jouer(Joueur joueur) {joueur.ajouterEffet(EffetTour.FERRONNERIE);}
}
