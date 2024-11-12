package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Echangeur extends Carte {
    public Echangeur() {
        super("Échangeur", 1, -3, new ArrayList<>(List.of(TypeCarte.ACTION)));
    }
    @Override
    public void jouer(Joueur joueur) {
        String choix = joueur.choisir(joueur.getNom()+" , choisissez une carte en main",joueur.getNomCartesTrainEnJeu(),null,false);
        if (!choix.isEmpty()){
            Carte carte = joueur.getCarteEnJeuAvecNom(choix);
            joueur.ajouterCarteDansPioche(carte);
            joueur.retirerCarteEnJeu(choix);
        }
    }
}
