package jav;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
        ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/src/main/resources/Play.png");
        start = new JButton(imageIcon);
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
