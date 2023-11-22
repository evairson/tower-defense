package jav.Maps;

import jav.Personnages.Perso;
import jav.Personnages.Tours;
import jav.Personnages.Ennemis.Ennemis;

public class Case {
    private boolean base;
    private boolean depart;
    private Perso contenu;

    public Case(){
        base=false;
        depart=false;
        contenu=null;
    }

    public boolean EstUneBase(){
        return base;
    }

    public boolean EstUnDepart(){
        return depart;
    }

    public Perso getContenu(){
        return contenu;
    }

    public void setContenu(Perso p){
        contenu = p;
    }

    public void afficher(){
        String perso = ".";
        if(contenu!=null){
            if(contenu instanceof Ennemis) perso ="e";
            if(contenu instanceof Tours) perso ="t";
        }
        System.out.print(perso+" ");
    }
}
