package jav.Personnages.Tours;

import java.util.ArrayList;

import jav.Personnages.Ennemis.Ennemis;


public interface TourAttaque {

    public boolean attaque(Ennemis e);
    public void attaquer(ArrayList<Ennemis> e);

}
