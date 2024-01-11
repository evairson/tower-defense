package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Pouvoirs.Etoile;
import jav.Personnages.Pouvoirs.Fleur;
import jav.Personnages.Pouvoirs.Pouvoirs;

public class Luigi extends Tours implements TourAttaque, Fleur, Etoile{

    public Luigi(RealCoordonnee pos){
        super();
        pv=200;
        degat = 20;
        range = 1;
        timebetweendegat = 3000;
        this.pos = pos;
        lettre = "LU";
        url = "tours/luigi/luigi";
        scale = 1;
        nbimageAnimation = 3;
        }

        public void toFlower(){
            Fleur.super.toFlower(this);
            timebetweendegat -= 1000;
            this.url = "tours/luigi/luigiFleur/luigi";
            nextImage();
        }
    
        public void toStar(){
            Etoile.super.toStar(this);
            timebetweendegat -= 1000;
            url="tours/luigi/luigiEtoile/luigi";
            nextImage();
        }

        public void pouvoir(Game g){

        }
    
    
}
