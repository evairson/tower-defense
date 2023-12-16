package jav.Personnages.Ennemis;

import jav.App;
import jav.Game;
import jav.Personnages.Perso;
import jav.Personnages.Tours.Tours;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;

public abstract class Ennemis extends Perso {

    protected int valeur;
    protected int timebetweenMov;

    protected long timeMov;

    public static final int frame = 100;

    Ennemis(){
        timeMov=System.currentTimeMillis();
        timeAttaque=System.currentTimeMillis();
        timeAnim=System.currentTimeMillis();
        numAnimation=1;
        timeanimationAttaqued = System.currentTimeMillis();
    }

    public int getValeur(){
        return this.valeur;
    }
    public int getTimebetweenMov(){
        return this.timebetweenMov;
    }


    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= range){
                t.enleverPv(this.degat);
            }
        }
        return false;
    }

    public boolean canMove(Game g){
        for(Tours t : g.getToursEnJeu()){
            if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && pos.getX()-t.getPos().getX() <= 3*Game.sizecase/4){
                return false;
            }
        }
        return true;
    }


    public void avancer(Game g){
        if(canMove(g)){
            pos.setX(pos.getX()-(Game.sizecase/ frame));
        }
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
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void attaquer(ArrayList<Tours> tours){
        int i=0;
        if(tours.size()!=0){
            while(i<tours.size() && !attaque(tours.get(i))){
                i++;
            }
        }
    }

    public void mort(ArrayList<Ennemis> ennemis){
        ennemis.remove(this);
    }


    public void update(Game game){
        this.pouvoir(game);

        if(image!=null){
            if(System.currentTimeMillis() - timeAnim > 200){
                nextImage();
                timeAnim =System.currentTimeMillis();
            }
        }


        if(System.currentTimeMillis() - timeMov > (timebetweenMov / frame)){
            avancer(game);
            timeMov =System.currentTimeMillis();
        }

        if(attacked){
            if(System.currentTimeMillis() - timeanimationAttaqued > 200){
                changeImageAttacked();
                timeanimationAttaqued = System.currentTimeMillis();
            }
        }


        if(System.currentTimeMillis() - timeAttaque > timebetweendegat){
            attaquer(game.getToursEnJeu());
            timeAttaque =System.currentTimeMillis();
        }

        if(mort){
            if(game.getView()!=null){
                image.setIcon(null);
            }
            mort(game.getEnnemis());
        }

        if(pos.getX()==0){
            game.gameOver();
        }
    }



}


