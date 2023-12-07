package jav.Personnages.Tours;

import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public class Mario extends Tours {
    public Mario(RealCoordonnee pos){
    pv=60;
    degat = 20;
    prix = 20;
    niveau= 0;
    range = 6;
    vitessedegat = 5000;
    mort = false;
    this.pos = pos;
    lettre = "MA";
    }
    public void toFlower(){
        this.url = "";
        switch(this.niveau){
            case 0 -> {this.pv = 100; this.degat+=15;this.niveau++;return;}
            case 2 -> {this.pv = 100;this.degat-=15;this.niveau--;return;}
            default -> {return;}
        }
    }

    public void toStar(){
        this.url = "";
        switch(this.niveau){
            case 0 -> {this.pv = 150;this.degat+=35;this.niveau+=2;return;}
            case 1 -> {this.pv = 150;this.degat+=15;this.niveau++;return;}
            default -> {return;}
        }
    }

    
}
