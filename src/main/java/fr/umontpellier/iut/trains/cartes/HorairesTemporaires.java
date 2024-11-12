package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class HorairesTemporaires extends Carte {
    public HorairesTemporaires() {
        super("Horaires temporaires", -5,new ArrayList<>(List.of(TypeCarte.ACTION)));
    }


    /**
     * S'il y a strictement moins de 2 cartes TRAIN dans la pioche (+ défausse),
     * alors toutes les cartes TRAIN disponibles sont ajoutées à la main du joueur
     * et toutes les autres cartes sont défaussées.
     * @param joueur le joueur qui joue la carte
     */
    @Override
    public void jouer(Joueur joueur) {

        List<Carte> carteTrains = new ArrayList<>(),
        carteDevoiles = new ArrayList<>();
        boolean estFini=false;

        while (!estFini && carteTrains.size() < 2) {
            Carte carte = joueur.piocher();
            if (carte == null) estFini = true;
            else if (carte.getTypesCarte().contains(TypeCarte.TRAIN)) carteTrains.add(carte);
            else carteDevoiles.add(carte);
        }
        for (Carte carte : carteTrains) joueur.ajouterCarteDansMain(carte);
        for (Carte carte : carteDevoiles) joueur.ajouterCarteDansDefausse(carte);
    }
}
