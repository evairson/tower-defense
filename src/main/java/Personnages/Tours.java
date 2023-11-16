package java.Personnages;

import java.Maps.Cordonnee;

public abstract class Tours implements Perso{
    protected int pv;
    protected int degat;
    protected int prix;
    protected int niveau;
    protected String image;
    protected int range;
    protected int vitessedegat;
    protected Cordonnee pos;


    public int getPv(){
        return this.pv;
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

    public int getPrix(){
        return this.prix;
    }
    public int getNiveau(){
        return this.niveau;
    }

    public Cordonnee getPos(){
        return this.pos;
    }

    public void enleverPv(int degat){
        if(pv-degat >0){
            pv = pv - degat;
        }
        else {
            this.mort();
        }
    }

    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getX() - t.getPos().getX()<= range){
                t.enleverPv(this.degat);
            }
        }
        return false;
    }


}
