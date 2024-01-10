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

public class MenuDepartView extends JFrame {
    private JButton start;
    private App app;
    private JPanel difficultyPanel;

    private int longueur;
    private int largeur;
    private Dimension buttonSize = new Dimension(300, 100);
    private HashMap<String, JButton> buttons;

    public MenuDepartView(App app){
        
        super("Menu de Départ");
        buttons = new HashMap<>();
        this.app = app;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel fondPanel = new JPanel(){
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/src/main/resources/FondMenuDepart.png");
              Image fond = imageIcon.getImage();
              g.drawImage(fond,0,0,getWidth(),getHeight(),null);
          }
        };
        
        start = createButton("Play.png", 0.3);
        start.addActionListener(ActionListener -> app.lanceJeuInterface());

        fondPanel.setLayout(new BoxLayout(fondPanel, BoxLayout.Y_AXIS));

        JPanel startGame = new JPanel();
        startGame.add(start);
        startGame.setOpaque(false);

        difficultyPanel = createButtonPanel("Difficulté", buttonSize, "facile", "moyen", "difficile");
        addActionButtonDificulty();
        buttonSelectDificulty();

        JPanel modePanel = createButtonPanel("Mode de Jeu", buttonSize, "campagne", "marathon", "personnalise");
        addActionButtonMode();
        buttonSelectMode();

        fondPanel.add(startGame);
        fondPanel.add(difficultyPanel);
        fondPanel.add(modePanel);

        getContentPane().add(fondPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static JButton createButton(String s, double size){
        JButton b = new JButton();
        try {
            ImageIcon image = createIcon(s, size);
            b = new JButton(image);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
        } 

        catch (IOException exception) {
            exception.printStackTrace();
        }
        return b;
    }

    private static ImageIcon createIcon(String s, double size) throws IOException{
        File file = new File(App.currentDirectory+"/src/main/resources/button/" + s);
        Image bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(width*size), (int)(height*size), Image.SCALE_DEFAULT));
        return image;
    }

    private JPanel createButtonPanel(String title, Dimension size, String... buttonTexts) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));

        for (String text : buttonTexts) {
            JButton button = createButton(text + ".png", 0.3);
            buttons.put(text, button);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(button);
        }

        panel.setOpaque(false);

        return panel;
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
            
            buttons.get(s).addActionListener(action -> {app.setDificulty(dificulty); buttonSelectDificulty();});
        }
    }

    public void addActionButtonMode(){
        String[] str = {"campagne", "marathon", "personnalise"};
        for(String s : str){
            final int mode;
            switch(s){
                case "marathon" : mode = 2; break;
                case "personnalisé" : mode = 3; break;
                default : mode = 1;
            }
            
            buttons.get(s).addActionListener(action -> {app.setMode(mode); buttonSelectMode();});
        }
    }

    private void buttonSelectDificulty(){
        String[] str = {"facile", "moyen", "difficile"};
        for(String s : str){
            if(s == convertDifficulty()){
                try{
                    buttons.get(s).setIcon(createIcon(s + "S.png", 0.3));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            else {
                try{
                    buttons.get(s).setIcon(createIcon(s + ".png", 0.3));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            this.difficultyPanel.repaint();
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

    private void buttonSelectMode(){
        String[] str = {"campagne", "marathon", "personnalise"};
        for(String s : str){
            if(s == convertMode()){
                try{
                    buttons.get(s).setIcon(createIcon(s + "S.png", 0.3));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            else {
                try{
                    buttons.get(s).setIcon(createIcon(s + ".png", 0.3));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            this.difficultyPanel.repaint();
        }
    }

    private String convertMode(){
        switch(app.getMode()){
            case 1 : return "campagne";
            case 2 : return "marathon";
            case 3 : return "personnalise";
            default : return "campagne";
        }
    }
    

}
