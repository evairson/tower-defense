package jav.Personnages.Tours;

import java.util.ArrayList;

import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Boo;
import jav.Personnages.Ennemis.Ennemis;


public interface TourAttaque {

    public RealCoordonnee getPos();
    public void setAnimed(boolean b);
    public int getRange();
    public int getDegat();

    public default boolean attaque(Ennemis e){
            if(!(e instanceof Boo) || !((Boo)e).isGhost()){
                if(e.getPos().getY()==this.getPos().getY()){
                    if(e.getPos().getIntCoordonnee().getX() - this.getPos().getIntCoordonnee().getX() <= getRange()){
                        setAnimed(true);
                        e.setAttacked(true);
                        e.enleverPv(this.getDegat());
                    }
                }
            }
            return false;
        
    }
    public default void attaquer(ArrayList<Ennemis> e){
            int i=0;
            if(e.size()!=0){
                while(i<e.size() && !attaque(e.get(i))){
                    i++;
                }
            }
    }

}
