package jav.Personnages.Ennemis;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Perso;
import jav.Personnages.Tours.Tours;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;

public abstract class Ennemis extends Perso {

    protected int valeur;
    protected int timebetweenMov;
    protected int timebetweendegat;
    protected long timeMov;
    protected long timeAttaque;
    protected double timeAnim;
    private int frame;

    Ennemis(){
        timeMov=System.currentTimeMillis();
        timeAttaque=System.currentTimeMillis();
        timeAnim=System.currentTimeMillis();
        numAnimation=1;
        frame = 100;
    }
    public void toFlower(String image){
        System.out.println("Vous ne pouvez pas utiliser de pouvoirs sur un ennemi");
    }

    public void toStar(String image){
       System.out.println("Vous ne pouvez pas utiliser de pouvoirs sur un ennemi");
    }

    public int getValeur(){
        return this.valeur;
    }
    public int getTimebetweenMov(){
        return this.timebetweenMov;
    }


    public int getTimebetweenDegat(){
        return timebetweendegat;
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
            if(t.getPos().getIntCoordonnee().getY()==pos.getY() && t.getPos().getIntCoordonnee().getX()==pos.getX()-1){
                return false;
            }
        }
        for(Ennemis e : g.getEnnemis()){
            if(e.getPos().getIntCoordonnee().getY()==pos.getY() && e.getPos().getIntCoordonnee().getX()==pos.getX()-1){
                return false;
            }
        }
        return true;
    }

    public boolean depasser(Game g){
        for(Ennemis e : g.getEnnemis()){
            if(e.getClass() != this.getClass() && e.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && e.getPos().getIntCoordonnee().getX()==pos.getIntCoordonnee().getX()-1){
                return true;
            }
        }
        return false;
    }


    public void avancer(Game g){
        if(canMove(g)){
            pos.setX(pos.getX()-(Game.sizecase/ frame));
        }
        else {
            if(depasser(g)){ // a regler
                pos.setX(pos.getX()-(Game.sizecase)-(Game.sizecase/8));
            }
        }
    }

    public void nextImage(){
        if(numAnimation<nbimageAnimation){
            numAnimation++;
        }
        else numAnimation =1;
        try{
            String currentDirectory = System.getProperty("user.dir");
            File file = new File(currentDirectory + "/src/main/resources/" + getUrl()+numAnimation+".png");
            Image bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            ImageIcon imageIcon2 = new ImageIcon(imageIcon.getImage().getScaledInstance(3*Game.sizecase/4, 3*Game.sizecase/4, Image.SCALE_DEFAULT));
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



        if(System.currentTimeMillis() - timeAttaque > timebetweendegat){
            attaquer(game.getToursEnJeu());
            timeAttaque =System.currentTimeMillis();
        }

        if(mort){
            mort(game.getEnnemis());
        }

        if(pos.getX()==0){
            game.gameOver();
        }
    }

    public abstract void pouvoir(Game g);

}


