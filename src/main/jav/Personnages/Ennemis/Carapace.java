package jav.Personnages.Ennemis;


import jav.Game;
import jav.Personnages.Tours.Tours;
import jav.Personnages.Tours.Tuyau;

public class Carapace extends Ennemis {

    public Carapace(){
        super();
        lettre="C ";
        url="ennemis/carapace/carapace";
        pv=100;
        valeur=0;
        timebetweenMov=300;
        degat=50;
        range=1;
        timebetweendegat=100;
        nbimageAnimation=6;
        scale = 0.5;
        timeBetweenAnim = 100;
        setLevelDificulty();
    }

    @Override
    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY() && !(t instanceof Tuyau)){
            if(pos.getX()-t.getPos().getX() <= 3*Game.sizecase/4){
                t.enleverPv(this.degat);
                t.setAttacked(true);
                mort = true;
            }
        }
        return false;
    }



    @Override
    public void avancer(Game g){
        if(!(pos.getIntCoordonnee().getX()==1)){
            pos.setX(pos.getX()-(Game.sizecase/ frame));
        }
        else {
            mort = true;
        }
    }
    

    @Override
    public void update(Game g){
        super.update(g);
        if(pos.getIntCoordonnee().getX()==g.getMap().getLargeur()-1){
            meurt();
        }
    }



    public void pouvoir(Game g){
        // pas besoin
    }
}
