package jav.Personnages.Tours;
import jav.Maps.*;

public class TuyauTeleportation extends Tours{
    public TuyauTeleportation(){
    pv=1;                      //pv symbolique puisque normalement il ne devrait pas être attaqué
    degat = 0;
    prix = 30;
    niveau= 0;
    range = 0;
    mort = false;
    pos = new Coordonnee(0, 0);

    }
    



    
}
