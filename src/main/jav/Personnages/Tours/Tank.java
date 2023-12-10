package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.RealCoordonnee;

public class Tank extends Tours{
    public Tank(RealCoordonnee pos){
        super();
        pv=300;
        prix = 30;
        mort = false;
        this.pos = pos;
        lettre = "T ";
    }


    public void toFlower() {
        System.out.println("vous ne pouvez pas sur cette tour");
    }


    public void toStar() {
        System.out.println("vous ne pouvez pas sur cette tour");
    }

    public void pouvoir(Game g){

    }
    
}
