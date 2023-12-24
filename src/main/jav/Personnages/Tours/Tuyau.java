package jav.Personnages.Tours;
import jav.Game;
import jav.Maps.*;
import jav.Personnages.Ennemis.Ennemis;

public class Tuyau extends Tours{
    
    public Tuyau(RealCoordonnee pos){
        super();
        pv=1;                      //pv symbolique puisque normalement il ne devrait pas être attaqué
        degat = 0;
        prix = 30;
        niveau= 0;
        range = 0;
        mort = false;
        this.pos = pos;
        lettre = "TT";
    }

    @Override
    public void toFlower() {
        System.out.println("vous ne pouvez pas sur cette tour");
    }

    @Override
    public void toStar() {
        System.out.println("vous ne pouvez pas sur cette tour");
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
