package jav.Personnages.Tours;

import jav.Game;
import jav.Personnages.Perso;
import jav.Personnages.Ennemis.Ennemis;

import java.util.ArrayList;

public abstract class Tours extends Perso{
    protected int prix;
    protected int niveau;


    public Tours(){
        timeAttaque=System.currentTimeMillis();
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


    public boolean attaque(Ennemis e){
        if(e.getPos().getY()==pos.getY()){
            if(e.getPos().getIntCoordonnee().getX() - this.pos.getIntCoordonnee().getX() <= range){
                e.enleverPv(this.degat);
            }
        }
        return false;
    }

    public void attaquer(ArrayList<Ennemis> e){
        int i=0;
        if(e.size()!=0){
            while(i<e.size() && !attaque(e.get(i))){
                i++;
            }
        }
    }


    public void update(Game game){
        this.pouvoir(game);

        if(System.currentTimeMillis() - timeAttaque > timebetweendegat){
            attaquer(game.getEnnemis());
            timeAttaque =System.currentTimeMillis();
        }

        if(mort){
            mort(game.getToursEnJeu());
            image.setIcon(null);
        }
    }
    
    public abstract void toFlower();

    public abstract void toStar();

}
