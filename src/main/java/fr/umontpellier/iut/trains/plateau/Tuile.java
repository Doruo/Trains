package fr.umontpellier.iut.trains.plateau;

import java.util.*;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.EffetTour;

public abstract class Tuile {
    /**
     * Liste des tuiles voisines qui sont connectées à la tuile courante (voisines
     * sur le plateau, sauf les tuiles entre lesquelles il y a une barrière
     * infranchissable)
     */
    private ArrayList<Tuile> voisines;
    /**
     * Ensemble des joueurs qui ont posé un rail sur la tuile
     */
    private Set<Joueur> rails;

    public Tuile() {
        this.voisines = new ArrayList<>();
        this.rails = new HashSet<>();
    }

    /**
     * @return {@code true} si la tuile ne contient aucun rail, {@code false} sinon
     */
    public boolean estVide() {
        return rails.isEmpty();
    }

    /**
     * @param joueur le joueur dont on veut déterminer s'il a posé un rail sur la
     *               tuile
     * @return {@code true} si le joueur a posé un rail sur la tuile, {@code false}
     *         sinon
     */
    public boolean hasRail(Joueur joueur) {
        return rails.contains(joueur);
    }
    public int getNbRails(){return rails.size();}

    /**
     * Ajoute un rail du joueur sur la tuile
     * 
     * @param joueur le joueur qui pose un rail sur la tuile
     */
    public void ajouterRail(Joueur joueur) {rails.add(joueur);}
    public Set<Joueur> getNbJoueurs(){return rails;}

    /**
     * Ajoute une voisine à la tuile courante, et ajoute la tuile courante comme
     * voisine de la tuile passée en argument.
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * construire le plateau de jeu.
     * 
     * @param tuile la tuile voisine à ajouter
     */
    public void ajouterVoisine(Tuile tuile) {
        voisines.add(tuile);
        tuile.voisines.add(this);
    }

    /**
     * Supprime une tuile de la liste de voisines de {@code this} (et supprime
     * {@code this} des voisines de la tuile passée en paramètres).
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * représenter les barrières infranchissables sur le plateau.
     * 
     * @param tuile la tuile voisine à supprimer
     */
    public void supprimerVoisine(Tuile tuile) {
        voisines.remove(tuile);
        tuile.voisines.remove(this);
    }
    /**
     * Vérifit si le joueur peut poser un rail
     * sur cette tuile
     * @param joueur le joueur courant
     * */
    public boolean estPosable(Joueur joueur){return !hasRail(joueur) && joueur.getArgent() >= getPrix(joueur);}

    /**
     * Vérifit si le joueur peut démarer
     * la partie sur cette tuile.
     * Il ne doit y avoir aucun joueur sur cette tuile
     * @param joueurs la liste de tous les joueurs du jeu
     */
    public boolean estPosableInitial(List<Joueur> joueurs){
        for (Joueur joueur : joueurs)
            if (hasRail(joueur)) return false;
        return true;
    }

    public boolean estPosableGare(){return false;}

    /**
     * @return le nombre de jetons gare posés sur la tuile. Par défaut la fonction
     *         renvoie 0 car on ne peut pas poser de jeton gare sur une tuile
     *         quelconque.
     */
    public int getNbGares() {return 0;}

    public void incrementerNbGare(){}

    public ArrayList<Tuile> getVoisine(){return voisines;}

    public int getPrix(Joueur joueur){
        if (joueur.getEffetsTour().contains(EffetTour.COOPERATION))
            return 0;
        return getNbRails();
    }
    public void ajouterScoreCase(Joueur j){}

    /**
     * @return une représentation de la tuile sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface
     *         graphique)
     */
    public Map<String, Object> dataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("rails", rails.stream().map(Joueur::getCouleur).toArray());
        int nbGares = getNbGares();
        if (nbGares > 0) {
            map.put("nbGares", nbGares);
        }
        return map;
    }
}
