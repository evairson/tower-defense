package jav;

import jav.Maps.Case;
import jav.Maps.Coordonnee;
import jav.Maps.Plateau;
import jav.Personnages.Tours;
import jav.Personnages.Ennemis.Boo;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Ennemis.Goomba;
import jav.Personnages.Ennemis.Koopa;
import jav.Personnages.Ennemis.Lakitu;
import jav.Personnages.Ennemis.Plante;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Plateau map;
    private Joueur joueur;
    private ArrayList<Tours> toursEnJeu;
    private ArrayList<Ennemis> ennemis;
    private long time;
    private long timeEnnemi;
    private boolean end;
    private int vitesseApparition;
    private int typeEnnemi; // un entier qui augmente petit à petit pour ajouter des ennemis plus dur
    private int nbEnnemis; // nombre d'ennemis qui apparaissent dans une partie (doit être un diviseur de 100)

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

    public void gameOver(){
        end = true;
    }
    public Joueur getJoueur(){
        return this.joueur;
    }
    

    Game(int longeur, int largeur, int nbEnnemis){
        end = false;
        time = System.currentTimeMillis();
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
                
                map.updateContenu(g);
                 
                for(Case[] line : map.getGrid()){
                    for(Case cas : line){
                        if(cas.getContenu()!=null){
                            cas.getContenu().update(g);
                        }  
                    }
                }

                if(typeEnnemi<=100){
                ajouterEnnemi();
                }

                if(end){
                    System.out.println("Vous avez perdu");
                    System.exit(0);
                }

                }},
                 0,
                 200);
        }

    public Ennemis selecEnnemi(){
        
            int i = (int)(Math.random()*(typeEnnemi));
            System.out.println(i);
            System.out.println(nbEnnemis);
            typeEnnemi += 100/nbEnnemis;
            if(i<30) return new Goomba();
            if(i<50) return new Koopa();
            if(i<65) return new Plante();
            if(i<85) return new Boo();
            if(i<100) return new Lakitu();
            
        
        return null;

    }


    public void ajouterEnnemi(){
        
        if(System.currentTimeMillis() - timeEnnemi> vitesseApparition){
            vitesseApparition -= 7000/nbEnnemis; //(8 000 - nbennemis * x = 1000)
            int i = (int)(Math.random()*(map.getLongeur()));
                if(map.getGrid()[i][map.getLargeur()-1].getContenu()==null){
                    Ennemis e = selecEnnemi();
                    ennemis.add(e);
                    e.setPos(new Coordonnee(map.getLargeur()-1, i));
                    map.updateContenu(this);
                    map.afficher();
                }
            timeEnnemi=System.currentTimeMillis();
        }
        }
    
}
