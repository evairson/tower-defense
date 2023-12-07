package jav.Personnages.Tours;

import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public class Peach extends Tours{
    public Peach(RealCoordonnee pos){
        pv=80;
        degat = 0;
        prix = 20;
        niveau= 0;
        range = 8;
        vitessedegat = 4000;
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
    
    
}
