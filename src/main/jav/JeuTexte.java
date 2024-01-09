
package jav;

import jav.gui.*;
import jav.Exception.choisirToursException;
import jav.Exception.choixActionException;
import jav.Maps.Plateau;
import java.util.Scanner;

public class JeuTexte {

    public JeuTexte(int longeur, int largeur, int nbEnnemis, int levelDificulty){
        Game.sizecase = 100;
        Game g = new Game(longeur, largeur,nbEnnemis, null, levelDificulty);
        g.update();
        tryUpdateUtilisateur(g);

    }

    public static void tryUpdateUtilisateur(Game g){
        try{       
            Scanner sc = new Scanner(System.in);
            updateUtilisateur(g, sc);

       } catch (choixActionException e){
            System.out.println("Vous avez mal écris votre action recommencez :");
           tryUpdateUtilisateur(g);
       }
    }
    
    public static void updateUtilisateur(Game g, Scanner sc) throws choixActionException{
        g.getMap().afficher();
        System.out.println();
        System.out.println("1: Montrer le jeu 2: Placer une tour 3: Acheter une tour 4: Finir le jeu");
        g.getJoueur().afficheInventaire();
        String reponse = sc.nextLine();
        switch(reponse){
            case "1": tryUpdateUtilisateur(g); break;
            case "2": placerTour(g); tryUpdateUtilisateur(g); break;
            case "3": acheterTour(g); tryUpdateUtilisateur(g); break;
            case "4": System.exit(0); break;
            default: throw new choixActionException();
        }
    }

    public static void placerTour(Game g){
        Scanner sc = new Scanner(System.in);
        g.getJoueur().afficheInventaire();
        String toursJouer = "";
        try{
            toursJouer = choisirTour(sc);
        } catch(choisirToursException e){
            System.out.println("Vous avez mal écrit le nom de la tour. Recommencez :");
            placerTour(g);
            return;
        }
        if (g.getJoueur().getInventaire().get(toursJouer) == 0){
            System.out.println("Vous n'avez plus de " + toursJouer);
            return;
        }
        try{
            choisirCor(g, sc, toursJouer);
        } catch  (choisirToursException e){
            System.out.println("vous n'avez pas écris les bonnes coordonnées. Recommencez :");
            placerTour(g);
        }  
    }

    public static String choisirTour(Scanner sc) throws choisirToursException{
        System.out.println("Choissez la tour à jouer");
        String toursJouer =sc.nextLine();
        if(!(toursJouer.equals("mario") ||toursJouer.equals("luigi") || toursJouer.equals("peach") ||toursJouer.equals("tank") 
        || toursJouer.equals("tuyau") ||toursJouer.equals("etoile") || toursJouer.equals("fleur"))){
            throw new choisirToursException();
        }
        return toursJouer;
    }

    public static void choisirCor(Game g, Scanner sc, String toursJouer) throws choisirToursException, StringIndexOutOfBoundsException{
        System.out.println("Choisissez les coordonnées (ex : A3) de "+toursJouer);
        String coordString = sc.nextLine();
        int x = 0;
        if(coordString.substring(1).length()==2){
            try {
                x = Integer.parseInt(String.valueOf(coordString.substring(1, 3)));
            }
            catch (NumberFormatException f) {
                throw new choisirToursException();
            }
        }
        else if(coordString.substring(1).length()==1){
            try {
                x = Integer.parseInt(String.valueOf(coordString.substring(1)));
            }
            catch (NumberFormatException f) {
                throw new choisirToursException();
            }
        }
        else {
            throw new choisirToursException();
        } 

        if(Plateau.alphabet.indexOf(coordString.charAt(0))==-1){
            throw new choisirToursException();
        }
        if(x<1 || x > g.getMap().getLargeur()){
            throw new choisirToursException();
        }
        int y = Plateau.alphabet.indexOf(coordString.charAt(0));
        if(y > g.getMap().getLongeur()){
            throw new choisirToursException();
        }
        g.createTours(toursJouer, x-1, y); 
    }

    public static void acheterTour(Game g){
        Scanner sc = new Scanner(System.in);
        g.getJoueur().afficheBoutique();
        String tours = sc.nextLine();
        if(g.getJoueur().getBoutique().get(tours) != null){
            if (g.getJoueur().getBoutique().get(tours) > g.getJoueur().getMonnaie()){
                System.out.println("Vous ne pouvez pas acheter cet objet");
            }else{
                g.getJoueur().acheter(tours);
            }
        }
        else {
            tryUpdateUtilisateur(g);
        }
    }
}
