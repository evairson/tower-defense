package jav.Personnages.Tours;

import jav.Game;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Lanceur;
import jav.Personnages.Perso;
import jav.Personnages.Ennemis.Carapace;
import jav.Personnages.Ennemis.Ennemis;

public class Mario extends Tours implements Lanceur{
    public Mario(RealCoordonnee pos){
    timeAttaque = System.currentTimeMillis();
    timebetweendegat = 4000;
    pv=60;
    degat = 20;
    prix = 20;
    range = 6;
    range= 6;
    mort = false;
    this.pos = pos;
    lettre = "MA";
    }

    public boolean lancer(Perso e, Game g){
        if(e.getPos().getY()==pos.getY()){
            if(this.pos.getX() - e.getPos().getX()<= range && this.pos.getX() - e.getPos().getX()>= 2){
                Carapace car = new Carapace(false);
                car.setPos(new RealCoordonnee(pos.getIntCoordonnee().getX()+1, pos.getIntCoordonnee().getY()));
                g.getEnnemis().add(car);
                g.getMap().updateContenu(g);
                return true;
            }
        }
        return false;
    }

    public void toFlower(){
        this.url = "";
        switch(this.niveau){
            case 0 -> {this.pv = 100; this.degat+=15;this.niveau++;break;}
            case 2 -> {this.pv = 100;this.degat-=15;this.niveau--;break;}
            default -> {break;}
        }
    }

    public void toStar(){
        this.url = "";
        switch(this.niveau){
            case 0 -> {this.pv = 150;this.degat+=35;this.niveau+=2;break;}
            case 1 -> {this.pv = 150;this.degat+=15;this.niveau++;break;}
            default -> {break;}
        }
    }

    public void pouvoir(Game g){
        if(System.currentTimeMillis() - timeAttaque > timebetweendegat){
            int i=0;
            if(g.getEnnemis().size()!=0){
                while(i<g.getEnnemis().size()){
                    if(!lancer(g.getEnnemis().get(i), g)){
                        i++;
                    }
                    else{
                        timeAttaque = System.currentTimeMillis();
                        return;
                    } 
                }
            }
        }

        
    }

    @Override
    public int GetrangeProj() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetrangeProj'");
    }

    
}
