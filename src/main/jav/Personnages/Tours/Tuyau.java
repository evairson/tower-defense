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
        url = "tours/Mario_pipe.png";
        lettre = "TT";
    }
    


    @Override
    public void toFlower() {
        System.out.println("vous ne pouvez pas sur cette tour");
    }

    public void attaque(Ennemis e,int gridLength){
        e.setPos(new RealCoordonnee((int)e.getPos().getX(),(gridLength-1)));
    }

    @Override
    public void toStar() {
        System.out.println("vous ne pouvez pas sur cette tour");
    }

    public void pouvoir(Game g){

    }
}
