package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Perso;

import java.util.ArrayList;

public abstract class Tours extends Perso{
    protected int prix;
    protected int niveau;

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
        this.pouvoir(game);


        if(mort){
            mort(game.getToursEnJeu());
        }
    }
    
    public abstract void toFlower();

    public abstract void toStar();


}
