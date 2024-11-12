package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class FeuDeSignalisation extends Carte {
    public FeuDeSignalisation() {
        super("Feu de signalisation", -2,new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherEnMain(1);
        Carte carte = joueur.piocher();

        if (carte != null){
            boolean choix = joueur.choisirTrivial(joueur.getNom()+", souhaitez vous défausser "+carte.toLog()+" qui est au dessus de votre deck ?");

            if (choix) joueur.ajouterCarteDansDefausse(carte);
            else joueur.ajouterCarteDansPioche(carte);
        }
    }
}