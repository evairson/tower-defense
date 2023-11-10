package main.java.Maps;

public class Plateau {
    private Case[][] grid;

    public Plateau(int largeur, int longeur){
        grid = new Case[longeur][largeur];
    }
}
