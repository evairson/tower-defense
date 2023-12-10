package jav.Personnages;

import javax.swing.JLabel;

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
    protected double scale;

    public double getScale(){
        return scale;
    }


    public void setPos(RealCoordonnee c){
        pos = c;
    }

    public int getTimebetweenDegat(){
        return timebetweendegat;
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

    public abstract void update(Game game);
    public abstract void pouvoir(Game g);
}
