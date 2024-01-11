package jav.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jav.*;

public class MenuView extends JFrame {
    protected App app;

    protected Dimension buttonSize = new Dimension(300, 100);
    protected HashMap<String, JButton> buttons;
    protected JPanel fondPanel;

    public MenuView(App app, String title){
        
        super(title);
        buttons = new HashMap<>();
        this.app = app;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        fondPanel = new JPanel(){
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/src/main/resources/FondMenuDepart.png");
              Image fond = imageIcon.getImage();
              g.drawImage(fond,0,0,getWidth(),getHeight(),null);
          }
        };
        fondPanel.setLayout(new BoxLayout(fondPanel, BoxLayout.Y_AXIS));
    }

    protected static JButton createButton(String s, double size){
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

    protected static ImageIcon createIcon(String s, double size) throws IOException{
        File file = new File(App.currentDirectory+"/src/main/resources/button/" + s);
        Image bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(width*size), (int)(height*size), Image.SCALE_DEFAULT));
        return image;
    }

    protected JPanel createButtonPanel(String title, Dimension size, String... buttonTexts) {
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


    protected void buttonSelect(String focus, String... buttonTexts){
        for(String s : buttonTexts){
            if(s == focus){
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
        }
    }

    protected void addActionButtonMode(){
        String[] str = {"campagne", "marathon", "personnalise"};
        for(String s : str){
            final int mode;
            switch(s){
                case "marathon" : mode = 2; break;
                case "personnalise" : mode = 3; break;
                default : mode = 1;
            }
            
            if(s!="personnalise"){
                buttons.get(s).addActionListener(action -> {app.setMode(mode); buttonSelect(convertMode(), str);});
            }
            else {
                buttons.get(s).addActionListener(action -> {app.setMode(mode); app.lanceJeuInterface();});
            }
         }
    }

    protected String convertMode(){
        switch(app.getMode()){
            case 1 : return "campagne";
            case 2 : return "marathon";
            case 3 : return "personnalise";
            default : return "campagne";
        }
    }

}
