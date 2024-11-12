package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainPostal extends Carte {
    public TrainPostal() {
        super("Train postal", 1, -4, new ArrayList<>(List.of(TypeCarte.TRAIN, TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {

        List<String> choixPossibles = joueur.getNomCartesMain();
        String choix;

        do {
            choix = joueur.choisir(joueur.getNom()+", défaussez autant de cartes que vous voulez",choixPossibles,null,true);
            if (!choix.isEmpty()){
                Carte carte = joueur.getCarteMainAvecNom(choix);
                joueur.defausserCarteMain(carte);
                joueur.solder(1);
            }
        }
        while (!choix.isEmpty());
    }
}