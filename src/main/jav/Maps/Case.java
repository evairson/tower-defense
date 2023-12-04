package jav.Maps;

import jav.Personnages.Luigi;
import jav.Personnages.Mario;
import jav.Personnages.Peach;
import jav.Personnages.Perso;
import jav.Personnages.Tours;
import jav.Personnages.TuyauTank;
import jav.Personnages.TuyauTeleportation;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Ennemis.Goomba;
import jav.Personnages.Ennemis.Koopa;
import jav.Personnages.Ennemis.Lakitu;
import jav.Personnages.Ennemis.Plante;
import jav.Personnages.Ennemis.Boo;
import jav.*;

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
            if(contenu.getClass() == Boo.class) perso ="B";
            if(contenu.getClass() == Goomba.class) perso ="G";
            if(contenu.getClass() == Koopa.class) perso ="K";
            if(contenu.getClass() == Lakitu.class) perso ="L";
            if(contenu.getClass() == Plante.class) perso ="P";
            if(contenu instanceof Mario) perso ="M";
            if(contenu instanceof Peach) perso ="PE";
            if(contenu instanceof Luigi) perso ="LU";
            if(contenu instanceof TuyauTank) perso ="T";
            if (contenu instanceof TuyauTeleportation) perso = "TT";
        }
        System.out.print(perso+" ");
    }
}
