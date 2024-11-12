package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainMatinal extends Carte {
    public TrainMatinal() {super("Train matinal", 2, -5, new ArrayList<>(List.of(TypeCarte.TRAIN, TypeCarte.ACTION)));}

    @Override
    public void jouer(Joueur joueur) {joueur.ajouterEffet(EffetTour.TRAINMATINAL);}
}
