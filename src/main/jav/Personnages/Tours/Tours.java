package jav.Personnages.Tours;

import jav.*;
import jav.gui.*;
import jav.Personnages.Perso;
import jav.Personnages.Ennemis.Boo;
import jav.Personnages.Ennemis.Ennemis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;

public abstract class Tours extends Perso{
    protected int prix;
    protected int niveau;
    protected boolean isAnimed;
    public static final String[] listTour = {"mario","luigi","peach","tuyau","fleur","etoile"};


    public Tours(){
        super();
    }

    public boolean isAnimed(){
        return isAnimed;
    }

    public void setAnimed(boolean b){
        isAnimed = b;
    }

    public int getPrix(){
        return this.prix;
    }
    public int getNiveau(){
        return this.niveau;
    }

    public void mort(ArrayList<Tours> tours){
        tours.remove(this);
    }

    @Override
    public void nextImage(){
        if(numAnimation<nbimageAnimation){
            numAnimation++;
        }
        else {
            numAnimation =1;
            isAnimed = false;
        }
        try{
            File file = new File(App.currentDirectory + "/src/main/resources/" + getUrl()+numAnimation+".png");
            Image bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(Game.sizecase))/height)*getScale()), (int)(Game.sizecase*getScale()), Image.SCALE_DEFAULT));
            image.setIcon(imageIcon2);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    
    public void update(Game game){
        this.pouvoir(game);

        if(image!=null && isAnimed){
            if(System.currentTimeMillis() - timeAnim > 200){
                nextImage();
                timeAnim =System.currentTimeMillis();
            }
        }

        if(image!=null && attacked){
            if(System.currentTimeMillis() - timeanimationAttaqued > 200){
                changeImageAttacked();
                timeanimationAttaqued = System.currentTimeMillis();
            }
        }

        if(this instanceof TourAttaque){
            if(System.currentTimeMillis() - timeAttaque > timebetweendegat){
                ((TourAttaque)this).attaquer(game.getEnnemis());
                timeAttaque =System.currentTimeMillis();
            }
        }


        if(mort){
            if(game.getView()!=null){
                image.setIcon(null);
            }
            mort(game.getToursEnJeu());    
        }
    }

}
