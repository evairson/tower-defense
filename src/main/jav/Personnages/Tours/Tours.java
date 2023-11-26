package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Personnages.Perso;

import java.util.ArrayList;

public class Tours implements Perso{
    protected int pv;
    protected int degat;
    protected int prix;
    protected int niveau;
    protected String image;
    protected int range;
    protected int vitessedegat;
    protected Coordonnee pos;
    protected boolean mort;

    public int getPv(){
        return this.pv;
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

    public int getPrix(){
        return this.prix;
    }
    public int getNiveau(){
        return this.niveau;
    }

    public Coordonnee getPos(){
        return this.pos;
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

    public void mort(ArrayList<Tours> tours){
        tours.remove(this);
    }


    public void update(Game game){

        if(mort){
            mort(game.getToursEnJeu());
        }
    }


}
