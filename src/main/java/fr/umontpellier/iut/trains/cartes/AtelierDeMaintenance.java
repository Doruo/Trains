package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class AtelierDeMaintenance extends Carte {
    public AtelierDeMaintenance() {super("Atelier de maintenance", -5, new ArrayList<>(List.of(TypeCarte.ACTION)) );}
    @Override
    public void jouer(Joueur joueur) {if (joueur.mainNonVide()) joueur.ajouterCarteRecue(joueur.prendreDansLaReserve(joueur.devoilerCarteMain().getNom()));}
}