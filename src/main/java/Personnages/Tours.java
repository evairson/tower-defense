package java.Personnages;

import java.Maps.Cordonnee;

public abstract class Tours implements Perso{
    private int pv;
    private int degat;
    private int prix;
    private int niveau;
    private String image;
    private int porte;
    private int vitessedegat;
    private Cordonnee pos;


    public int getPv(){
        return this.pv;
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
            if(this.pos.getX() - t.getPos().getX()<= porte){
                t.enleverPv(this.degat);
            }
        }
        return false;
    }


}
