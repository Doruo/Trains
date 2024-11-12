package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class BureauDuChefDeGare extends Carte {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", -4,new ArrayList<>(List.of(TypeCarte.ACTION)) );
    }

    @Override
    public void jouer(Joueur joueur) {
        String choix = joueur.choisir(joueur.getNom()+" , choisissez une carte en main",joueur.getNomCartesActionMain(),null,false);
        if(!choix.isEmpty()) joueur.getCarteMainAvecNom(choix).jouer(joueur);
    }
}
