package jav.Personnages.Ennemis;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Tours.Tours;

public class Lakitu extends Ennemis implements Lanceur {
    private int rangeProj;
    private boolean hasProj;
    public Lakitu(){
        super();
        lettre='L';
        pv=50;
        valeur=60;
        timebetweenMov=3000;
        degat=30;
        range=1;
        timebetweendegat=4000;
        rangeProj = 8;
        hasProj = true;
    }

    public int GetrangeProj(){
        return rangeProj;
    }


    public boolean lancer(Tours t, Game g){
       if(pos.getIntCoordonnee().getY()<g.getMap().getLongeur()-1){
            if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY()+1){
                if(this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= rangeProj && this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()>= 2){
                    Heriss her = new Heriss();
                    her.setPos(new RealCoordonnee(pos.getIntCoordonnee().getX()-1, pos.getIntCoordonnee().getY()+1));
                    g.getEnnemis().add(her);
                    g.getMap().updateContenu(g);
                    hasProj=false;
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
       
        int i = (int)(Math.random()*(100));
        if(i>90){
            if(pos.getIntCoordonnee().getY()>0){
            if(!isPersoY(g, -1)){
                pos.setY(pos.getY()-1);
            }
            }
        }
        if(i<10){
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
