package jav.Personnages.Ennemis;


import jav.Game;
import jav.Personnages.Tours.TourAttaque;
import jav.Personnages.Tours.Tours;

public class Carapace extends Ennemis implements TourAttaque {

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
        scale = 0.7;
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

    public boolean attaque(Ennemis e){
        if(e.getPos().getY()==pos.getY()){
            if(e.getPos().getIntCoordonnee().getX() - this.pos.getIntCoordonnee().getX() <= range){
                e.enleverPv(this.degat);
                mort = true;
            }
        }
        return false;
    }

    public boolean depasserTour(Game g){
        for(Tours t : g.getToursEnJeu()){
            if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && t.getPos().getIntCoordonnee().getX()==pos.getIntCoordonnee().getX()+1){
                return true;
            }
        }
        return false;
    }


    @Override
    public void avancer(Game g){
        if(canMove(g)){
            pos.setX(pos.getX()-(Game.sizecase/ frame));
        }
        else {
            if(depasser(g)){ // a regler
                pos.setX(pos.getX()-(Game.sizecase)-(Game.sizecase/8));
            }
        }

    }

    @Override
    public boolean canMove(Game g){
        for(Tours t : g.getToursEnJeu()){
            if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && t.getPos().getIntCoordonnee().getX()==pos.getIntCoordonnee().getX()-1){
                return false;
            }
        }
        for(Ennemis e : g.getEnnemis()){
            if(e.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && e.getPos().getIntCoordonnee().getX()==pos.getIntCoordonnee().getX()-1){
                 return false;
            }
        }
        return true;
    }
    

    @Override
    public void update(Game g){
        super.update(g);
        if(pos.getIntCoordonnee().getX()==g.getMap().getLargeur()-1){
            meurt();
        }
    }



    public void pouvoir(Game g){
        // pas besoin
    }
}
