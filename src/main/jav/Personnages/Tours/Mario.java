package jav.Personnages;

import jav.Maps.Coordonnee;

public class Mario extends Tours {
    public Mario(){
    pv=60;
    degat = 20;
    prix = 20;
    niveau= 0;
    range = 6;
    vitessedegat = 5000;
    mort = false;
    pos = new Coordonnee(0, 0);
    }
    public void toFlower(String image){
        this.image = image;
        switch(this.niveau){
            case 0 -> {this.pv = 100; this.degat+=15;this.niveau++;return;}
            case 2 -> {this.pv = 100;this.degat-=15;this.niveau--;return;}
            default -> {return;}
        }
    }

    public void toStar(String image){
        this.image = image;
        switch(this.niveau){
            case 0 -> {this.pv = 150;this.degat+=35;this.niveau+=2;return;}
            case 1 -> {this.pv = 150;this.degat+=15;this.niveau++;return;}
            default -> {return;}
        }
    }

    
}
