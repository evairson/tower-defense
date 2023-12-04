package jav.Personnages.Ennemis;

import java.util.ArrayList;

import jav.Game;
import jav.Personnages.Tours.Tours;

public class Carapace extends Ennemis {
    public Carapace(){
        super();
        lettre='C';
        image="";
        pv=100;
        valeur=50;
        vitesse=500;
        degat=3;
        range=1;
        vitessedegat=100;

    }

    @Override
    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getX() - t.getPos().getX()<= range){
                t.enleverPv(this.degat);
                mort = true;
            }
        }
        return false;
    }

    public void pouvoir(Game g){
        // pas besoin
    }
}
