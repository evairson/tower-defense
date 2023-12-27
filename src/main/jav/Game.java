package jav;

import jav.Exception.DeuxToursMemeCase;
import jav.Maps.Case;
import jav.Maps.Plateau;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Boo;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Ennemis.Goomba;
import jav.Personnages.Ennemis.Koopa;
import jav.Personnages.Ennemis.Lakitu;
import jav.Personnages.Pouvoirs.Fleur;
import jav.Personnages.Tours.Luigi;
import jav.Personnages.Tours.Mario;
import jav.Personnages.Tours.Peach;
import jav.Personnages.Tours.Tours;
import jav.Personnages.Tours.Tank;
import jav.Personnages.Tours.Tuyau;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Plateau map;

    private Joueur joueur;
    private ArrayList<Tours> toursEnJeu;
    private ArrayList<Ennemis> ennemis;
    private long timeEnnemi;
    private boolean end;
    private int vitesseApparition;
    private int typeEnnemi; // un entier qui augmente petit à petit pour ajouter des ennemis plus dur
    private int nbEnnemis; // nombre d'ennemis qui apparaissent dans une partie (doit être un diviseur de 100)
    private GameView view;
    public static int sizecase;

    public ArrayList<Ennemis> getEnnemis() {
        return ennemis;
    }

    public Plateau getMap(){
        return map;
    }

    public ArrayList<Tours> getToursEnJeu() {
        return toursEnJeu;
    }
    public void addToursEnJeu(Tours t){
        toursEnJeu.add(t);
    }

    public GameView getView(){
        return view;
    }

    public void gameOver(){
        end = true;
    }
    public Joueur getJoueur(){
        return this.joueur;
    }
    

    public Game(int longeur, int largeur, int nbEnnemis, GameView view){
        this.view = view;
        end = false;
        timeEnnemi = System.currentTimeMillis();
        joueur = new Joueur();
        map = new Plateau(longeur,largeur);
        ennemis = new ArrayList<Ennemis>();
        toursEnJeu = new ArrayList<Tours>();
        vitesseApparition = 8000;
        typeEnnemi=0;
        if(100%nbEnnemis==0){
            this.nbEnnemis=nbEnnemis;
        }
        else {
            this.nbEnnemis= nbEnnemis-100%nbEnnemis;
        }
    }

    public void update(){
        Game g = this;
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask()
         {
            
            public void run() {
                if(view!=null){
                    view.setImage();
                }

                
                try {
                    map.updateContenu(g);
                } catch(DeuxToursMemeCase exc){
                    System.out.println("Attention Deux tours sur la même case !!");
                    exc.changeTour(g);
                }
                 
                for(Case[] line : map.getGrid()){
                    for(Case cas : line){
                        if(cas.getContenuTours()!=null){
                            cas.getContenuTours().update(g);
                        }
                        if(cas.getContenuEnnemis().size()!= 0){
                            for(Ennemis e : cas.getContenuEnnemis()){
                                e.update(g);
                            }
                        }
                    }
                }

                if(typeEnnemi<=100){
                ajouterEnnemi();
                }

                if(end){
                    System.out.println("Vous avez perdu");
                    t.cancel();
                    view.control.afficheGameOver();
                }

                }},
                 0,
                 10);
        }

    public Ennemis selecEnnemi(){
        
            int i = typeEnnemi + (int)(Math.random()*(30));
            typeEnnemi += 100/nbEnnemis;
            if(i<50) return new Goomba();
            if(i<75) return new Koopa();
            if(i<100) return new Boo();
            if(i<=130) return new Lakitu();
            
        return null;

    }


    public void ajouterEnnemi(){
        
        if(System.currentTimeMillis() - timeEnnemi> vitesseApparition){
            vitesseApparition -= 7000/nbEnnemis; //(8 000 - nbennemis * x = 1000)
            int i = (int)(Math.random()*(map.getLongeur()));
                Ennemis e = selecEnnemi();
                ennemis.add(e);
                e.setPos(new RealCoordonnee(map.getLargeur()-1, i));
                try {

                    map.updateContenu(this);
                } catch(DeuxToursMemeCase exc){
                    System.out.println("Attention Deux tours sur la même case !!");
                    exc.changeTour(this);
                }
            timeEnnemi=System.currentTimeMillis();
        }
    }

    public  boolean canUsePower(int x,int y){
        return this.getMap().getCase(x, y).getContenuTours() instanceof Peach || this.getMap().getCase(x, y).getContenuTours() instanceof Mario || this.getMap().getCase(x, y).getContenuTours() instanceof Luigi;
    }



    public void createTours(String toursJouer,int x,int y){
        if(toursJouer.equals("mario") || toursJouer.equals("luigi") || toursJouer.equals("peach") || toursJouer.equals("tank") || toursJouer.equals("tuyau")){
            if (this.getJoueur().getInventaire().get(toursJouer) >= 1){
                Tours tour = switch (toursJouer) {
                    case "mario"->new Mario(new RealCoordonnee(x, y)); 
                    case "luigi"->new Luigi(new RealCoordonnee(x, y)); 
                    case "peach"->new Peach(new RealCoordonnee(x, y)); 
                    case "tank" ->new Tank(new RealCoordonnee(x, y)); 
                    case "tuyau" -> new Tuyau(new RealCoordonnee(x, y));
                    default -> new Mario(new RealCoordonnee(x, y)); 
                };
                this.addToursEnJeu(tour); 
                this.getMap().getCase(x,y).setContenuTours(tour);
                this.getJoueur().removeTours(1, toursJouer);
            }
            else{
                if(view==null){
                    System.out.println("Vous ne pouvez pas poser "+ toursJouer);
                }
                }
        }
        else if (toursJouer.equals("fleur") || toursJouer.equals("etoile")){
            if (this.getMap().getCase(x, y).getContenuTours() == null){
                System.out.println("Vous ne pouvez pas utiliser de pouvoirs sur une case où il n'y a rien");
                }
            else{
                if (this.getJoueur().getInventaire().get(toursJouer)>= 1 && this.canUsePower(x, y)){
                    if(toursJouer.equals("fleur")) {
                        ((Fleur)(this.getMap().getCase(x, y).getContenuTours())).toFlower();}
                    else {
                       // ((Tours)(this.getMap().getCase(x, y).getContenuTours())).toStar();
                    }
                    this.getJoueur().removeTours(1, toursJouer);
                }
                else{
                    System.out.println("Vous ne pouvez pas poser "+ toursJouer);
                }
            }
        }
    }
    
}
