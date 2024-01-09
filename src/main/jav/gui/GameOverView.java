package jav.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import jav.*;

public class GameOverView extends JFrame {

    private JButton replay;
    private App app;

    public GameOverView(App app, String s){
        this.app = app;
    
        setTitle("GameOver");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel fond = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(App.currentDirectory+"/src/main/resources/"+s+".png");
                Image fond = imageIcon.getImage();
                g.drawImage(fond,0,0,getWidth(),getHeight(),null);
            }
        };

        try {
            File file = new File(App.currentDirectory+"/src/main/resources/Replay.png");
            Image bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(width*0.3), (int)(height*0.3), Image.SCALE_DEFAULT));
            replay = new JButton(image);
            replay.setOpaque(false);
            replay.setContentAreaFilled(false);
            replay.setBorderPainted(false);
            replay.setFocusPainted(false);
            } 

        catch (IOException exception) {
            exception.printStackTrace();
        }

        replay.addActionListener(ActionListener -> {this.dispose(); app.relance();});
        fond.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        fond.add(replay,gbc);
        getContentPane().add(fond);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
