package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Ennemis;

public class Peach extends Tours implements TourAttaque{
    public Peach(RealCoordonnee pos){
        pv=80;
        degat = 0;
        prix = 20;
        range = 8;
        timebetweendegat = 4000;
        mort = false;
        this.pos = pos;
        lettre="PE";
        }
        public void toFlower(){
            this.url = "";
            switch(this.niveau){
                case 0 -> {this.pv = 130; this.degat+=15;this.niveau++;return;}
                case 2 -> {this.pv = 130;this.degat-=15;this.niveau--;return;}
                default -> {return;}
            }
        }
    
        public void toStar(){
            this.url = "";
            switch(this.niveau){
                case 0 -> {this.pv = 180;this.degat+=30;this.niveau+=2;return;}
                case 1 -> {this.pv = 180;this.degat+=15;this.niveau++;return;}
                default -> {return;}
            }
        }

        public void pouvoir(Game g){

        }
        @Override
        public boolean attaque(Ennemis e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'attaque'");
        }
    
    
}
