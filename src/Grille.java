/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmarcilh et nrichard
 */
public class Grille {

    Cellule Cellules[][] = new Cellule[6][7];
    //for (int i = 0; i < 6; i++) {​​​​

        //     for (int j = 0; j < 7; j++) {​​​​

           //      Cellules[i][j] = new Cellule();

           //  }​​​​

        // }​​​​

    public boolean ajouterJetonDansColonne(Jeton unJeton, int numColonne) {//méthode pour ajouter un jeton dans une colonne
        int i = 0;
        while (i < 6 && Cellules[i][numColonne].recuperJeton() == null) {//on vérifie 
            i++;

        }
        if (Cellules[i - 1][numColonne].recuperJeton() == null) {
            if (Cellules[i - 1][numColonne].activerTrouNoir()) {
                Cellules[i - 1][numColonne].activerTrouNoir();
            } else {

                Cellules[i - 1][numColonne].affecterJeton(unJeton);
            }
            return true;
        }
        return false; //pas rentrer dans le for = colonne pleine
    }

    public boolean etreRemplie() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (Cellules[i][j].recuperJeton() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void viderGrille() {
        for (int i = 0; i < 6; i++) {//double boucle for pour parcourir toute la grille
            for (int j = 0; j < 7; j++) {
                Cellules[i][j] = new Cellule();// à chaque cellule on recréer une nouvelle cellule
            }
        }
    }

    public void afficherGrilleSurConsole() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (Cellules[i][j].recuperJeton() == null) {//on vérifie qu'il n'y a pas de jeton
                    if (Cellules[i][j].presenceTrouNoir()) {
                        System.out.print("N ");    // La presence d'un trou noir est representé par un N
                    } else if (Cellules[i][j].presenceDesintegrateur()) {
                        System.out.print("D ");   // La presence d'un desintegrateur est representé par un D
                    } else {
                        System.out.print("X "); // une cellule vide est representé par un X
                    }
                } else {//si il y a un jeton, on affiche la couleur du jeton
                    System.out.print(Cellules[i][j].lireCouleurDuJeton() + " ");
                }
            }
            System.out.println("");
        }
    }

    public boolean celluleOccupee(int numLigne, int numColonne) {
        if (Cellules[numLigne][numColonne].recuperJeton() == null) {// si il n' a pas de jeton
            return false;
        }
        return true;
    }

    public String lireCouleurDuJeton(int numLigne, int numColonne) {
        return Cellules[numLigne][numColonne].lireCouleurDuJeton();
    }

    public boolean ligneValide(String couleur, int numLigne) {//méthode pour vérifier l'allignement de 4 jeton de meme couleur
        int a = 0;//compteur pour le nombre de jetons              //sur une ligne
                //de la meme couleur        
        for (int i = 0; i < 7; i++) {//7 colonnes de large, donc 7 tours de boucle au maximum
            if (celluleOccupee(numLigne, i) && lireCouleurDuJeton(numLigne, i).equals(couleur)) {//on fixe la ligne, et on regarde la clouleur
                a++;                                                            //et on incrémente le compteur ("on a 1 jeton de telle couleur")
                for (int j = i + 1; j < 7 && j < i + 4; j++) {//on se déplace jusqu'a 4 cellules plus loin sans soertir de la grille
                    if (celluleOccupee(numLigne, j) && lireCouleurDuJeton(numLigne, j).equals(couleur)) {
                        a++;//si les couleurs sont les memes, on incrémente le compteur
                    }//quand on a fait les 4 cases, si on atteint pas 4, on se déplace d'un rang vers la gauche et on revérifie
                }//on fait donc le test 7 fois, où on vérifie à chaque fois qu'il n'y a pas 4 jetons de la meme couleur alignées

                if (a == 4) {//si le comtpteur atteint 4, on la ligne est valide
                    return true;
                }
                a = 0;
            }
        }
        return false;

    }

    public boolean colonneValide(String couleur, int numColonne) {
        int a = 0;  // a est notre conteur de jetons de la meme couleur
        for (int i = 0; i < 6; i++) { //On parcours nos 6 lignes afin de regarder si 4 jetons de meme couleurs sont alignées
            if (celluleOccupee(i, numColonne) && lireCouleurDuJeton(i, numColonne).equals(couleur)) {
                a++;
                for (int j = i + 1; j < 6 && j < i + 4; j++) {//meme chose qu'avant, on fait le test des 4 lignes suivantes sans sortir de la grille
                    if (celluleOccupee(j, numColonne) && lireCouleurDuJeton(j, numColonne).equals(couleur)) {
                        a++;
                    }

                }
                if (a == 4) {
                    return true;
                }
                a = 0;
            }
        }
        return false;
    }

    public boolean diagonaleValide(String couleur, int numColonne, int numLigne) {
        int a = 0;// a est notre conteur de jetons de la meme couleur

        for (int i = 0; i < 7; i++) {//on fait 7 boucles pour faire toute la ligne
            for (int j = 0; i < 6; j++) {
                if (celluleOccupee(numLigne, numColonne) && lireCouleurDuJeton(numLigne, numColonne).equals(couleur)) {//on se place à l'endroit ou on est
                    a++;//on incrémente le compteur de jetons                                                             //pour récuperer la couleur du jeton
                    for (int x = j + 1; x < 6 && x < j + 4; x++) {
                        for (int y = i + 1; y < 7 && y < i + 4; y++) {
                            if (celluleOccupee(x, y) && lireCouleurDuJeton(x, y).equals(couleur)) {//on compare la oculeur du jeton d'avant a celle de celui de la 
                                a++;//on incrémente si c'est le cas                                //la case d'à coté
                            }//des que ce n'est plus le cas on sort de la boucle et on continue la partie
                        }
                    }
                    if (a == 4) {//si le compteur atteint 4, on renvoie true, pour la suite 
                        return true;
                    }
                    a = 0;
                }

            }
        }
        return false;
    }

    public boolean etreGagnantPourJoueur(Joueur unJoueur) {  
        String couleur = unJoueur.lireCouleur();
        for (int i = 0; i < 6; i++) {//on lance le test pour les 6 lignes
            if (ligneValide(couleur, i)) {
                return true;
            }

        }
        for (int i = 0; i < 7; i++) {//on lance le test pour les 7 colonnes
            if (colonneValide(couleur, i)) {
                return true;
            }
        }
       // for (int i = 0; i < 7; i++) {
         //   for (int j = 0; i < 6; j++) {
           //     if (diagonaleValide(couleur, i, j)) {
             //       return true;
               // }
            //}
       // }
        return false;
    }



    

    public void tasserGrille(int uneColonne) {
        int i = 5;//on se place en haut d'une colonne

        while (i > 0 && celluleOccupee(i, uneColonne)) {//et on descend progressivement tant qu'il y a des jetons
            i--;

        }

        if (i != 0) {//permet de s'arreter quand on a fait toutes les lignes
            Jeton unJeton;
            for (int j = i; j > 0; j--) {
                unJeton = Cellules[j - 1][uneColonne].recuperJeton();//on récupere le jeton (si il y en a un) à la ligne en dessous de j
                if (unJeton == null) {//si il n'y a pas de jeton à la ligne sous j
                    Cellules[j][uneColonne].supprimerJeton();//on supprime le jeton à la ligne j
                    Cellules[j][uneColonne].affecterJeton(null);
                } else {
                   Cellules[j][uneColonne].affecterJeton(unJeton);
                }
            }
            Cellules[0][uneColonne].affecterJeton(null);
        }
    }

    public boolean colonneRemplie(int uneColonne) {//on se place dans une colonne
        for (int i = 0; i < 6; i++) {
            if (!celluleOccupee(i, uneColonne)) {//on vérifie si une ligne de cette colonne n'est pas occupée
                return false;
            }

        }
        return true;
    }

    public boolean placerTrouNoir(int numLigne, int numColonne) {//on place le trou noir dans la cellule numLigne/numColonne
        if (Cellules[numLigne][numColonne].placerTrouNoir()) {
            return true;
        }
        return false;
    }

    public boolean placerDesintegrateur(int numLigne, int numColonne) {//on place le desintegrateur dans la cellule numLigne/numColonne
        if (Cellules[numLigne][numColonne].placerDesintegrateur()) {
            return true;
        }
        return false;
    }

    public boolean supprimerJeton(int numLigne, int numColonne) {//on supprime le jeton à l'emplacement requis
        if (Cellules[numLigne][numColonne].supprimerJeton()) {
            return true;

        }
        return false;
    }

    public Jeton recupererJeton(int numLigne, int numColonne) {
        Jeton unJeton = Cellules[numLigne][numColonne].recuperJeton();
        if (unJeton != null) {//si le jeton existe
            Cellules[numLigne][numColonne].supprimerJeton();//on l'enleve de la grille

        }
        return unJeton;//et on le rend a son propriétaire
    }
}
