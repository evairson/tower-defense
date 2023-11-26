
package jav;

import java.util.Scanner;

import jav.Maps.Case;
import jav.Personnages.Luigi;
import jav.Personnages.Mario;
import jav.Personnages.Peach;
import jav.Personnages.Tours;
import jav.Personnages.TuyauTank;

public class JeuTexte {
    public static void main(String[] args){
        Game g = new Game(5, 30,5);
        g.update();
        updateUtilisateur(g);
    }
    
    public static void updateUtilisateur(Game g){
        g.getMap().afficher();
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Montrer le jeu 2: Placer une tour 3: Acheter une tour 4: Finir le jeu");
        g.getJoueur().afficheInventaire();
        int rep = Integer.valueOf(sc.nextLine());
        switch(rep){
            case 1: updateUtilisateur(g);
            case 2: placerTour(g); updateUtilisateur(g);
            case 3: acheterTour(g); updateUtilisateur(g);
            case 4: System.exit(0);
            default: updateUtilisateur(g);
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
        switch(toursJouer){
            case "mario" -> {Mario mario = new Mario(); 
                mario.setPos(x, y);
                g.addToursEnJeu(mario); 
                Case newTours = new Case();
                newTours.setContenu(mario);
                g.getMap().setCase(Integer.valueOf(x), Integer.valueOf(y), newTours);}
            case "luigi" -> {Luigi luigi = new Luigi(); 
                luigi.setPos(x, y);
                g.addToursEnJeu(luigi); 
                Case newTours = new Case();
                newTours.setContenu(luigi);
                g.getMap().setCase(Integer.valueOf(x), Integer.valueOf(y), newTours);}
            case "peach" -> {Peach peach = new Peach(); 
                peach.setPos(x, y);
                g.addToursEnJeu(peach); 
                Case newTours = new Case();
                newTours.setContenu(peach);
                g.getMap().setCase(Integer.valueOf(x), Integer.valueOf(y), newTours);}
            case "tuyauTank" -> {TuyauTank tuyauTank = new TuyauTank(); 
                tuyauTank.setPos(x, y);
                g.addToursEnJeu(tuyauTank); 
                Case newTours = new Case();
                newTours.setContenu(tuyauTank);
                g.getMap().setCase(Integer.valueOf(x), Integer.valueOf(y), newTours);}
        }
        
    



    }

    public static void acheterTour(Game g){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quelle Tours Voulez vous acheter");
        String tours = sc.nextLine();
        Tours acheter = convertStringToTours(tours);
        if (acheter.getPrix() > g.getJoueur().getMonnaie()){
            System.out.println("Vous ne pouvez pas acheter cette tours");
        }else{
            g.getJoueur().addTours(1, tours);
        }
    }
    public static Tours convertStringToTours(String tours){
        switch(tours){
            case "mario" -> {return new Mario();}
            case "luigi" -> {return new Luigi();}
            case "peach" -> {return new Peach();}
            case "tuyauTank" ->{return new TuyauTank();}
            default -> {return null;}
        }
    }

}
