package java.Personnages;

import java.Maps.Cordonnee;

public abstract class Ennemis implements Perso {
    private int pv;
    private int valeur;
    private int vitesse;
    private int degat;
    private String image;
    private int porte;
    private int vitessedegat;
    private Cordonnee pos;

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
    public int getPorte(){
        return porte;
    }

    public int getVitesseDegat(){
        return vitessedegat;
    }

    public Cordonnee getPos(){
        return this.pos;
    }

    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getX() - t.getPos().getX()<= porte){
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

}


