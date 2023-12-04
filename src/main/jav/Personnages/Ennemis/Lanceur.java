package jav.Personnages.Ennemis;

import jav.Game;
import jav.Personnages.Tours.Tours;

public interface Lanceur {
    public boolean lancer(Tours t, Game g);
    public int GetrangeProj();
}
