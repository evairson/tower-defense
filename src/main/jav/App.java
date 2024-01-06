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
import java.util.Scanner;

import javax.print.attribute.standard.RequestingUserName;



public class App {
    private GameView view;
    private JeuTexte jeuTexte;

    private MenuDepartView viewMenuDepart;
    public static final String currentDirectory = System.getProperty("user.dir");

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
                case 2: GameTerminal(); break;
                default: TypeGame(); break;
            }}
        catch(NumberFormatException e){
            System.out.println("Rééssaayer");
            TypeGame();return;
        }

    }

    public void GameInterface(){
            EventQueue.invokeLater( () -> {
           
            view = new GameView(3,10,5, this);
            view.setVisible(true);}
            
            );

    }



    public void GameTerminal(){
        jeuTexte = new JeuTexte(5, 20, 5);
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
        GameInterface();
    }

    

}
