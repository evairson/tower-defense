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

public class MenuDepartView extends MenuView {
    private JButton start;
    private JPanel difficultyPanel;

    private int longueur;
    private int largeur;

    public MenuDepartView(App app){
        
        super(app, "Menu de Départ");
        
        start = createButton("Play.png", 0.3);
        start.addActionListener(ActionListener -> app.lanceJeuInterface());

        fondPanel.setLayout(new BoxLayout(fondPanel, BoxLayout.Y_AXIS));

        JPanel startGame = new JPanel();
        startGame.add(start);
        startGame.setOpaque(false);

        difficultyPanel = createButtonPanel("Difficulté", buttonSize, "facile", "moyen", "difficile");
        addActionButtonDificulty();
        buttonSelect(convertDifficulty(), "facile", "moyen", "difficile");

        JPanel modePanel = createButtonPanel("Mode de Jeu", buttonSize, "campagne", "marathon", "personnalise");
        addActionButtonMode();
        buttonSelect(convertMode(), "campagne", "marathon", "personnalise");

        fondPanel.add(startGame);
        fondPanel.add(difficultyPanel);
        fondPanel.add(modePanel);

        getContentPane().add(fondPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addActionButtonDificulty(){
        String[] str = {"facile", "moyen","difficile"};
        for(String s : str){
            final int dificulty;
            switch(s){
                case "moyen" : dificulty = 2; break;
                case "difficile" : dificulty = 3; break;
                default : dificulty = 1;
            }
            
            buttons.get(s).addActionListener(action -> {app.setDificulty(dificulty); buttonSelect(convertDifficulty(), str);});
        }
    }



    private String convertDifficulty(){
        switch(app.getLevelDificulty()){
            case 1 : return "facile";
            case 2 : return "moyen";
            case 3 : return "difficile";
            default : return "facile";
        }
    }

}
