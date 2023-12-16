package jav.Personnages.Tours;

import jav.Game;
import jav.Personnages.Ennemis.Ennemis;

public class CarapaceTours extends Tours {
    private int timebetweenMov;
    private long timeMov;
    
    public CarapaceTours(){
        lettre="C ";
        url="ennemis/carapace/carapace";
        pv=100;
        timebetweenMov=500;
        degat=3;
        range=1;
        timebetweendegat=100;
        nbimageAnimation=6;
        timeMov=System.currentTimeMillis();
        scale = 0.5;
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


    public void avancer(Game g){
            if(canMove(g) && !(pos.getIntCoordonnee().getX()==g.getMap().getLargeur()-1)){
                pos.setX(pos.getX()+(Game.sizecase/Ennemis.frame));
            }
            else {
                if(depasserTour(g)){ // a regler
                    pos.setX(pos.getX()+(Game.sizecase)+(Game.sizecase/Ennemis.frame));
                }
            }

    }

    public boolean canMove(Game g){
        for(Tours t : g.getToursEnJeu()){
            if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && t.getPos().getIntCoordonnee().getX()==pos.getIntCoordonnee().getX()+1){
                return false;
            }
        }
        for(Ennemis e : g.getEnnemis()){
            if(e.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && e.getPos().getX()-pos.getX()<=3*Game.sizecase/4){
                 return false;
            }
        }
        return true;
    }
    

    @Override
    public void update(Game g){
        super.update(g);

        if(System.currentTimeMillis() - timeMov > (timebetweenMov / Ennemis.frame)){
            avancer(g);
            timeMov =System.currentTimeMillis();
        }
    }



    public void pouvoir(Game g){
        // pas besoin
    }

    public void toFlower(){

    }

    public void toStar(){

    }
}

