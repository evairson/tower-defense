package jav.Maps;

import jav.Game;
import jav.Personnages.Ennemis.Ennemis;

import jav.Personnages.Tours.Tours;

public class Plateau {
    private Case[][] grid;
    private int largeur;
    private int longueur;
    public final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Case[][] getGrid(){
        return grid;
    }
    
    public int getLargeur(){
        return largeur;
    }

    public int getLongeur(){
        return longueur;
    }
    public Case getCase(int x,int y){
        return grid[y][x];

    }

    public Plateau(int longeur, int largeur){
        this.largeur=largeur;
        this.longueur=longeur;
        grid = new Case[longeur][largeur];
        for(int i =0; i < longeur; i ++){
            for(int j = 0; j <largeur; j++){
                grid[i][j] = new Case();
            }
        }
    }

    public void updateContenu(Game g){
        for(int i =0; i < longueur; i ++){
            for(int j = 0; j <largeur; j++){
                grid[i][j].resetContenu();
                for(int k =0; k<g.getEnnemis().size(); k++){
                    Ennemis e = g.getEnnemis().get(k);
                    if(e.getPos().getIntCoordonnee().getX()==j && e.getPos().getIntCoordonnee().getY()==i){
                        grid[i][j].addContenuEnnemis(e);
                    }
                }
                for(int k =0; k<g.getToursEnJeu().size(); k++){
                    Tours t = g.getToursEnJeu().get(k);
                    if(t.getPos().getIntCoordonnee().getX()==j && t.getPos().getIntCoordonnee().getY()==i){
                        grid[i][j].setContenuTours(t);
                    }
                }
            }
        }

    }

    public void afficher(){
        System.out.println();
        for(int i =0; i<Math.min(largeur, 10); i++){
            System.out.print(i + "  ");
        }
        if(largeur>=10){
            for(int i=10; i<=largeur; i++){
            System.out.print(i + " ");
            }
        }
        System.out.println();
        for(int j =0; j< longueur; j++){
            System.out.print(alphabet.charAt(j) + "  ");
            for(Case cas : grid[j]){
                cas.afficher();
            }
            System.out.println();
        }
    }
}
