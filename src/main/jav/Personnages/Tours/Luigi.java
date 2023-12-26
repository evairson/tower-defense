package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Pouvoirs.Fleur;

public class Luigi extends Tours implements TourAttaque, Fleur {

    public Luigi(RealCoordonnee pos){
        super();
        pv=100;
        degat = 10;
        prix = 20;
        range = 1;
        timebetweendegat = 2000;
        this.pos = pos;
        lettre = "LU";
        url = "tours/luigi/luigi";
        scale = 1;
        nbimageAnimation = 3;
        }

        public void toFlower(){
            Fleur.super.toFlower(this);
            this.url = "tours/luigi/luigiFleur/luigi";
            nextImage();
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
