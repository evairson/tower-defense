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
    public void toFlower(String image){
        this.image = image;
        switch(this.niveau){
            case 0 -> {this.pv = 100; this.degat+=15;this.niveau++;return;}
            case 2 -> {this.pv = 100;this.degat-=15;this.niveau--;return;}
            default -> {return;}
        }
    }

    public void toStar(String image){
        this.image = image;
        switch(this.niveau){
            case 0 -> {this.pv = 150;this.degat+=35;this.niveau+=2;return;}
            case 1 -> {this.pv = 150;this.degat+=15;this.niveau++;return;}
            default -> {return;}
        }
    }
}
