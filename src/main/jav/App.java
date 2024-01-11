package jav;

import jav.Personnages.Tours.Tours;
import jav.gui.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.InvocationTargetException;
import java.net.http.WebSocket.Listener;
import java.util.Scanner;

import javax.print.attribute.standard.RequestingUserName;



public class App {
    private GameView view;
    private JeuTexte jeuTexte;
    private int levelDificulty = 1; // 1 - facile 2 - moyen 3 - difficile
    private int mode = 1; // 1 - campagne 2 - marathon 3 - personnalisé
    private int lvl = 1;

    private int nbEnnemis;
    private int largeur;
    private int hauteur;

    private MenuDepartView viewMenuDepart;
    public static final String currentDirectory = System.getProperty("user.dir");

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setNbEnnemis(int i){
        nbEnnemis = i;
    }

    public void setHauteur(int i){
        hauteur = i;
    }

    public void setLargeur(int i){
        largeur = i;
    }

    public int getNbEnnemis(){
        return nbEnnemis;
    }

    public int getLvl(){
        return lvl;
    }

    public void setNextLvl(){
        lvl++;
    }

    public void setLevel(int lvl){
        this.lvl = lvl;
    }

    public int getMode(){
        return mode;
    }

    public void setDificulty(int i){
        levelDificulty = i;
    }

    public void setMode(int i){
        mode = i;
    }

    public int getLevelDificulty(){
        return levelDificulty;
    }
    public static void main(String[] args) {
        App app = new App();
        app.TypeGame();
    }

    public void TypeGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Jeu avec l'interface graphique 2: Jeu sur le terminal");
        try {
            int rep = Integer.valueOf(sc.nextLine()); 
            switch(rep){
                case 1: afficheMenu(); break;
                case 2: GameTerminal(sc); break;
                default: TypeGame(); break;
            }}
        catch(NumberFormatException e){
            System.out.println("Rééssaayer");
            TypeGame();return;
        }
    }

    public void GameInterface(int level){
            EventQueue.invokeLater( () -> {
                switch(level){
                    case 1 : view = new GameView(2,10,5, this, getLevelDificulty(), mode);  break;
                    case 2 : view = new GameView(3,10,10, this, getLevelDificulty(), mode);  break;
                    case 3 : view = new GameView(4,10,20, this, getLevelDificulty(), mode);  break;
                    case 4 : view = new GameView(5,10,50, this, getLevelDificulty(), mode); break;
                    case 5 : new GameOverView(this, "ecranWin"); break;
                    default : view = new GameView(5,10,0, this, getLevelDificulty(), mode);
                }
            view.setVisible(true);}
            );
    }

    public void GameTerminal(Scanner sc){
        System.out.println("choisissez votre niveau de difficulté : | 1 - facile |  | 2 - Moyen |  | 3 - Difficile |");
        int rep = Integer.valueOf(sc.nextLine()); 
        try{
            switch(rep){
                case 1: levelDificulty = 1; break;
                case 2: levelDificulty = 2; break;
                case 3: levelDificulty = 3; break;
                default: GameTerminal(sc); break;
            }
        } catch (NumberFormatException e){
        System.out.println("Réessayer");
        GameTerminal(sc);}
        jeuTexte = new JeuTexte(5, 20, 5, levelDificulty);
    }

    public void afficheMenu(){
        EventQueue.invokeLater( () -> {
        viewMenuDepart = new MenuDepartView(this);});
    }

    public void relance(){
        afficheMenu();
    }

    public void lanceJeuInterface(){
        viewMenuDepart.dispose();
        if(mode==1){
            new MenuCampagne(this);
        }
        else if(mode==3){
            new MenuPersonnalise(this);
        }
        else {
            GameInterface(0);
        }
    }

    public void startCampagneGame(int level){
        GameInterface(level);
    }

    public void lanceJeuPersonnalise(){
        EventQueue.invokeLater( () -> {
            view = new GameView(hauteur, largeur,nbEnnemis, this, getLevelDificulty(), mode); 
        view.setVisible(true);}
        );
    }

}
