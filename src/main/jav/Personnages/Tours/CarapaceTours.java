package jav.Personnages.Tours;

import jav.Game;
import jav.Personnages.Ennemis.Ennemis;

public class CarapaceTours extends Tours implements TourAttaque {
    private int timebetweenMov;
    private long timeMov;
    private long timeAnimCara;
    
    public CarapaceTours(int pouvoir){     
        
        if (pouvoir ==1){
            lettre="F ";
            url="tours/bouleFeu/feu";
            timebetweenMov=500;
            nbimageAnimation=5;
        }
        else{
            lettre="C ";
            url="ennemis/carapace/carapace";
            timebetweenMov=1000;
            nbimageAnimation=6;
        }

        pv=100;
        degat=3;
        range=1;
        timebetweendegat=100;
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
            if(!depasserTour(g) && !(pos.getIntCoordonnee().getX()==g.getMap().getLargeur()-1)){
                pos.setX(pos.getX()+3*(Game.sizecase/Ennemis.frame));
            }
            else {
                if(depasserTour(g)){ 
                    pos.setX(pos.getX()+(2*Game.sizecase)+(Game.sizecase/Ennemis.frame));
                }
                else {
                    mort = true;
                }
            }

    }
    

    @Override
    public void update(Game g){

        super.update(g);
        if(!mort){
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

    }



    public void pouvoir(Game g){
        // pas besoin
    }

    public void toFlower(){

    }

    public void toStar(){

    }
}

