package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class PersonnelDeGare extends Carte {
    public PersonnelDeGare() {
        super("Personnel de gare", -2,new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>(List.of("piocher", "argent","ferraille"));
        List<Bouton> boutons = new ArrayList<>();

        boutons.add(new Bouton("Piochez 1 carte",choixPossibles.get(0)));
        boutons.add(new Bouton("Recevez 1 Argent",choixPossibles.get(1)));
        boutons.add(new Bouton("Remettez 1 Ferraille sur la pile",choixPossibles.get(2)));

        String choix = joueur.choisir(joueur.getNom()+", faites 1 choix parmis ces options", choixPossibles, boutons, false);

        switch (choix){
            case "piocher" -> joueur.piocherEnMain();
            case "argent" -> joueur.solder(1);
            case "ferraille" -> joueur.retirerUneCarteFerraille();
        }
    }
}
