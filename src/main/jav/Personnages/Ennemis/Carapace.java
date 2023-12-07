package jav.Personnages.Ennemis;

import java.util.ArrayList;

import jav.Game;
import jav.Personnages.Tours.Tours;

public class Carapace extends Ennemis {
    public Carapace(){
        super();
        lettre="C ";
        url="ennemis/carapace/carapace";
        pv=100;
        valeur=50;
        timebetweenMov=500;
        degat=3;
        range=1;
        timebetweendegat=100;
        nbimageAnimation=6;

    }

    @Override
    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= range){
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
