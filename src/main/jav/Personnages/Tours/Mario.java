package jav.Personnages.Tours;

import jav.Game;
import jav.Exception.DeuxToursMemeCase;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Lanceur;
import jav.Personnages.Perso;

public class Mario extends Tours implements Lanceur, TourAttaque{
    private int rangeCara;

    public Mario(RealCoordonnee pos){
        super();
        timeAttaque = System.currentTimeMillis();
        timebetweendegat = 4000;
        pv=40;
        degat = 20;
        prix = 20;
        range = 1;
        rangeCara= 6;
        mort = false;
        this.pos = pos;
        lettre = "MA";
        url = "tours/mario/mario";
        scale = 1;
        nbimageAnimation = 3;
    }

    public boolean lancer(Perso e, Game g){
        if(e.getPos().getY()==pos.getY()){
            if(e.getPos().getIntCoordonnee().getX() - this.pos.getIntCoordonnee().getX() <= rangeCara && e.getPos().getIntCoordonnee().getX() - this.pos.getIntCoordonnee().getX() >= 2){
                isAnimed = true;
                CarapaceTours car = new CarapaceTours();
                car.setPos(new RealCoordonnee(pos.getIntCoordonnee().getX()+1, pos.getIntCoordonnee().getY()));
                g.getToursEnJeu().add(car);
                try {

                    g.getMap().updateContenu(g);
                } catch(DeuxToursMemeCase exc){
                    System.out.println("Attention Deux tours sur la même case !!");
                }
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
        return rangeCara;
    }

    
}
