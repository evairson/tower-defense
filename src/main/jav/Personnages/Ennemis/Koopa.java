package jav.Personnages.Ennemis;

import jav.Game;
import jav.Exception.DeuxToursMemeCase;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Lanceur;
import jav.Personnages.Perso;
import jav.Personnages.Tours.Tuyau;

public class Koopa extends Ennemis implements Lanceur {
    private int rangeCara;
    private boolean hasCarapace;
    public Koopa(){
        super();
        lettre="K ";
        pv=40;
        valeur=10;
        timebetweenMov=3000;
        degat=2;
        range=1;
        rangeCara = 6;
        timebetweendegat=5000;
        hasCarapace = true;
        url = "ennemis/koopa/koopa";
        nbimageAnimation = 8;
        scale = 0.7;
        setLevelDificulty();
    }

    public int GetrangeProj(){
        return rangeCara;
    }

    
    public boolean lancer(Perso t, Game g){
        if(t.getPos().getY()==pos.getY() && !(t instanceof Tuyau)){
            if(this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= rangeCara && this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()>= 2){
                Carapace car = new Carapace();
                car.setPos(new RealCoordonnee(pos.getIntCoordonnee().getX()-1, pos.getIntCoordonnee().getY()));
                g.getEnnemis().add(car);
                try {
                    g.getMap().updateContenu(g);
                } catch(DeuxToursMemeCase exc){
                    System.out.println("Attention Deux tours sur la même case c'est un problème de Koopa!!");
                }
                hasCarapace=false;
                return true;
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
