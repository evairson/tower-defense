
package jav;

import jav.Personnages.Tours.Luigi;
import jav.Personnages.Tours.Mario;
import jav.Personnages.Tours.Peach;
import jav.Personnages.Tours.*;
import java.util.Scanner;


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
        createTours(g, toursJouer, x, y);

    }


    public static void acheterTour(Game g){
        Scanner sc = new Scanner(System.in);
        g.getJoueur().afficheBoutique();
        String tours = sc.nextLine();
        if (g.getJoueur().getBoutique().get(tours) > g.getJoueur().getMonnaie()){
            System.out.println("Vous ne pouvez pas acheter cet objet");
        }else{
            g.getJoueur().addTours(1, tours);
        }
    }
    public static void createTours(Game g,String toursJouer,int x,int y){
        switch(toursJouer){
            case "mario" -> {if (g.getJoueur().getInventaire().get("mario") >= 1){
                Mario mario = new Mario(); 
                mario.setPos(x, y);
                g.addToursEnJeu(mario); 
                g.getMap().getCase(x,y).setContenu(mario);
                g.getJoueur().removeTours(1, toursJouer);
                break;
            }else{
                System.out.println("Vous ne pouvez pas poser "+ toursJouer);break;
            }}
            case "luigi" -> { if (g.getJoueur().getInventaire().get("luigi") >= 1){
                Luigi luigi = new Luigi(); 
                luigi.setPos(x, y);
                g.addToursEnJeu(luigi); 
                g.getMap().getCase(x,y).setContenu(luigi);
                g.getJoueur().removeTours(1, toursJouer);
                break;
                }else{
                    System.out.println("Vous ne pouvez pas poser "+ toursJouer);break;
                }}
            case "peach" -> {if (g.getJoueur().getInventaire().get("peach") >= 1){
                Peach peach = new Peach(); 
                peach.setPos(x, y);
                g.addToursEnJeu(peach); 
                g.getMap().getCase(x,y).setContenu(peach);
                g.getJoueur().removeTours(1, toursJouer);
                break;
                }else{
                    System.out.println("Vous ne pouvez pas poser "+ toursJouer);break;
                }}
            case "tuyauTank" -> {if (g.getJoueur().getInventaire().get("tuyauTank") >= 1){
                TuyauTank tuyauTank = new TuyauTank(); 
                tuyauTank.setPos(x, y);
                g.addToursEnJeu(tuyauTank); 
                g.getMap().getCase(x,y).setContenu(tuyauTank);
                g.getJoueur().removeTours(1, toursJouer);
                break;
                }else{
                    System.out.println("Vous ne pouvez pas poser "+ toursJouer);break;
                }}
            case "TuyauTeleportation " -> {if (g.getJoueur().getInventaire().get("TuyauTeleportation")>=1){
                TuyauTeleportation tuyau = new TuyauTeleportation();
                tuyau.setPos(x, y);
                g.addToursEnJeu(tuyau);
                g.getMap().getCase(x, y).setContenu(tuyau);
                g.getJoueur().removeTours(1, toursJouer);
                break;
                }else{
                    System.out.println("Vous ne pouvez pas poser "+ toursJouer);break;
                } }
            case "fleur" -> {if (g.getMap().getCase(x, y).getContenu() == null){
                System.out.println("Vous ne pouvez pas utiliser de pouvoirs sur une case où il n'y a rien");
                break;
                }else{
                    if (g.getJoueur().getInventaire().get("fleur")>= 1){
                    g.getMap().getCase(x, y).getContenu().toFlower(imageUrl(toursJouer));
                    g.getJoueur().removeTours(1, toursJouer);
                    break;
                    }else{
                        System.out.println("Vous ne pouvez pas poser "+ toursJouer);break;
                    }
                }
            }
            case "étoile" -> {if (g.getMap().getCase(x, y).getContenu() == null){
                System.out.println("Vous ne pouvez pas utiliser de pouvoirs sur une case où il n'y a rien");
                break;
                }else{
                    if (g.getJoueur().getInventaire().get("étoile")>=1){
                    g.getMap().getCase(x, y).getContenu().toStar(imageUrl(toursJouer));
                    g.getJoueur().removeTours(1, toursJouer);
                    break;
                    }else{
                        System.out.println("Vous ne pouvez pas poser "+ toursJouer);break;
                    }

                }
            }
            default -> { placerTour(g); break; }
        }

    }
    public static boolean canUsePower(Game g,int x,int y){
        return g.getMap().getCase(x, y).getContenu() instanceof Peach || g.getMap().getCase(x, y).getContenu() instanceof Mario || g.getMap().getCase(x, y).getContenu() instanceof Luigi;

    }
    public static String imageUrl(String Tours){
        return "";

    }

}
