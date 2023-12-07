package jav.Personnages.Ennemis;

import jav.Game;
import jav.Personnages.Tours.Tours;

public class Boo extends Ennemis {
    public Boo(){
        super();
        lettre=". ";
        url="ennemis/booinvisible/boo";
        pv=60;
        valeur=50;
        timebetweenMov=1000;
        degat=10;
        range=1;
        timebetweendegat=2000;
        nbimageAnimation=1;
    }

    public boolean nextIsTour(Game g){
        if(g.getToursEnJeu().size()!=0){
            for(Tours t : g.getToursEnJeu()){
                if(t.getPos().getIntCoordonnee().getY()==pos.getY() && t.getPos().getIntCoordonnee().getX()==pos.getY()-1){
                    return true;
                }
            }
        }
        return false;
    }

    public void pouvoir(Game g){ // Boo est invisible les premières cases du jeu sauf s'il rencontre une tour
        if(pos.getIntCoordonnee().getX()<=(g.getMap().getLargeur()-g.getMap().getLargeur()/2) || nextIsTour(g)){
            url="ennemis/boo/boo";
            nbimageAnimation=3;
            lettre="B ";
        }
    }
}
