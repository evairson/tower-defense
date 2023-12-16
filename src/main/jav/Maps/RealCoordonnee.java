package jav.Maps;

import jav.Game;

public class RealCoordonnee {
    private double x;
    private double y;
    private static int sizeCase;

    public RealCoordonnee(int intx, int inty){
        sizeCase = Game.sizecase;
        x = intx*sizeCase;
        y = inty*sizeCase;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public static int getIntCoordonneeXY(int x){
        return (int)(x/sizeCase);
    }

    public Coordonnee getIntCoordonnee(){
        return new Coordonnee((int)(x/sizeCase), (int)(y/sizeCase));
    }

}
