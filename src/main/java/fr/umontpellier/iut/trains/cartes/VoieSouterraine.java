package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class VoieSouterraine extends Carte {
    public VoieSouterraine() {
        super("Voie souterraine", -7,new ArrayList<>(List.of(TypeCarte.RAIL)));
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterEffet(EffetTour.VOIESOUTERRAINE);
    }
}
