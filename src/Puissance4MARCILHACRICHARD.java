
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marci et nrichard
 */
public class Puissance4MARCILHACRICHARD {

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Quel est le nom de joueurs 1 : ");//on demande au début du jeu le nom des joueurs
        Scanner sc;
        sc = new Scanner(System.in);
        String nomJoeur1 = sc.nextLine();

        System.out.println("Quel est le nom de joueurs 2 : ");
        sc = new Scanner(System.in);
        String nomJoeur2 = sc.nextLine();
        Partie uneNouvellePartie = new Partie();//une fois les noms enregistrés, on lance la partie
        uneNouvellePartie.debuterPartie();
    }

}
