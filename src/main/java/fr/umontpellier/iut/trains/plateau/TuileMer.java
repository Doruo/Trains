package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

/**
 * Classe représentant une tuile de mer (tuile qui ne peut pas être occupée par
 * un rail ou une gare)
 */
public class TuileMer extends Tuile {
    public TuileMer() {
        super();
    }

    @Override
    public boolean estPosable(Joueur joueur) {return false;}
    @Override
    public boolean estPosableInitial(List<Joueur> joueur){return false;}
}
