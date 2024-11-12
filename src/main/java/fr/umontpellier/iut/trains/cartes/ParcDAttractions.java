package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class ParcDAttractions extends Carte {
    public ParcDAttractions() {
        super("Parc d'attractions", 1, -4,new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = joueur.getNomCartesTrainEnJeu();
        if (!choixPossibles.isEmpty()){
            String choix = joueur.choisir(joueur.getNom()+" , choisissez une carte en jeu",choixPossibles,null,false);
            Carte carte = joueur.getCarteEnJeuAvecNom(choix);
            joueur.solder(carte.getValeur());
        }
    }
}
