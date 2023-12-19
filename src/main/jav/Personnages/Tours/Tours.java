package jav.Personnages.Tours;

import jav.App;
import jav.Game;
import jav.Personnages.Perso;
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


    public Tours(){
        timeAttaque=System.currentTimeMillis();
        timeAnim =System.currentTimeMillis();
        timeanimationAttaqued = System.currentTimeMillis();
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


    public boolean attaque(Ennemis e){
        if(e.getPos().getY()==pos.getY()){
            if(e.getPos().getIntCoordonnee().getX() - this.pos.getIntCoordonnee().getX() <= range){
                isAnimed = true;
                e.setAttacked(true);
                e.enleverPv(this.degat);
            }
        }
        return false;
    }

    public void attaquer(ArrayList<Ennemis> e){
        int i=0;
        if(e.size()!=0){
            while(i<e.size() && !attaque(e.get(i))){
                i++;
            }
        }
    }



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

        if(attacked){
            if(System.currentTimeMillis() - timeanimationAttaqued > 200){
                changeImageAttacked();
                timeanimationAttaqued = System.currentTimeMillis();
            }
        }

        if(System.currentTimeMillis() - timeAttaque > timebetweendegat){
            attaquer(game.getEnnemis());
            timeAttaque =System.currentTimeMillis();
        }

        if(mort){
            System.out.println("ok");
            if(game.getView()!=null){
                image.setIcon(null);
            }
            mort(game.getToursEnJeu());    
        }
    }
    
    public abstract void toFlower();

    public abstract void toStar();

}
