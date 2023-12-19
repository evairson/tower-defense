package jav.Personnages.Tours;

import jav.Game;
import jav.Personnages.Ennemis.Ennemis;

public class CarapaceTours extends Tours implements TourAttaque {
    private int timebetweenMov;
    private long timeMov;
    private long timeAnimCara;
    
    public CarapaceTours(){
        lettre="C ";
        url="ennemis/carapace/carapace";
        pv=100;
        timebetweenMov=300;
        degat=3;
        range=1;
        timebetweendegat=100;
        nbimageAnimation=6;
        timeMov=System.currentTimeMillis();
        scale = 0.5;
        timeAnimCara = System.currentTimeMillis();
    }

    @Override
    public boolean attaque(Ennemis e){
        if(e.getPos().getY()==pos.getY()){
            if(e.getPos().getX()-pos.getX() <= 3*Game.sizecase/4){
                isAnimed = true;
                e.setAttacked(true);
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
        return true;
    }
    

    @Override
    public void update(Game g){
        super.update(g);

        if(image!=null){
            if(System.currentTimeMillis() - timeAnimCara > 100){
                nextImage();
                timeAnimCara =System.currentTimeMillis();
            }
        }

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

