package jav.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import jav.*;

public class JPanelPlateau extends JPanel{
    private Image haut;
    private Image bas;
    private Image milieu;
    private Game game;

    public JPanelPlateau(Game g){
        this.game = g;
        try {
            haut = ImageIO.read(new File(App.currentDirectory + "/src/main/resources/case-haut.png"));
            bas = ImageIO.read(new File(App.currentDirectory + "/src/main/resources/case-bas.png"));
            milieu = ImageIO.read(new File(App.currentDirectory + "/src/main/resources/case-milieu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for(int i=0;i<game.getMap().getLongeur();i++){
            for(int j=0; j<game.getMap().getLargeur();j++){
                if(i==game.getMap().getLongeur()-1){
                    graphics.drawImage(bas, j*Game.sizecase,i*Game.sizecase ,(int)(Game.sizecase), (int)(Game.sizecase), this);
                }
                else if(i==0){
                    graphics.drawImage(haut, j*Game.sizecase,i*Game.sizecase ,(int)(Game.sizecase), (int)(Game.sizecase), this);
                }
                else {
                    graphics.drawImage(milieu, j*Game.sizecase,i*Game.sizecase ,(int)(Game.sizecase), (int)(Game.sizecase), this);
                }
            }
        }
      
    }


}
