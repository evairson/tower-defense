package jav.Personnages.Ennemis;

import jav.Game;
import jav.Personnages.Tours.Tours;

public class Goomba extends Ennemis {
    private int sprintRange;
    private int sprint;
    private int vitesseBasique;

    public Goomba(){
        super();
        lettre='G';
        pv=20;
        valeur=2;
        vitesseBasique=2000;
        vitesse = vitesseBasique;
        degat=2;
        range=1;
        vitessedegat=2000;
        sprintRange = 4;
        sprint=500;
        url = "goomba.png";
    }

    public boolean RangeIsTour(Game g){
        if(g.getToursEnJeu().size()!=0){
            for(Tours t : g.getToursEnJeu()){
                if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= sprintRange){
                    return true;
                }
            }
        }
        return false;
    }



    public void pouvoir(Game g){
        if(RangeIsTour(g)){
            vitesse = sprint;
        }
        else{
            vitesse = vitesseBasique;
        }
    }
}
