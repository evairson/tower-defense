package java.Personnages;

import java.Maps.Cordonnee;

public abstract class Ennemis implements Perso {
    protected int pv;
    protected int valeur;
    protected int vitesse;
    protected int degat;
    protected String image;
    protected int range;
    protected int vitessedegat;
    protected Cordonnee pos;

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

    public Cordonnee getPos(){
        return this.pos;
    }

    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getX() - t.getPos().getX()<= range){
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
            this.mort();
        }
    }

    public void avancer(){
        pos.setX(pos.getX()-1);
    }

}


