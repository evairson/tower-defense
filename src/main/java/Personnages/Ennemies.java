package java.Personnages;
abstract class Ennemies {
    private int pv;
    private int valeur;
    private int vitesse;
    private int degat;
    private String image;
    public int getPvEnnemie(){
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
    abstract public void attaque();

}


