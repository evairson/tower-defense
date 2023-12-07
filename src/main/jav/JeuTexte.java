
package jav;

import jav.Personnages.Tours.Luigi;
import jav.Personnages.Tours.Mario;
import jav.Personnages.Tours.Peach;
import jav.Exception.choisirCoorToursException;
import jav.Maps.Plateau;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Tours.*;

import java.io.EOFException;
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
        System.out.println();
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
        System.out.println("Choissez la tour à jouer");
        String toursJouer =sc.nextLine();
        if (g.getJoueur().getInventaire().get(toursJouer) == 0){
            System.out.println("Vous n'avez plus de " + toursJouer);
            toursJouer = sc.nextLine();
        }
        try{
            choisirCor(g, sc, toursJouer);
        } catch  (choisirCoorToursException e){
            System.out.println("vous n'avez pas écris les bonnes coordonnées. Recommencez :");
        }  
        
    }

    public static void choisirCor(Game g, Scanner sc, String toursJouer) throws choisirCoorToursException{
        System.out.println("Choisissez les coordonnées (ex : A3) de "+toursJouer);
        String coordString = sc.nextLine();
        try {
            Integer.parseInt(String.valueOf(coordString.charAt(1)));
        }
        catch (NumberFormatException e) {
            throw new choisirCoorToursException();
        }
        int x = Integer.parseInt(String.valueOf(coordString.charAt(1)));
        if(Plateau.alphabet.indexOf(coordString.charAt(0))==-1){
            throw new choisirCoorToursException();
        }
        if(x<1 || x > g.getMap().getLargeur()){
            throw new choisirCoorToursException();
        }
        int y = Plateau.alphabet.indexOf(coordString.charAt(0));
        if(y > g.getMap().getLongeur()){
            throw new choisirCoorToursException();
        }
        g.createTours(toursJouer, x-1, y);
        
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
