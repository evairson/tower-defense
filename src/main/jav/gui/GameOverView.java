package jav.gui;

import javax.swing.*;
import java.awt.*;
import jav.*;

public class GameOverView extends JFrame {
    public GameOverView(){
        setTitle("GameOver");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel fond = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/src/main/resources/GameOver.jpg");
                Image fond = imageIcon.getImage();
                g.drawImage(fond,0,0,getWidth(),getHeight(),null);
            }
        };
        getContentPane().add(fond);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
