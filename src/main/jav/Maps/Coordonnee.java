package jav.Maps;

public class Coordonnee {
    private int x;
    private int y;

    public Coordonnee(int x, int y){
        this.x = x; this.y=y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}