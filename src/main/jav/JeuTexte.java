
package jav;

import jav.Personnages.Tours.Luigi;
import jav.Personnages.Tours.Mario;
import jav.Personnages.Tours.Peach;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Tours.*;
import java.util.Scanner;

public class JeuTexte {

    public JeuTexte(int longeur, int largeur, int nbEnnemis){
        Game.sizecase = 100;
        Game g = new Game(longeur, largeur,nbEnnemis, null);
        g.update();
        updateUtilisateur(g);
    }
    
    public static void updateUtilisateur(Game g){
        g.getMap().afficher();
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Montrer le jeu 2: Placer une tour 3: Acheter une tour 4: Finir le jeu");
        g.getJoueur().afficheInventaire();
        String reponse = sc.nextLine();
        switch(reponse){
            case "1": updateUtilisateur(g); break;
            case "2": placerTour(g); updateUtilisateur(g); break;
            case "3": acheterTour(g); updateUtilisateur(g); break;
            case "4": System.exit(0); break;
            default: updateUtilisateur(g); break;
        }
    }

    public static void placerTour(Game g){
        Scanner sc = new Scanner(System.in);
        g.getJoueur().afficheInventaire();
        System.out.println("Veulliez choisir quelle tour vous voulez jouer");
        String toursJouer =sc.nextLine();
         if (g.getJoueur().getInventaire().get(toursJouer) == 0){
            System.out.println("Vous n'avez plus de " + toursJouer);
            toursJouer = sc.nextLine();
        }
        System.out.println("Sur quelle ligne voulez vous poser "+toursJouer);
        int x = Integer.valueOf(sc.nextLine());
        System.out.println("Sur quelle colone voulez vous poser "+toursJouer);
        int y = Integer.valueOf(sc.nextLine());
        g.createTours(toursJouer, x, y);

    }


    public static void acheterTour(Game g){
        Scanner sc = new Scanner(System.in);
        g.getJoueur().afficheBoutique();
        String tours = sc.nextLine();
        if (g.getJoueur().getBoutique().get(tours) > g.getJoueur().getMonnaie()){
            System.out.println("Vous ne pouvez pas acheter cet objet");
        }else{
            g.getJoueur().addTours(1, tours);
            g.getJoueur().acheter(g.getJoueur().getBoutique().get(tours));
        }
    }

}
