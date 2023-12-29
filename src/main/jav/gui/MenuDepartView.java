package jav.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import jav.*;

public class MenuDepartView extends JFrame {
    JButton start;
    App app;

    private int longueur;
    private int largeur;
    public MenuDepartView(App app){
        super("Menu de Départ");
        this.app = app;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel fondPanel = new JPanel(){
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/src/main/resources/FondMenuDepart.jpg");
              Image fond = imageIcon.getImage();
              g.drawImage(fond,0,0,getWidth(),getHeight(),null);
          }
        };

        try {
            File file = new File(App.currentDirectory+"/src/main/resources/Play.png");
            Image bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(width*0.3), (int)(height*0.3), Image.SCALE_DEFAULT));
            start = new JButton(image);
            start.setOpaque(false);
            start.setContentAreaFilled(false);
            start.setBorderPainted(false);
            } 

        catch (IOException exception) {
            exception.printStackTrace();
        }
        start.addActionListener(ActionListener -> app.lanceJeuInterface());
        fondPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        fondPanel.add(start,gbc);
        getContentPane().add(fondPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
