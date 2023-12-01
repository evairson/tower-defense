package jav.Maps;

import jav.Personnages.Perso;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Ennemis.Goomba;
import jav.Personnages.Ennemis.Koopa;
import jav.Personnages.Ennemis.Lakitu;
import jav.Personnages.Tours.Tours;
import jav.Personnages.Ennemis.Boo;

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
        char perso = '.';
        if(contenu!=null){
            if(contenu instanceof Tours) perso ='t';
            else perso = ((Ennemis)contenu).getLettre();
        }
        System.out.print(perso+" ");
    }
}
