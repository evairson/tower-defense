package jav.Personnages.Tours;

import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public class TuyauTank extends Tours{
    public TuyauTank(RealCoordonnee pos){
        pv=300;
        degat = 0;
        prix = 30;
        niveau= 0;
        range = 0;
        vitessedegat = 0;
        mort = false;
        this.pos = pos;
        lettre = "T";
    }

    @Override
    public void toFlower() {
        System.out.println("vous ne pouvez pas sur cette tour");
    }

    @Override
    public void toStar() {
        System.out.println("vous ne pouvez pas sur cette tour");
    }
    
}
