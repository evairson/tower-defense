package jav.Personnages.Ennemis;

import jav.Game;

public class Heriss extends Ennemis {

    public Heriss(){
        super();
        lettre="H ";
        url="ennemis/heriss/heriss";
        pv=20;
        valeur=5;
        timebetweenMov=3000;
        degat=10;
        range=1;
        timebetweendegat=1000;
        scale = 0.5;
        nbimageAnimation = 4;

    }

    public void pouvoir(Game g){
        //pas besoin
    }

}