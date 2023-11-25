package jav.Personnages.Ennemis;

import java.util.ArrayList;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Personnages.Tours.Tours;

public class Koopa extends Ennemis implements Lanceur {
    private int rangeCara;
    private boolean hasCarapace;
    public Koopa(){
        super();
        lettre='K';
        pv=40;
        valeur=20;
        vitesse=3000;
        degat=2;
        range=1;
        rangeCara = 6;
        vitessedegat=5000;
        hasCarapace = true;
    }

    public int GetrangeProj(){
        return rangeCara;
    }

    
    public boolean lancer(Tours t, Game g){
        if(t.getPos().getY()==pos.getY()){
            if(this.pos.getX() - t.getPos().getX()<= rangeCara && this.pos.getX() - t.getPos().getX()>= 2){
                Carapace car = new Carapace();
                car.setPos(new Coordonnee(pos.getX()-1, pos.getY()));
                g.getEnnemis().add(car);
                g.getMap().updateContenu(g);
                hasCarapace=false;
            }
        }
        return false;
    }



    public void pouvoir(Game g){
        if(hasCarapace){
            int i=0;
            if(g.getToursEnJeu().size()!=0){
                while(i<g.getToursEnJeu().size() && !lancer(g.getToursEnJeu().get(i), g)){
                    i++;
                }
            }
        }

    }
}
