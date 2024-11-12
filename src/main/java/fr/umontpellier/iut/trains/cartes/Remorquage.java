package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Remorquage extends Carte {
    public Remorquage() {
        super("Remorquage", -3,new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {

        List<String> choixPossibles = joueur.getNomCartesTrainDefausse();
        List<Bouton> boutons = choixPossibles.stream().map(Bouton::new).toList();

        String choix = joueur.choisir(joueur.getNom()+", ajouter une carte TRAIN de la défausse à votre main", choixPossibles,boutons, false);

        if (!choix.isEmpty()){
            joueur.ajouterCarteDansMain(joueur.getCarteDefausseAvecNom(choix));
            joueur.retirerCarteDefausse(choix);
        }
    }
}
