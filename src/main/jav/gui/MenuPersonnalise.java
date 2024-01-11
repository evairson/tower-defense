package jav.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jav.*;

public class MenuPersonnalise extends MenuView {
    private JButton start;

    private int longueur;
    private int largeur;

    public MenuPersonnalise(App app){
        
        super(app, "Menu de Départ");
        
        start = createButton("Play.png", 0.3);
        start.setEnabled(false);
        start.addActionListener(ActionListener -> {this.dispose(); app.lanceJeuPersonnalise();});

        fondPanel.setLayout(new BoxLayout(fondPanel, BoxLayout.Y_AXIS));

        JPanel startGame = new JPanel();
        startGame.add(start);
        startGame.setOpaque(false);

        JPanel nbLigne = createButtonPanel("Hauteur de la grille", buttonSize, "nb/2ligne", "nb/3ligne", "nb/4ligne", "nb/5ligne");
        addActionButtonHauteur();

        JPanel nbColonne = createButtonPanel("Largeur de la grille", buttonSize, "nb/10colonne", "nb/15colonne", "nb/20colonne");
        addActionButtonLargeur();

        JPanel nbEnnemis = createButtonPanel("Nombre d'ennemis", buttonSize, "nb/5ennemis", "nb/10ennemis", "nb/20ennemis", "nb/50ennemis", "nb/100ennemis");
        addActionButtonNbEnnemis();

        JPanel modeJeu = createButtonPanel("Mode de jeu", buttonSize, "campagne", "marathon");
        addActionButtonMode();

        fondPanel.add(startGame);
        fondPanel.add(nbLigne);
        fondPanel.add(nbColonne);
        fondPanel.add(nbEnnemis);
        fondPanel.add(modeJeu);

        getContentPane().add(fondPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addActionButtonHauteur(){
        String[] str = {"nb/2ligne", "nb/3ligne", "nb/4ligne", "nb/5ligne"};
        for(String s : str){
            final String hauteur = s;
            
            buttons.get(s).addActionListener(action -> {app.setHauteur(recupererNombre(hauteur)); updateStart(); buttonSelect(s, str);});
        }
    }

    public void addActionButtonLargeur(){
        String[] str = {"nb/10colonne", "nb/15colonne", "nb/20colonne"};
        for(String s : str){
            final String largeur = s;
            
            buttons.get(s).addActionListener(action -> {app.setLargeur(recupererNombre(largeur)); updateStart(); buttonSelect(s, str);});
        }
    }

    public void addActionButtonNbEnnemis(){
        String[] str = {"nb/5ennemis", "nb/10ennemis", "nb/20ennemis", "nb/50ennemis", "nb/100ennemis"};
        for(String s : str){
            final String nbEnnemis = s;
            buttons.get(s).addActionListener(action -> {app.setNbEnnemis(recupererNombre(nbEnnemis)); updateStart(); buttonSelect(s, str);});
        }
    }


    public void addActionButtonMode(){
        String[] str = {"campagne", "marathon"};
        for(String s : str){
            final int mode;
            switch(s){
                case "marathon" : mode = 2; break;
                default : mode = 1;
            }
            buttons.get(s).addActionListener(action -> {app.setMode(mode); updateStart(); buttonSelect(convertMode(), str);});
        }
    }

    public void updateStart(){
        if(app.getHauteur() != 0 && app.getLargeur() != 0 && app.getNbEnnemis() != 0 && app.getMode() != 3){
            start.setEnabled(true);
        }  
    }

    private int recupererNombre(String s){
        if(s.substring(4, 5).equals("0") || s.substring(4, 5).equals("5")){
            return Integer.parseInt(s.substring(3, 5));
        }
        else {
            return Integer.parseInt(s.substring(3, 4));
        }
    }

}
