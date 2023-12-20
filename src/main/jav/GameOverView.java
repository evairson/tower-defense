package jav;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JFrame {
    public GameOverView(){
        setTitle("GameOver");
        setSize(1500,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel fond = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/main/resources/GameOver.jpg");
                Image fond = imageIcon.getImage();
                g.drawImage(fond,0,0,getWidth(),getHeight(),null);
            }
        };
        getContentPane().add(fond);
        setVisible(true);
    }
}
