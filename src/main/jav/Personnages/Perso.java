package jav.Personnages;

import jav.Game;
import jav.Maps.Coordonnee;

public interface Perso {
    public int getPv();
    public int getDegat();
    public Coordonnee getPos();
    public void enleverPv(int degat);
    public void meurt();

    public void update(Game game);
    public void toFlower(String image);
    public void toStar(String image);

}
