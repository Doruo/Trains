package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CabineDuConducteur extends Carte {
    public CabineDuConducteur() {
        super("Cabine du conducteur", -2, new ArrayList<>(List.of(TypeCarte.ACTION)) );
    }

    @Override
    public void jouer(Joueur joueur) {

        if (joueur.mainNonVide()){
            boolean estFini = false;
            while (!estFini) {
                String choix = joueur.choisir(joueur.getNom()+", défaussez autant de cartes que vous voulez",joueur.getNomCartesMain(),null,true);
                if (choix.isEmpty()) estFini = true;
                else {
                    Carte carte = joueur.getCarteMainAvecNom(choix);
                    joueur.defausserCarteMain(carte);
                    joueur.piocherEnMain();
                }
            }
        }
    }
}
