package java.Personnages;

import java.Maps.Cordonnee;

public interface Perso {
    public void changepv(int degat);
    public int getPv();
    public int getDegat();
    public Cordonnee getPos();
    public void enleverPv(int degat);
    public void mort();
    public boolean attaque(Perso t);
}
