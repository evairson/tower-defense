package jav.Maps;

import java.util.ArrayList;
import java.util.HashMap;

import jav.Personnages.Perso;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Tours.Tours;

public class Case {
    private boolean base;
    private boolean depart;
    private Tours contenuTours;
    private ArrayList<Ennemis> contenuEnnemis;

    public Case(){
        base=false;
        depart=false;
        contenuEnnemis=new ArrayList<>();
    }

    public boolean EstUneBase(){
        return base;
    }

    public boolean EstUnDepart(){
        return depart;
    }

    public Tours getContenuTours(){
        return contenuTours;
    }

    public ArrayList<Ennemis> getContenuEnnemis(){
        return contenuEnnemis;
    }

    public void setContenuTours(Tours t){
        contenuTours = t;
    }

    public void resetContenu(){
        contenuEnnemis.clear();
        contenuTours = null;
    }

    public void addContenuEnnemis(Ennemis e){
        contenuEnnemis.add(e);
    }

    public void afficher(){
        String perso = ".";
        String spaceTour = " ";
        String spaceEnnemi = " ";
        String ennemi = "";
        String tour = "";
        if(contenuTours!=null){
            tour = contenuTours.getLettre();
            spaceTour = "";
            perso = "";
        }
        if(contenuEnnemis.size() != 0){
            ennemi = contenuEnnemis.get(0).getLettre();
            spaceEnnemi = "";
            perso = "";
        }
        System.out.print(perso+tour+ennemi+spaceEnnemi+spaceTour);
    }
}
