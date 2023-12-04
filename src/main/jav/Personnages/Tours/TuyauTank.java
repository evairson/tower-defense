package jav.Personnages.Tours;

import jav.Maps.Coordonnee;

public class TuyauTank extends Tours{
    public TuyauTank(){
        pv=300;
        degat = 0;
        prix = 30;
        niveau= 0;
        range = 0;
        vitessedegat = 0;
        mort = false;
        pos = new Coordonnee(0, 0);
    }
    
}
