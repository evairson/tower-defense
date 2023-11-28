package jav.Personnages;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public interface Perso {
    public int getPv();
    public int getDegat();
    public RealCoordonnee getPos();
    public void enleverPv(int degat);
    public void meurt();
    public String getUrl();

    public void update(Game game);
}
