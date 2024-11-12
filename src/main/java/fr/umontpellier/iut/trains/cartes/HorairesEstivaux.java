package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class HorairesEstivaux extends Carte {
    public HorairesEstivaux() {
        super("Horaires estivaux", -3 , new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {
        if (joueur.choisirTrivial(joueur.getNom()+", souhaitez-vous ecartez "+getNom()+" et recevoir 3 Argent ?")) {
            joueur.solder(3);
            joueur.retirerCarteEnJeu(getNom());
            joueur.ecarterCarte(this);
        }
    }
}