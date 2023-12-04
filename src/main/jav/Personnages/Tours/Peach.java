package jav.Personnages.Tours;

import jav.Maps.Coordonnee;

public class Peach extends Tours{
    public Peach(){
        pv=80;
        degat = 0;
        prix = 20;
        niveau= 0;
        range = 8;
        vitessedegat = 4000;
        mort = false;
        pos = new Coordonnee(0, 0);
        }
        public void toFlower(String image){
            this.image = image;
            switch(this.niveau){
                case 0 -> {this.pv = 130; this.degat+=15;this.niveau++;return;}
                case 2 -> {this.pv = 130;this.degat-=15;this.niveau--;return;}
                default -> {return;}
            }
        }
    
        public void toStar(String image){
            this.image = image;
            switch(this.niveau){
                case 0 -> {this.pv = 180;this.degat+=30;this.niveau+=2;return;}
                case 1 -> {this.pv = 180;this.degat+=15;this.niveau++;return;}
                default -> {return;}
            }
        }
    
    
}
