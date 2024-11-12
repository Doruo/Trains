package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.EffetTour;

import java.util.ArrayList;
import java.util.Set;

/**
 * Classe représentant une tuile plaine, fleuve ou montagne.
 */
public class TuileTerrain extends Tuile {
    /**
     * Type de terrain de la tuile ({@code PLAINE}, {@code FLEUVE} ou {@code MONTAGNE})
     */
    private TypeTerrain type;

    public TuileTerrain(TypeTerrain type) {
        super();
        this.type = type;
    }

    @Override
    public int getPrix(Joueur joueur){
        int prix = super.getPrix(joueur);

        ArrayList<EffetTour> effetsTours = joueur.getEffetsTour();
        if (effetsTours.contains(EffetTour.PONTENACIER) && type.equals(TypeTerrain.FLEUVE)
                || effetsTours.contains(EffetTour.TUNNEL) && type.equals(TypeTerrain.MONTAGNE)
        ) return prix;

        return switch (type){
            case FLEUVE -> 1+prix;
            case MONTAGNE -> 2+prix;
            case PLAINE -> prix;
        };
    }

}
