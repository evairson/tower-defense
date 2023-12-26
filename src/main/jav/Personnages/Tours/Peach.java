package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.RealCoordonnee;

public class Peach extends Tours implements TourAttaque{

    public Peach(RealCoordonnee pos){
        super();
        pv=80;
        degat = 10;
        prix = 20;
        range = 6;
        timebetweendegat = 3000;
        this.pos = pos;
        lettre="PE";

        url = "tours/peach/peach";
        scale = 1;
        nbimageAnimation = 3;
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
    
    
}
