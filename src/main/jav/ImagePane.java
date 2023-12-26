package jav;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePane extends JPanel{
    private Game game;
    private String img;
    private Image image;

    public ImagePane(Game g, String img){
        super();
        game = g;
        this.img = img;
        getImage();
    }

    public void getImage(){
        try {
        File file = new File(App.currentDirectory + "/src/main/resources/"+img);
        Image bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(Game.sizecase), (int)(Game.sizecase), Image.SCALE_DEFAULT));
        image = imageIcon2.getImage();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
            graphics.drawImage(image, 0, 0, this);
            
        
      
    }
}
