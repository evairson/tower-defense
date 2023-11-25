package jav.Personnages.Tours;

import jav.Game;
import jav.Personnages.Ennemis.Ennemis;

import java.util.ArrayList;

public class TourAttaque extends Tours {


    public boolean attaque(Ennemis e){
        if(e.getPos().getY()==pos.getY()){
            if(this.pos.getX() - e.getPos().getX()<= range){
                e.enleverPv(this.degat);
            }
        }
        return false;
    }
    
    public void attaquer(ArrayList<Ennemis> ennemis){
        int i=0;
        while(!attaque(ennemis.get(i)) && i<=ennemis.size()){
            i++;
        }
    }

    @Override
    public void update(Game game){
        attaquer(game.getEnnemis());

        super.update(game);
    }
}
