package jav.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

import jav.*;

public class MenuCampagne extends JFrame {
    private JButton start;
    private App app;
    private JPanel difficultyPanel;
    private GameController control;

    private Dimension buttonSize = new Dimension(300, 100);
    private HashMap<Integer, JButton> buttons;

    public MenuCampagne(App app){
        
        super("Menu de la Campagne");
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
        

        fondPanel.setLayout(new BoxLayout(fondPanel, BoxLayout.Y_AXIS));
        
        for(int i = 1; i<5; i ++){
            JPanel panel = new JPanel();
            JButton b = createButton(i, 0.3);
            MenuCampagne menu = this;
            final int lvl = i;
            b.addActionListener(action -> {menu.dispose(); app.setLevel(lvl); app.startCampagneGame(lvl);});
            buttons.put(i, b);
            panel.add(b);
            panel.setOpaque(false);
            fondPanel.add(panel);
        }
        getContentPane().add(fondPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(int i, double size){
        JButton b = new JButton();
        boolean enable = true;
        try {
            String content = new String(Files.readAllBytes(Paths.get(App.currentDirectory+"/src/main/resources/campagne.json")));
            JSONObject jsonObject = new JSONObject(content);

            JSONArray levels = jsonObject.getJSONArray("levels");
            JSONObject level = levels.getJSONObject(app.getLevelDificulty()-1);

            if(!level.getBoolean(String.valueOf(i))){
                enable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ImageIcon image = createIcon(i, size);
            b = new JButton(image);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            if(!enable) b.setEnabled(false);
        } 

        catch (IOException exception) {
            exception.printStackTrace();
        }
        return b;
    }

    public ImageIcon createIcon(int i, double size) throws IOException{
        File file = new File(App.currentDirectory+"/src/main/resources/button/level/level" + i + ".png");
        Image bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(width*size), (int)(height*size), Image.SCALE_DEFAULT));
        return image;
    }

}
