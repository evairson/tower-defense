package jav.Personnages.Ennemis;

import jav.Game;

public class Heriss extends Ennemis {

    public Heriss(){
        super();
        lettre='H';
        image="";
        pv=20;
        valeur=10;
        vitesse=3000;
        degat=4;
        range=1;
        vitessedegat=1000;
    }

    public void pouvoir(Game g){
        //pas besoin
    }

}