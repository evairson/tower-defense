package jav.Personnages.Ennemis;

import jav.Game;
import jav.Personnages.Tours.Tours;

public class Boo extends Ennemis {
    public Boo(){
        super();
        lettre='.';
        url="";
        pv=60;
        valeur=50;
        timebetweenMov=5000;
        degat=10;
        range=1;
        timebetweendegat=2000;
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
        if(pos.getX()<=(g.getMap().getLargeur()-g.getMap().getLargeur()/3) || nextIsTour(g)){
            url="Boo.png";
            lettre='B';
        }
    }
}
