package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeRenseignements extends Carte {
    public CentreDeRenseignements() {super("Centre de renseignements", 1, -4, new ArrayList<>(List.of(TypeCarte.ACTION)));}

    @Override
    public void jouer(Joueur joueur) {

        List<String> choixPossibles = new ArrayList<>();
        List<Bouton> boutons = new ArrayList<>();
        ListeDeCartes cartes = new ListeDeCartes();

        for (int i = 0; i < 4; i++){
            Carte carte = joueur.piocher();
            cartes.add(carte);
            choixPossibles.add(carte.getNom());
            boutons.add(new Bouton(carte.getNom(),carte.getNom()));
        }

        String choix = joueur.choisir(joueur.getNom()+", choisissez une carte à ajouter dans votre main",choixPossibles, boutons, true);

        if (!choix.isEmpty()){
            Carte carteChoisi = joueur.getCarteListeAvecNom(choix,cartes);
            joueur.ajouterCarteDansMain(carteChoisi);
            cartes.remove(carteChoisi);
        }

        while (!cartes.isEmpty()){
            boutons.clear();
            for (Carte carte : cartes) boutons.add(new Bouton(carte.getNom()));

            choix = joueur.choisir(joueur.getNom()+", choisissez dans quel ordre vont les autres cartes dans le deck",choixPossibles, boutons, false);
            Carte carteChoisi = joueur.getCarteListeAvecNom(choix,cartes);
            joueur.ajouterCarteDansPioche(carteChoisi);
            choixPossibles.remove(choix);
            cartes.remove(carteChoisi);
        }
    }
}
