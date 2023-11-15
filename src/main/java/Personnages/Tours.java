package java.Personnages;

public abstract class Tours {
    private int pv;
    private int degat;
    private int prix;
    private int niveau;
    private String image;
    public int getPvTours(){
        return this.pv;
    }
    public int getDegat(){
        return this.degat;
    }
    public int getPrix(){
        return this.prix;
    }
    public int getNiveau(){
        return this.niveau;
    }


}
