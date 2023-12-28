package jav.Personnages;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jav.App;
import jav.Game;
import jav.Maps.RealCoordonnee;

public abstract class Perso {
    protected int degat;
    protected String url;
    protected int pv;
    protected boolean mort;
    protected JLabel image;
    protected int nbimageAnimation;
    protected int numAnimation;
    protected int range;
    protected String lettre;
    protected RealCoordonnee pos;
    protected int timebetweendegat;
    protected long timeAttaque;
    protected long timeanimationAttaqued;
    protected double scale;
    protected boolean attacked;
    protected boolean alreadyAttacked;

    protected double timeAnim;

    public Perso(){
        numAnimation =1;
        timeAttaque=System.currentTimeMillis();
        timeAnim =System.currentTimeMillis();
        timeanimationAttaqued = System.currentTimeMillis();
    }

    public void addDegat(int i){
        degat += i;
    }

    public double getScale(){
        return scale;
    }

    public boolean isAttacked(){
        return attacked;
    }

    public void setAttacked(boolean b){
        attacked = b;
    }


    public void setPos(RealCoordonnee c){
        pos = c;
    }

    public int getTimebetweenDegat(){
        return timebetweendegat;
    }

    public void changeImageAttacked(){
        try{
            String light = "";
            if(!alreadyAttacked){ light = "L";
            alreadyAttacked = true;}
            else {alreadyAttacked = false;
            attacked = false;}
            File file = new File(App.currentDirectory + "/src/main/resources/" + getUrl()+light+numAnimation+".png");
            Image bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(Game.sizecase))/height)*getScale()), (int)(Game.sizecase*getScale()), Image.SCALE_DEFAULT));
            image.setIcon(imageIcon2);
        }
        catch (IOException exception) {
            System.out.println("Voici l'url : " + App.currentDirectory + "/src/main/resources/" + getUrl()+"L"+numAnimation+".png");
            System.out.println("Voici l'url 2 : " + App.currentDirectory + "/src/main/resources/" + getUrl()+""+numAnimation+".png");
            System.out.println("Le perso : " + lettre + " a posé problème"); 
            exception.printStackTrace();
        }
    }
    
    public void enleverPv(int degat){
        if(pv-degat >0){
            pv = pv - degat;
        }
        else {
            this.meurt();
        }
    }

    public void meurt(){
        mort=true;
    }

    public String getUrl(){
        return url;
    }

    public RealCoordonnee getPos(){
        return this.pos;
    }

    public String getLettre(){
        return lettre;
    }

    public JLabel getImage(){
        return image;
    }

    public void setImage(JLabel jlabel){
        image = jlabel;
    }


    public int getPv(){
        return this.pv;
    }

    public int getDegat(){
        return this.degat;
    }
    public int getRange(){
        return range;
    }

    public void nextImage(){
        if(numAnimation<nbimageAnimation){
            numAnimation++;
        }
        else numAnimation =1;
        try{
            File file = new File(App.currentDirectory + "/src/main/resources/" + getUrl()+numAnimation+".png");
            Image bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(Game.sizecase))/height)*getScale()), (int)(Game.sizecase*getScale()), Image.SCALE_DEFAULT));
            image.setIcon(imageIcon2);
            image.setBounds((int)getPos().getX(), (int)(getPos().getY()+12*Game.sizecase/7) -getImage().getIcon().getIconHeight(), (int)(Game.sizecase*getScale())+1, (int)(Game.sizecase*getScale())+1); 
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

    }


    public abstract void update(Game game);
    public abstract void pouvoir(Game g);
}
