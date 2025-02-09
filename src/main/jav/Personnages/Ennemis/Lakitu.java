package jav.Personnages.Ennemis;

import jav.Game;
import jav.Exception.DeuxToursMemeCase;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Lanceur;
import jav.Personnages.Perso;
import jav.Personnages.Tours.Tours;
import jav.Personnages.Tours.Tuyau;

public class Lakitu extends Ennemis implements Lanceur {
    private int rangeProj;
    private boolean hasProj;
    public Lakitu(){
        super();
        lettre="L ";
        pv=50;
        valeur=15;
        timebetweenMov=3000;
        degat=5;
        range=1;
        timebetweendegat=4000;
        rangeProj = 8;
        hasProj = true;
        url = "ennemis/lakitu/lakitu";
        nbimageAnimation=3;
        scale = 0.7;
        setLevelDificulty();
    }

    public int GetrangeProj(){
        return rangeProj;
    }


    public boolean lancer(Perso t, Game g){
       if(pos.getIntCoordonnee().getY()<g.getMap().getLongeur()-1 && !(t instanceof Tuyau)){
            if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY()+1){
                if(this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= rangeProj && this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()>= 2){
                    Heriss her = new Heriss();
                    her.setPos(new RealCoordonnee(pos.getIntCoordonnee().getX()-1, pos.getIntCoordonnee().getY()+1));
                    g.getEnnemis().add(her);
                    try {
                        g.getMap().updateContenu(g);
                    } catch(DeuxToursMemeCase exc){
                        System.out.println("Attention Deux tours sur la même case !! C'est un problème de Lakitu");
                    }
                    hasProj=false;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPersoY(Game g, int i){
        
            if(g.getEnnemis().size()!=0){
                for(int k =0; k<g.getEnnemis().size(); k++){
                    Ennemis e = g.getEnnemis().get(k);
                    if(e.getPos().getIntCoordonnee().getX()==pos.getIntCoordonnee().getX() && e.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY()+i){
                        return true;
                    }
                }
            }
            if(g.getToursEnJeu().size()!=0){
                for(int k =0; k<g.getToursEnJeu().size(); k++){
                    Tours t = g.getToursEnJeu().get(k);
                    if(t.getPos().getIntCoordonnee().getX()==pos.getIntCoordonnee().getX() && t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY()+i){
                        return true;
                    }
                }
            }

        
        return false;

    }

    @Override
    public void avancer(Game g){

        super.avancer(g);
       
        int i = (int)(Math.random()*(500));
        if(i==499){
            if(pos.getIntCoordonnee().getY()>0){
            if(!isPersoY(g, -1)){
                pos.setY(pos.getY()-Game.sizecase);
            }
            }
        }
        if(i==0){
            if(pos.getIntCoordonnee().getY()<g.getMap().getLongeur()-1){
            if(!isPersoY(g, 1)){
                pos.setY(pos.getY()+Game.sizecase);
            }
            }
        }
        
    }

    public void pouvoir(Game g){
        if(hasProj){
            int i=0;
            if(g.getToursEnJeu().size()!=0){
                while(i<g.getToursEnJeu().size() && !lancer(g.getToursEnJeu().get(i), g)){
                    i++;
                }
            }
        }
    }
 
}
