package jav;

import jav.Maps.Case;
import jav.Maps.Coordonnee;
import jav.Maps.Plateau;
import jav.Personnages.Ebasique;
import jav.Personnages.Ennemis;
import jav.Personnages.Tours;
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

    public ArrayList<Ennemis> getEnnemis() {
        return ennemis;
    }

    public Plateau getMap(){
        return map;
    }

    public ArrayList<Tours> getToursEnJeu() {
        return toursEnJeu;
    }

    public void gameOver(){
        end = true;
    }
    

    Game(int longeur, int largeur){
        end = false;
        time = System.currentTimeMillis();
        timeEnnemi = System.currentTimeMillis();
        joueur = new Joueur();
        map = new Plateau(longeur,largeur);
        ennemis = new ArrayList<Ennemis>();
        toursEnJeu = new ArrayList<Tours>();
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
                ajouterEnnemi();

                if(end){
                    System.out.println("Vous avez perdu");
                    System.exit(0);
                }

                }},
                 0,
                 200);
        }


    public void ajouterEnnemi(){
        if( System.currentTimeMillis()- timeEnnemi>4000){
            int i = (int)(Math.random()*(map.getLongeur()));
                if(map.getGrid()[i][map.getLargeur()-1].getContenu()==null){
                    Ennemis e = new Ebasique();
                    ennemis.add(e);
                    e.setPos(new Coordonnee(map.getLargeur()-1, i));
                }
            timeEnnemi=System.currentTimeMillis();
        }
        }
    
}
