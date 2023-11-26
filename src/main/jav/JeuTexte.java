package jav;

import java.util.Scanner;

import jav.Personnages.Tours.Tours;

public class JeuTexte {

    public JeuTexte(int longeur, int largeur, int nbEnnemis){
        Game g = new Game(longeur, largeur,nbEnnemis);
        g.update();
        updateUtilisateur(g);
    }
    
    public static void updateUtilisateur(Game g){
        g.getMap().afficher();
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Montrer le jeu 2: Placer une tour 3: Acheter une tour 4: Finir le jeu");
        int rep = Integer.valueOf(sc.nextLine());
        switch(rep){
            case 1: updateUtilisateur(g);
            case 2: placerTour(); updateUtilisateur(g);
            case 3: acheterTour(); updateUtilisateur(g);
            case 4: System.exit(0);
            default: updateUtilisateur(g);
        }
    }

    public static void placerTour(){

    }

    public static void acheterTour(){

    }

}
