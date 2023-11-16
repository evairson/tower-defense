package java;

import java.Maps.Case;
import java.Maps.Plateau;
import java.Personnages.Ennemis;

public class Game {
    private Plateau map;
    private Joueur joueur;

    Game(){
        joueur = new Joueur();
        map = new Plateau(5,10);
    }

    public boolean gameOver(Case cas){
        if(cas.getContenu() instanceof Ennemis && cas.EstUneBase()){
            return true;
        }
        return false;
    }

    
}
