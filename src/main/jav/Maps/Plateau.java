package jav.Maps;

import jav.Game;
import jav.Personnages.Ennemis.Ennemis;

public class Plateau {
    private Case[][] grid;
    private int largeur;
    private int longeur;

    public Case[][] getGrid(){
        return grid;
    }
    
    public int getLargeur(){
        return largeur;
    }

    public int getLongeur(){
        return longeur;
    }

    public Plateau(int longeur, int largeur){
        this.largeur=largeur;
        this.longeur=longeur;
        grid = new Case[longeur][largeur];
        for(int i =0; i < longeur; i ++){
            for(int j = 0; j <largeur; j++){
                grid[i][j] = new Case();
            }
        }
    }

    public void updateContenu(Game g){
        for(int i =0; i < longeur; i ++){
            for(int j = 0; j <largeur; j++){
                grid[i][j].setContenu(null);
                for(int k =0; k<g.getEnnemis().size(); k++){
                    Ennemis e = g.getEnnemis().get(k);
                    if(e.getPos().getX()==j && e.getPos().getY()==i){
                        grid[i][j].setContenu(e);
                    }
                }
            }
        }

    }

    public void afficher(){
        for(Case[] line : grid){
            for(Case cas : line){
                cas.afficher();
            }
            System.out.println();
        }
    }
}
