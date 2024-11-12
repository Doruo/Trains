package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public abstract class Carte {
    private final String nom;

    private final int valeur;

    private final int cout;

    private final ArrayList<TypeCarte> typesCarte;

    public Carte(String nom, int cout){
        this.nom = nom;
        this.cout = cout;
        valeur = 0;
        typesCarte = new ArrayList<>();
    }

    public Carte(String nom, int cout, int valeur){
        this.nom = nom;
        this.cout = cout;
        this.valeur = valeur;
        typesCarte = new ArrayList<>();
    }

    public Carte(String nom, int valeur, int cout, ArrayList<TypeCarte> typesCarte) {
        this.nom = nom;
        this.valeur = valeur;
        this.cout = cout;
        this.typesCarte = typesCarte;
    }

    public Carte(String nom, int cout, ArrayList<TypeCarte> typesCarte) {
        this.nom = nom;
        this.cout = cout;
        this.typesCarte = typesCarte;
        valeur = 0;
    }

    public String getNom() {return nom;}

    public int getCout() {return cout;}

    public int getValeur() {return valeur;}

    public ArrayList<TypeCarte> getTypesCarte(){return typesCarte;}

    /**
     * Cette fonction est exécutée lorsqu'un joueur joue la carte pendant son tour.
     * Toutes les cartes ont une méthode jouer, mais elle ne fait rien par défaut.
     * 
     * @param joueur le joueur qui joue la carte
     */
    public void jouer(Joueur joueur){}

    @Override
    public String toString() {return nom;}

    public String getType(){
        List<TypeCarte> typeCartes = getTypesCarte();

        if (typeCartes.contains(TypeCarte.VICTOIRE)) return "victoire";
        else if (typeCartes.contains(TypeCarte.TRAIN)) return  "train";
        else if (typeCartes.contains(TypeCarte.ACTION)) return "action";
        else if (typeCartes.contains(TypeCarte.FERRAILLE)) return "ferraille";
        else if (typeCartes.contains(TypeCarte.RAIL)) return "rail";
        else if (typeCartes.contains(TypeCarte.GARE)) return "gare";

        return "";
    }

    public String toLog(){return String.format("<span class=\"carte %s\">%s</span>", getType(), nom);}
}
