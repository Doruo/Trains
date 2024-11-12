package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Depot extends Carte {
    public Depot() {super("Dépôt",1,-3, new ArrayList<>(List.of(TypeCarte.ACTION)));}

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherEnMain(2);
        List<String> choixPossibles = joueur.getNomCartesMain();

        if (choixPossibles.size() < 2) joueur.defausserMain();
        else {
            for (int i = 2; i > 0; i--) {
                String choix = joueur.choisir(joueur.getNom()+", défausser "+i+" carte(s)",choixPossibles,null,false);
                Carte carteDefausse = joueur.getCarteMainAvecNom(choix);
                joueur.defausserCarteMain(carteDefausse);
                joueur.log("Défausse "+carteDefausse.toLog());
            }
        }
    }
}
