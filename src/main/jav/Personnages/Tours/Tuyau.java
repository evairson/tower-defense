package jav.Personnages.Tours;
import javax.swing.plaf.basic.BasicSliderUI.ScrollListener;

import jav.Game;
import jav.Maps.*;
import jav.Personnages.Ennemis.Ennemis;

public class Tuyau extends Tours{
    
    public Tuyau(RealCoordonnee pos){
        super();
        prix = 30;
        this.pos = pos;
        url = "tours/Mario_pipe.png";
        lettre = "TT";
        url = "tours/tuyau/tuyau";
        nbimageAnimation = 1;
        scale = 0.5;
    }

    public void attaque(Ennemis e,int gridLength){
        e.setPos(new RealCoordonnee((int)e.getPos().getX(),(gridLength-1)));
    }



    public boolean warp(Ennemis e, Game g){
        if(e.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY()){
            if(e.getPos().getX() - pos.getX() <= Game.sizecase/3){
                e.setPos(new RealCoordonnee(g.getMap().getLargeur()-1, e.getPos().getIntCoordonnee().getY()));
                this.mort = true;
                return true;
            }
        }
        return false;
    }

    public void pouvoir(Game g){
        int i=0;
        if(g.getEnnemis().size()!=0){
            while(i<g.getEnnemis().size()){
                if(!warp(g.getEnnemis().get(i), g)){
                    i++;
                }
                else{
                    timeAttaque = System.currentTimeMillis();
                    return;
                } 
            }
        }
    }
}
