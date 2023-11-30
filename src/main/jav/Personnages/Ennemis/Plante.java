package jav.Personnages.Ennemis;

import jav.Game;

public class Plante extends Ennemis {
    public Plante(){
        super();
        lettre='P';
        pv=50;
        valeur=2;
        timebetweenMov=5000;
        degat=10;
        range=2;
        timebetweendegat=4000;
    }

    public void pouvoir(Game g){
        //Pas besoin
    }
}
