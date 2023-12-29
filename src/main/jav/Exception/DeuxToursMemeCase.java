package jav.Exception;

import jav.Game;
import jav.Personnages.Ennemis.Carapace;
import jav.Personnages.Tours.CarapaceTours;
import jav.Personnages.Tours.Tours;

public class DeuxToursMemeCase extends Exception {
    public void changeTour(Game g){
        for(Tours t : g.getToursEnJeu()){
            for(Tours t2 : g.getToursEnJeu()){
                if(t != t2 && t.getPos().getIntCoordonnee().getX() == t2.getPos().getIntCoordonnee().getX()
                && t.getPos().getIntCoordonnee().getY() == t2.getPos().getIntCoordonnee().getY()){
                    if(t instanceof CarapaceTours){
                        try {
                            if(t.getPos().getIntCoordonnee().getX() < g.getMap().getLargeur()){

                                t.getPos().setX(t.getPos().getX()+Game.sizecase);
                                conditionDeuxTours(g);
                            }
                            else {
                                t.meurt();
                            }
                        }
                        catch (DeuxToursMemeCase e) {
                            changeTour(g);
                        }
                    }
                    else {
                        try {
                            if(t2.getPos().getIntCoordonnee().getX() < g.getMap().getLargeur()){

                                t2.getPos().setX(t.getPos().getX()+Game.sizecase);
                                conditionDeuxTours(g);
                            }
                            else {
                                t2.meurt();
                            }
                        }
                        catch (DeuxToursMemeCase e) {
                            changeTour(g);
                        }
                    }
                }
            }
        }
    }

    public void conditionDeuxTours(Game g) throws DeuxToursMemeCase{
        for(Tours t : g.getToursEnJeu()){
            for(Tours t2 : g.getToursEnJeu()){
                if(t != t2 && t.getPos().getIntCoordonnee().getX() == t2.getPos().getIntCoordonnee().getX()
                && t.getPos().getIntCoordonnee().getY() == t2.getPos().getIntCoordonnee().getY()){
                    throw new DeuxToursMemeCase();
                }
            }
        }
    }
}
