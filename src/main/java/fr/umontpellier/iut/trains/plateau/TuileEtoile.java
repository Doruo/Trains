package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

/**
 * Classe représentant une tuile étoile (lieu éloigné)
 */
public class TuileEtoile extends Tuile {
    /**
     * Valeur du lieu éloigné (valeur indiquée sur le plateau)
     */
    private int valeur;

    public TuileEtoile(int valeur) {
        super();
        this.valeur = valeur;
    }

    @Override
    public void ajouterScoreCase(Joueur j){
        if (this.hasRail(j)) j.incrementerScore(valeur);
    }
    @Override
    public int getPrix(Joueur joueur){ return super.getPrix(joueur) + valeur;}
    @Override
    public boolean estPosableInitial(List<Joueur> joueur) {return false;}
}
