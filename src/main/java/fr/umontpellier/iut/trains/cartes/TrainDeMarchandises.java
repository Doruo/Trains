package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainDeMarchandises extends Carte {
    public TrainDeMarchandises() {super("Train de marchandises", 1, -4, new ArrayList<>(List.of(TypeCarte.TRAIN,TypeCarte.ACTION)));}

    @Override
    public void jouer(Joueur joueur) {
        boolean aFaitUnChoix;
        do {
            String choix = joueur.choisir(joueur.getNom()+", défaussez autant de cartes Ferraille que vous voulez", List.of("Ferraille"),null,true);
            aFaitUnChoix = !choix.isEmpty();
            if (aFaitUnChoix){
                joueur.ajouterCarteDansReserve(joueur.getCarteMainAvecNom(choix));
                joueur.retirerCarteEnMain(choix);
                joueur.solder(1);
            }
        }
        while (aFaitUnChoix);
    }
}
