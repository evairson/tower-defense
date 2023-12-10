package jav.Maps;

import jav.Personnages.Perso;

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
        String perso = ". ";
        if(contenu!=null){
            perso = contenu.getLettre();
        }
        System.out.print(perso+" ");
    }
}
