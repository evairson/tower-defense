package main.java;

import main.java.Maps.Plateau;

public class Game {
    private Plateau map;
    private Joueur joueur;

    Game(){
        joueur = new Joueur();
        map = new Plateau(5,10);
    }
}
