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

public abstract class Ennemis implements Perso {
    protected int pv;
    protected int valeur;
    protected int vitesse;
    protected int degat;
    protected String url;
    protected int range;
    protected int vitessedegat;
    protected RealCoordonnee pos;
    protected boolean mort;
    protected long timeMov;
    protected long timeAttaque;
    protected char lettre;
    protected JLabel image;
    protected int nbimageAnimation;
    protected int numAnimation;
    protected double timeAnim;

    Ennemis(){
        timeMov=System.currentTimeMillis();
        timeAttaque=System.currentTimeMillis();
        timeAnim=System.currentTimeMillis();
        numAnimation=1;
    }

    public String getUrl(){
        return url;
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
    public int getValeur(){
        return this.valeur;
    }
    public int getVitesse(){
        return this.vitesse;
    }
    public int getDegat(){
        return this.degat;
    }
    public int getRange(){
        return range;
    }

    public int getVitesseDegat(){
        return vitessedegat;
    }

    public RealCoordonnee getPos(){
        return this.pos;
    }

    public char getLettre(){
        return lettre;
    }

    public void setPos(RealCoordonnee c){
        pos = c;
    }

    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= range){
                t.enleverPv(this.degat);
            }
        }
        return false;
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
            pos.setX(pos.getX()-(Game.sizecase/100));
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

        if(System.currentTimeMillis() - timeAnim > 200){
            nextImage();
            timeAnim =System.currentTimeMillis();
        }

        if(System.currentTimeMillis() - timeMov > (vitesse/100)){
            avancer(game);
            timeMov =System.currentTimeMillis();
        }



        if(System.currentTimeMillis() - timeAttaque > vitessedegat){
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


