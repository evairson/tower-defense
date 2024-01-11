package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Pouvoirs.Etoile;
import jav.Personnages.Pouvoirs.Fleur;

public class Peach extends Tours implements TourAttaque, Fleur, Etoile{

    public Peach(RealCoordonnee pos){
        super();
        pv=150;
        degat = 15;
        range = 4;
        timebetweendegat = 3000;
        this.pos = pos;
        lettre="PE";

        url = "tours/peach/peach";
        scale = 1;
        nbimageAnimation = 3;
        }

        public void toFlower(){
            Fleur.super.toFlower(this);
            range += 1;
            this.url = "tours/peach/peachFleur/peach";
            nextImage();
        }
    
        public void toStar(){
            Etoile.super.toStar(this);
            range +=2;
            url="tours/peach/peachEtoile/peach";
            nextImage();
        }

        public void pouvoir(Game g){

        }
    
    
}
