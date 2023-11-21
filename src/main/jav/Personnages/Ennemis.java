package jav.Personnages;

import jav.Game;
import jav.Maps.Coordonnee;
import java.util.ArrayList;

public abstract class Ennemis implements Perso {
    protected int pv;
    protected int valeur;
    protected int vitesse;
    protected int degat;
    protected String image;
    protected int range;
    protected int vitessedegat;
    protected Coordonnee pos;
    protected boolean mort;
    protected long timeMov;
    protected long timeAttaque;

    Ennemis(){
        timeMov=System.currentTimeMillis();
        timeAttaque=System.currentTimeMillis();
    }

    public int getPv(){
        return this.pv;
    }
    public int getValeur(){
        return this.valeur;
    }
    public int getVitesse(){
        return this.vitesse;
    }
    public int getDegat(){
        return this.degat;
    }
    public int getRange(){
        return range;
    }

    public int getVitesseDegat(){
        return vitessedegat;
    }

    public Coordonnee getPos(){
        return this.pos;
    }

    public void setPos(Coordonnee c){
        pos = c;
    }

    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getX() - t.getPos().getX()<= range){
                t.enleverPv(this.degat);
            }
        }
        return false;
    }

    public void enleverPv(int degat){
        if(pv-degat >0){
            pv = pv - degat;
        }
        else {
            this.meurt();
        }
    }

    public void meurt(){
        mort=true;
    }

    public void avancer(){
        pos.setX(pos.getX()-1);
    }

    public void attaquer(ArrayList<Tours> tours){
        int i=0;
        if(tours.size()!=0){
            while(!attaque(tours.get(i)) && i<=tours.size()){
                i++;
            }
        }
    }

    public void mort(ArrayList<Ennemis> ennemis){
        ennemis.remove(this);
    }


    public void update(Game game){
        if(System.currentTimeMillis()- timeMov>vitesse){
            avancer();
            timeMov =System.currentTimeMillis();
        }

        if(System.currentTimeMillis()- timeAttaque>vitessedegat){
            attaquer(game.getToursEnJeu());
            timeAttaque =System.currentTimeMillis();
        }

        if(mort){
            mort(game.getEnnemis());
        }

        if(pos.getX()==0){
            game.gameOver();
        }
    }

}


