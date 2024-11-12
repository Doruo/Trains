package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.EffetTour;

/**
 * Classe représentant une tuile ville (où l'on peut poser des gares)
 */
public class TuileVille extends Tuile {
    /**
     * Nombre maximum de gares que l'on peut poser sur la tuile
     */
    private int nbGaresMax;
    /**
     * Nombre de gares posées sur la tuile
     */
    private int nbGaresPosees;

    @Override
    public void ajouterRail(Joueur joueur) {super.ajouterRail(joueur); ajouterScoreCase(joueur);}

    public TuileVille(int taille) {
        super();
        this.nbGaresMax = taille;
        this.nbGaresPosees = 0;
    }

    @Override
    public void ajouterScoreCase(Joueur j){
        if (getNbGares() > 0 && hasRail(j)){
            
            int score = 2;
            if (getNbGares() > 1){
                for(int i = 0 ; i < getNbGares()-1; i++)
                    score *= 2;
            }
            j.incrementerScore(score);
        }
    }
    @Override
    public int getPrix(Joueur joueur){
        if (joueur.getEffetsTour().contains(EffetTour.VIADUC)) return 0;
        return super.getPrix(joueur) + 1 + nbGaresPosees;
    }

    public void reactualiserScore(){
        if (getNbGares() > 0 && !estVide()){
            int score = 2;
            for (Joueur joueur : getNbJoueurs()) {
                if (getNbGares() > 1){
                    for(int i = 0 ; i < getNbGares(); i++)
                        score *= 2;
                }
                joueur.incrementerScore(score);
            }

        }
    }

    @Override
    public int getNbGares() {return nbGaresPosees;}
    public void incrementerNbGare(){reactualiserScore(); nbGaresPosees++;}

    public boolean estPosableGare (){return nbGaresPosees < nbGaresMax;}
}
