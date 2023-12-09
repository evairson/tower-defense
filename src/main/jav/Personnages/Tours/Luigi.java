package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Ennemis;

public class Luigi extends Tours implements TourAttaque {

    public Luigi(RealCoordonnee pos){
        super();
        pv=100;
        degat = 40;
        prix = 20;
        range = 1;
        timebetweendegat = 2000;
        mort = false;
        this.pos = pos;
        lettre = "LU";
        }

        public boolean attaque(Ennemis e){
            if(e.getPos().getY()==pos.getY()){
                if(e.getPos().getIntCoordonnee().getX() - this.pos.getIntCoordonnee().getX() <= range){
                    e.enleverPv(this.degat);
                }
            }
            return false;
        }

        public void toFlower(){
            this.url = "";
            switch(this.niveau){
                case 0 -> {this.pv = 150; this.degat+=30;this.niveau++;return;}
                case 2 -> {this.pv = 150;this.degat-=30;this.niveau--;return;}
                default -> {return;}
            }
        }
    
        public void toStar(){
            this.url = "";
            switch(this.niveau){
                case 0 -> {this.pv = 200;this.degat+=60;this.niveau+=2;return;}
                case 1 -> {this.pv = 200;this.degat+=30;this.niveau++;return;}
                default -> {return;}
            }
        }

        public void pouvoir(Game g){

        }
    
    
}
