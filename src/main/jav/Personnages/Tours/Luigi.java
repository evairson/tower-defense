package jav.Personnages.Tours;

import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public class Luigi extends Tours {

    public Luigi(RealCoordonnee pos){
        pv=100;
        degat = 40;
        prix = 20;
        niveau= 0;
        range = 1;
        vitessedegat = 2000;
        mort = false;
        this.pos = pos;
        lettre = "LU";
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
    
    
}
