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
    public MenuDepartView(App app){
        super("Menu de Départ");
        this.app = app;
        setSize(800, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel fondPanel = new JPanel(){
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/main/resources/FondMenuDepart.jpg");
              Image fond = imageIcon.getImage();
              g.drawImage(fond,0,0,getWidth(),getHeight(),null);
          }
        };
        start = new JButton("start");
        start.addActionListener(ActionListener -> app.lanceJeuInterface());
        fondPanel.setLayout(new BorderLayout());
        fondPanel.add(start, BorderLayout.SOUTH);
        getContentPane().add(fondPanel);
        setVisible(true);
    }


}
