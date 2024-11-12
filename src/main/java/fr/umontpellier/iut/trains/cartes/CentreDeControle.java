package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeControle extends Carte {
    public CentreDeControle() {
        super("Centre de contrôle",-3, new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherEnMain();
        Carte cartePioche = joueur.piocher();
        String nomCarteAnnonce = joueur.choisirUneCarte();

        if (cartePioche != null && nomCarteAnnonce != null) {
            joueur.log("Dessus du deck était "+cartePioche.toLog());
            if (cartePioche.getNom().equals(nomCarteAnnonce)) {
                joueur.log("Pioche "+cartePioche.toLog());
                joueur.ajouterCarteDansMain(cartePioche);
            }
            else joueur.ajouterCarteDansPioche(cartePioche);
        }
    }
}
