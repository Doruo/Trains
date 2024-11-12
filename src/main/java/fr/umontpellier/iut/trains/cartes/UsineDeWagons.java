package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class UsineDeWagons extends Carte {
    public UsineDeWagons() {
        super("Usine de wagons", -5, new ArrayList<>(List.of(TypeCarte.ACTION)));
    }

    @Override
    public void jouer(Joueur joueur) {

        List<String> choixPossiblesMain = new ArrayList<>(joueur.getNomCartesTrainMain());

        String choixCarteEcarte = joueur.choisir(joueur.getNom()+", écartez une carte TRAIN de votre main",choixPossiblesMain,null,false);

        Carte carteEcarte = joueur.getCarteMainAvecNom(choixCarteEcarte);
        joueur.retirerCarteEnMain(choixCarteEcarte);
        joueur.ecarterCarte(carteEcarte);

        List<String> nomCartesTrainReserve = joueur.getNomCartesTrainReserve();
        List<String> choixPossiblesReserve = new ArrayList<>();

        for (String nomCarte : nomCartesTrainReserve){
            Carte carteCourant = joueur.prendreDansLaReserve(nomCarte);
            if (-carteCourant.getCout() <= -carteEcarte.getCout() + 3) choixPossiblesReserve.add("ACHAT:"+nomCarte);
            joueur.ajouterCarteDansReserve(carteCourant);
        }

        String choixCarteTrainReserve = joueur.choisir(joueur.getNom()+", piochez une carte TRAIN depuis la réserve",choixPossiblesReserve,null,false);

        Carte carteTrainReserve = joueur.prendreDansLaReserve(choixCarteTrainReserve.split(":")[1]);
        joueur.ajouterCarteDansMain(carteTrainReserve);
    }
}
