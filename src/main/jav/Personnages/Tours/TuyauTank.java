package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public class TuyauTank extends Tours{
    public TuyauTank(RealCoordonnee pos){
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
