package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Perso;

import java.util.ArrayList;

public class Tours extends Perso{
    protected int prix;
    protected int niveau;
    protected int vitessedegat;

    public int getVitesseDegat(){
        return vitessedegat;
    }

    public int getPrix(){
        return this.prix;
    }
    public int getNiveau(){
        return this.niveau;
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
