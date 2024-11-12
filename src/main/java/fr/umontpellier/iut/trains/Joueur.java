package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.Tuile;

public class Joueur {
    /**
     * Le jeu auquel le joueur est rattaché
     */
    private Jeu jeu;
    /**
     * Nom du joueur (pour les affichages console et UI)
     */
    private String nom;
    /**
     * Quantité d'argent que le joueur a (remis à zéro entre les tours)
     */
    private int argent;
    /**
     * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
     * jouant les cartes RAIL (vertes) et remis à zéro entre les tous
     */
    private int pointsRails;
    /**
     * Nombre de jetons rails disponibles (non placés sur le plateau)
     */
    private int nbJetonsRails;
    /**
     * Liste des cartes en main
     */
    private ListeDeCartes main;
    /**
     * Liste des cartes dans la pioche (le début de la liste correspond au haut de
     * la pile)
     */
    private ListeDeCartes pioche;
    /**
     * Liste de cartes dans la défausse
     */
    private ListeDeCartes defausse;
    /**
     * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
     */
    private ListeDeCartes cartesEnJeu;
    /**
     * Liste des cartes reçues pendant le tour
     */
    private ListeDeCartes cartesRecues;

    /**
     * Couleur du joueur (utilisé par l'interface graphique)
     */
    private CouleurJoueur couleur;

    /**
     * Nombre de points que le joueur a pour tenter de gagner la partie
     */
    private int score;

    private ArrayList<EffetTour> effetsTour;

    public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
        this.jeu = jeu;
        this.nom = nom;
        this.couleur = couleur;
        argent = 0;
        pointsRails = 0;
        nbJetonsRails = 20;
        score = 0;
        main = new ListeDeCartes();
        defausse = new ListeDeCartes();
        pioche = new ListeDeCartes();
        cartesEnJeu = new ListeDeCartes();
        cartesRecues = new ListeDeCartes();
        effetsTour = new ArrayList<>();

        // créer 7 Train omnibus (non disponibles dans la réserve)
        pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
        // prendre 2 Pose de rails de la réserve
        for (int i = 0; i < 2; i++)
            pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
        // prendre 1 Gare de la réserve
        pioche.add(jeu.prendreDansLaReserve("Gare"));
        // mélanger la pioche
        pioche.melanger();
        // Piocher 5 cartes en main
        // Remarque : on peut aussi appeler piocherEnMain(5) si la méthode est écrite
        piocherEnMain(5);
    }
    public String getNom() {return nom;}

    public CouleurJoueur getCouleur() {return couleur;}

    public int getNbJetonsRails() {return nbJetonsRails;}

    public int getArgent() {return argent;}

    public ArrayList<EffetTour> getEffetsTour() {return effetsTour;}

    public List<String> getNomCartesMain(){return  main.stream().map(Carte::getNom).toList();}

    public List<String> getNomCartesActionMain(){return main.stream().filter(carte -> carte.getTypesCarte().contains(TypeCarte.ACTION)).map(Carte::getNom).toList();}

    public List<String> getNomCartesTrainReserve(){
        return jeu.getReserve().keySet().stream().filter(nomCarte -> !jeu.getReserve().get(nomCarte).isEmpty() &&
                        jeu.getReserve().get(nomCarte).get(0).getTypesCarte().contains(TypeCarte.TRAIN)).toList();
    }
    public List<String> getNomCartesTrainMain(){return main.stream().filter(carte -> carte.getTypesCarte().contains(TypeCarte.TRAIN)).map(Carte::getNom).toList();}

    public List<String> getNomCartesTrainDefausse(){return defausse.stream().filter(carte -> carte.getTypesCarte().contains(TypeCarte.TRAIN)).map(Carte::getNom).toList();}

    public List<String> getNomCartesTrainEnJeu() {return cartesEnJeu.stream().filter(carte -> carte.getTypesCarte().contains(TypeCarte.TRAIN)).map(Carte::getNom).toList();}

    /**
     * Recupere la carte de la main
     * à partir de son nom
     * 
     * @param nomCarte
     * @return la carte recherché
     */
    public Carte getCarteMainAvecNom(String nomCarte) {return main.getCarte(nomCarte);}
    public Carte getCarteDefausseAvecNom(String nomCarte) {return defausse.getCarte(nomCarte);}
    public Carte getCarteEnJeuAvecNom(String nomCarte) {return cartesEnJeu.getCarte(nomCarte);}
    public Carte getCarteListeAvecNom(String nomCarte, ListeDeCartes cartes) {return cartes.getCarte(nomCarte);}
    public boolean mainNonVide() {return !main.isEmpty();}

    /**
     * Renvoie le score total du joueur
     * <p>
     * Le score total est la somme des points obtenus par les effets suivants :
     * <ul>
     * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
     * un rail)
     * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
     * <li>score courant du joueur (points marqués en jouant des cartes pendant la
     * partie p.ex. Train de tourisme)
     * </ul>
     * 
     * @return le score total du joueur
     */
    public int getScoreTotal() {return score;} // A MODIF

    /**
     * Retire et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     *
     * @return la carte piochée ou {@code null} si aucune carte disponible
     */
    public Carte piocher() {
        if (pioche.isEmpty()) melangeDefausseDansPioche();
        return pioche.remove(0);
    }

    /**
     * Retire et renvoie les {@code n} premières cartes de la pioche.
     * <p>
     * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
     * ont pu être piochées sont renvoyées.
     * 
     * @param n nombre de cartes à piocher
     * @return une liste des cartes piochées (la liste peut contenir moins de n
     *         éléments si pas assez de cartes disponibles dans la pioche et la
     *         défausse)
     */
    public List<Carte> piocher(int n) {
        List<Carte> listCarte = new ArrayList<>();
        while (n > 0) {
            listCarte.add(piocher());
            n--;
        }
        return listCarte;
    }

    /**
     * Retire, ajoute dans la main et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     */
    public void piocherEnMain() {main.add(piocher());}

    /**
     * Retire, ajoute dans la main et renvoie les {@code n} premières cartes de la
     * pioche.
     * <p>
     * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
     * ont pu être piochées sont renvoyées.
     *
     * @param n nombre de cartes à piocher
     */
    public void piocherEnMain(int n) {main.addAll(piocher(n));}
    public void ajouterCarteDansPioche(Carte carte) {pioche.add(0, carte);}
    public void ajouterCarteDansDefausse(Carte carte) {defausse.add(0, carte);}
    public void ajouterCarteDansMain(Carte carte) {main.add(0, carte);}
    public void ajouterCarteRecue(Carte carte) {cartesRecues.add(0, carte);}
    public void ajouterCarteEnJeu(Carte carte) {cartesEnJeu.add(carte);}
    public void ajouterEffet(EffetTour effetTour){effetsTour.add(effetTour);}
    public void retirerEffetsTour(){effetsTour.clear();}
    public void ajouterGare(int numTuile) {jeu.getTuile(numTuile).incrementerNbGare();}

    /** Ajoute une carte ferailles. */
    public void piocherUneCarteFerraille() {
        if (!effetsTour.contains(EffetTour.DEPOTOIR)) {
            Carte carte = prendreDansLaReserve("Ferraille");
            if (carte != null) ajouterCarteRecue(carte);
        }
    }

    /** Enlève toutes les cartes ferrailles de la main du joueur et les remet dans la réserve.*/
    public void retirerCartesFerrailles() {
        ListeDeCartes cartesFerraille = new ListeDeCartes(main.stream().filter(carte -> carte.getNom().equals("Ferraille")).toList());
        main.removeAll(cartesFerraille);
        jeu.getReserve().get("Ferraille").addAll(cartesFerraille);
        if (!cartesFerraille.isEmpty()) log("Enlève toutes ses "+ cartesFerraille.get(0).toLog());
    }

    /** Enlève une carte ferraille de la main du joueur et la remet dans la réserve. */
    public void retirerUneCarteFerraille() {
        Carte carteFerraille = main.getCarte("Ferraille");
        if (carteFerraille !=  null) {
            ajouterCarteDansReserve(carteFerraille);
            retirerCarteEnMain(carteFerraille.getNom());
            log("Enlève une"+ carteFerraille.toLog());
        }
    }

    public void retirerArgent() {argent = 0;}
    public void retirerPointsRails() {pointsRails = 0;}

    public void melangeDefausseDansPioche() {
        defausse.melanger();
        pioche.addAll(defausse);
        defausse.clear();
    }

    /**
     * Solde l'argent du joueur
     * @param valeur (positif ou négative selon débit ou crédit)
     */
    public void solder(int valeur) {argent += valeur;}

    /**
     * Demande au joueur de choisir parmis toutes les cartes existantes
     * @return le nom de la carte choisi
     */
    public String choisirUneCarte() {

        List<String> choixPossibles = new ArrayList<>(jeu.getListeNomsCartes());
        List<Bouton> bouttons = new ArrayList<>();

        for (String nomCarte : jeu.getListeNomsCartes())
            bouttons.add(new Bouton(nomCarte));

        String choix = choisir(getNom() + ", choississez une carte", choixPossibles, bouttons, false);

        log(toLog() + " a choisi " + choix);
        return choix;
    }

    /**
     * Demande au joueur de choisir une carte
     * parmis ceux de sa main
     * 
     * @return la carte de sa main choisi
     */
    public Carte devoilerCarteMain() {
        List<String> choixPossibles = main.stream().map(Carte::getNom).toList();

        String choix = choisir(getNom() + ", dévoilez une carte de votre main", choixPossibles, null, false);

        Carte carteChoisi = getCarteMainAvecNom(choix);
        log(toLog() + " dévoile " + carteChoisi.toLog());

        return carteChoisi;
    }



    /**
     * Achète une carte
     * 
     * @param nomCarte le nom de la carte acheté
     */
    public Carte acheterCarte(String nomCarte) {

        Carte carte = prendreDansLaReserve(nomCarte);

        solder(carte.getCout());
        ajouterCarteRecue(carte);

        // Achat Carte Victoire
        if (carte.getTypesCarte().contains(TypeCarte.VICTOIRE)) {
            incrementerScore(jeu.getScoreCarte(carte));
            piocherUneCarteFerraille();
        }

        log("Achète "+carte.toLog());
        return carte;
    }

    public void poserRail(Tuile tuile){
        if (!getEffetsTour().contains(EffetTour.VOIESOUTERRAINE)) solder(-tuile.getPrix(this));
        if (tuile.getNbRails()>0 && !getEffetsTour().contains(EffetTour.COOPERATION)) piocherUneCarteFerraille();
        nbJetonsRails--;
        pointsRails--;
        tuile.ajouterRail(this);
        log("Pose un rail");
    }

    public void poserRailInitial(Tuile tuile) {
        nbJetonsRails--;
        tuile.ajouterRail(this);
    }

    private void finirTour() {
        retirerArgent();
        retirerPointsRails();
        defausserCartesJoueur();
        retirerEffetsTour();
        piocherEnMain(5);
    }

    private void defausserCartesJoueur() {
        defausserMain();
        defausserCartesRecus();
        defausserCartesEnJeu();
    }

    public void defausserCartesRecus() {
        defausse.addAll(cartesRecues);
        cartesRecues.clear();
    }
    public void defausserCartesEnJeu() {
        defausse.addAll(cartesEnJeu);
        cartesEnJeu.clear();
    }

    public void defausserMain() {
        defausse.addAll(main);
        main.clear();
    }

    public void defausserCarteMain(Carte carte) {
        if (main.contains(carte)) {
            main.retirer(carte.getNom());
            defausse.add(carte);
        }
    }
    public void incrementerScore(int n) {score+=n;}

    /**
     * Demande au joueur de choisir oui ou non
     * à une action.
     * @param instruction demande faite au joueur
     * @return true si 'oui', 'non' sinon
     */
    public boolean choisirTrivial(String instruction){
        List<Bouton> boutons = Arrays.asList(new Bouton("Oui","oui"),new Bouton("Non","non"));
        return choisir(instruction, Arrays.asList("oui", "non"),boutons,false).equals("oui");
    }

    public void retirerCarteEnMain(String nomCarte) {main.retirer(nomCarte);}
    public void retirerCarteDefausse(String nomCarte) {defausse.retirer(nomCarte);}
    public void retirerCarteEnJeu(String nomCarte) {cartesEnJeu.retirer(nomCarte);}
    public void retirerCartesRecues(String nomCarte) {cartesRecues.retirer(nomCarte);}
    public void ajouterCarteDansReserve(Carte carte){jeu.getReserve().get(carte.getNom()).add(0,carte);}
    public void ecarterCarte(Carte carte) {jeu.ajouterCarteEcarte(carte);}
    public Carte prendreDansLaReserve(String nomCarte){return jeu.prendreDansLaReserve(nomCarte);}

    /**
     * Joue un tour complet du joueur
     * <p>
     * Le tour du joueur se déroule en plusieurs étapes :
     * <ol>
     * <li>Initialisation
     * <p>
     * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
     * éventuel affichage dans le log.
     * 
     * <li>Boucle principale
     * <p>
     * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
     * il faut préparer la liste de tous les choix valides que le joueur peut faire
     * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
     * au joueur de choisir une action (en appelant la méthode {@code choisir}).
     * <p>
     * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
     * recommencer la boucle si le tour n'est pas terminé.
     * <p>
     * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
     * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
     * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
     * tour)
     * 
     * <li>Finalisation
     * <p>
     * Actions à exécuter à la fin du tour : réinitialiser les attributs
     * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
     * toutes les
     * cartes, piocher 5 nouvelles cartes en main, etc.
     * </ol>
     */
    public void jouerTour() {
        // Initialisation
        jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");
        // À FAIRE: compléter l'initialisation du tour si nécessaire (mais possiblement
        // rien de spécial à faire)

        boolean finTour = false, passeTour = true;

        // Boucle principale
        while (!finTour) {

            List<String> choixPossibles = new ArrayList<>();
            List<Bouton> boutons = new ArrayList<>();

            // À FAIRE: préparer la liste des choix possibles
            // Ajout nom cartes jouables
            for (Carte carte : main) {
                List<TypeCarte> typeCartes = carte.getTypesCarte();
                boolean estFerraille = typeCartes.contains(TypeCarte.FERRAILLE),
                        estVictoire = typeCartes.contains(TypeCarte.VICTOIRE);

                if (passeTour && estFerraille || !estFerraille && !estVictoire) choixPossibles.add(carte.getNom());
            }

            // Ajout Choix Achat Cartes
            for (String nomCarte : jeu.getReserve().keySet()) {
                List<Carte> listeCarteReserve = jeu.getReserve().get(nomCarte);
                if (!listeCarteReserve.isEmpty() && listeCarteReserve.get(0).getCout() + argent >= 0
                        && !nomCarte.equals("Ferraille"))
                    choixPossibles.add("ACHAT:" + nomCarte);
            }

            // Ajout Choix Tuiles
            if (nbJetonsRails > 0 && pointsRails > 0){
                for (int i = 0; i < jeu.getTuiles().size(); i++)
                    if (jeu.getTuile(i).estPosable(this))  choixPossibles.add("TUILE:"+i);
            }

            // Choix de l'action à réaliser
            String choix = choisir(String.format("Tour de %s", getNom()), choixPossibles, boutons, true);

            // À FAIRE: exécuter l'action demandée par le joueur
            if (choix.equals("Ferraille")) {
                retirerCartesFerrailles();
                finTour=true;
            }
            else if (choix.isEmpty()) {
                log("Termine son tour");
                finTour = true;
            }
            else if (choix.startsWith("ACHAT:"))
            {
                Carte carteAchete = acheterCarte(choix.split(":")[1]);

                // Effet Carte Train Matinal
                if (effetsTour.contains(EffetTour.TRAINMATINAL)){
                    if (choisirTrivial(getNom() + ", souhaitez-vous placer cette carte sur votre pioche ?")){
                        ajouterCarteDansPioche(carteAchete);
                        retirerCartesRecues(carteAchete.getNom());
                    }
                }
            }
            else if (choix.startsWith("TUILE:"))
            {
                // Pose Rail sur une Tuile
                int numTuile = Integer.parseInt(choix.split(":")[1]);
                Tuile tuile = jeu.getTuile(numTuile);
                
                boolean aVoisine = false;

                if (tuile.estPosable(this)){
                    for (Tuile t : jeu.getTuile(numTuile).getVoisine())
                        if (t.hasRail(this)) aVoisine = true;
                    // Pose Rail sur la tuile
                    if (aVoisine) poserRail(tuile);
                }
            }
            // Joue Carte
            else
            {
                Carte carteUtilise = getCarteMainAvecNom(choix);
                log("Joue " + carteUtilise.toLog());
                solder(carteUtilise.getValeur());
                retirerCarteEnMain(choix);
                ajouterCarteEnJeu(carteUtilise);

                carteUtilise.jouer(this);

                List<TypeCarte> typesCarte = carteUtilise.getTypesCarte();

                if (typesCarte.contains(TypeCarte.RAIL)){
                    if (effetsTour.contains(EffetTour.FERRONNERIE)) solder(2*effetsTour.stream().filter(effet -> effetsTour.contains(EffetTour.FERRONNERIE)).toList().size());
                    piocherUneCarteFerraille();
                    pointsRails++;
                }
                else if (typesCarte.contains(TypeCarte.GARE)) {

                    List<Tuile> tuiles = jeu.getTuiles();

                    choixPossibles.clear();
                    for (int i=0; i<tuiles.size();i++)
                        if (tuiles.get(i).estPosableGare())choixPossibles.add("TUILE:"+i);

                    if (!choixPossibles.isEmpty()) {
                        choix = choisir(getNom()+", choisissez où poser une gare",choixPossibles,null,false);
                        ajouterGare(Integer.parseInt(choix.split(":")[1]));
                    }
                    // Le joueur pioche une ferraille même si il ne peut peux pas poser de gare
                    piocherUneCarteFerraille();
                }
            }
            passeTour = false;
        }

        // Finalisation
        // À FAIRE: compléter la finalisation du tour
        finirTour();
    }

    /**
     * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
     * renvoie le choix du joueur.
     * <p>
     * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
     * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
     * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
     * autorisé à passer) soit reçu.
     * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question
     * par "oui" ou "non" :
     * <p>
     * 
     * <pre>{@code
     * List<String> choix = Arrays.asList("oui", "non");
     * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
     * }</pre>
     * <p>
     * Si par contre on voulait proposer les réponses à l'aide de boutons, on
     * pourrait utiliser :
     * 
     * <pre>{@code
     * List<String> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
     * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
     * }</pre>
     * 
     * (ici le premier bouton a le label "Oui !" et envoie la String "oui" s'il est
     * cliqué, le second a le label "Non !" et envoie la String "non" lorsqu'il est
     * cliqué)
     *
     * <p>
     * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
     * {@code false} le choix
     * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
     * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
     * {@code ""} est accepté pour éviter un blocage.
     * 
     * @param instruction message à afficher à l'écran pour indiquer au joueur la
     *                    nature du choix qui est attendu
     * @param choix       une collection de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (ou {@code null})
     * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
     *                    chaînes de caractères (label, valeur) correspondant aux
     *                    choix valides attendus du joueur qui doivent être
     *                    représentés par des boutons sur l'interface graphique (le
     *                    label est affiché sur le bouton, la valeur est ce qui est
     *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
     * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la
     *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
     *                    désire passer.
     * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
     *         d'un élément de {@code boutons} ou la chaîne vide)
     */
    public String choisir(String instruction, Collection<String> choix, List<Bouton> boutons, boolean peutPasser) {
        if (choix == null) choix = new ArrayList<>();
        if (boutons == null) boutons = new ArrayList<>();

        HashSet<String> choixDistincts = new HashSet<>(choix);
        choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
        if (peutPasser || choixDistincts.isEmpty()) {
            // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
            // ajoute "" à la liste des choix possibles
            choixDistincts.add("");
        }

        String entree;
        // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
        while (true) {
            jeu.prompt(instruction, boutons, peutPasser);
            entree = jeu.lireLigne();
            // si une réponse valide est obtenue, elle est renvoyée
            if (choixDistincts.contains(entree)) return entree;
        }
    }

    /**Ajoute un message dans le log du jeu
     * @param message message à ajouter dans le log */
    public void log(String message) {jeu.log(message);}

    @Override
    public String toString() {return String.format("=== %s (%d pts) ===\n  Argent: %d  Rails: %d\n  Cartes en jeu: %s\n  Cartes reçues: %s\n  Cartes en main: %s",
                nom, getScoreTotal(), argent, pointsRails, cartesEnJeu, cartesRecues, main);}

    /** @return le nom du joueur pour l'affichage dy jeu*/
    public String toLog() {return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);}

    /**
     * @return une représentation du joueur sous la forme d'un dictionnaire de
     * valeurs sérialisables (qui sera converti en JSON pour l'envoyer à l'interface graphique)
     */
    Map<String, Object> dataMap() {
        return Map.ofEntries(
                Map.entry("nom", nom),
                Map.entry("couleur", couleur),
                Map.entry("scoreTotal", getScoreTotal()),
                Map.entry("argent", argent),
                Map.entry("rails", pointsRails),
                Map.entry("nbJetonsRails", nbJetonsRails),
                Map.entry("main", main.dataMap()),
                Map.entry("defausse", defausse.dataMap()),
                Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                Map.entry("cartesRecues", cartesRecues.dataMap()),
                Map.entry("pioche", pioche.dataMap()),
                Map.entry("actif", jeu.getJoueurCourant() == this));
    }
}