package java.Maps;

import java.Personnages.Perso;

public class Case {
    private boolean base;
    private boolean depart;
    private Perso contenu;

    public boolean EstUneBase(){
        return base;
    }

    public boolean EstUnDepart(){
        return depart;
    }

    public Perso getContenu(){
        return contenu;
    }

}
