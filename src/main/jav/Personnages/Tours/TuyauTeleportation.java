package jav.Personnages.Tours;
import jav.Maps.*;

public class TuyauTeleportation extends Tours{
    public TuyauTeleportation(RealCoordonnee pos){
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
    
}
