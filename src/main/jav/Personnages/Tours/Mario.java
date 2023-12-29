package jav.Personnages.Tours;

import jav.Game;
import jav.Exception.DeuxToursMemeCase;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Lanceur;
import jav.Personnages.Perso;
import jav.Personnages.Pouvoirs.Etoile;
import jav.Personnages.Pouvoirs.Fleur;

public class Mario extends Tours implements Lanceur, TourAttaque, Fleur, Etoile{
    private int rangeCara;
    private int pouvoir;

    public Mario(RealCoordonnee pos){
        super();
        timeAttaque = System.currentTimeMillis();
        timebetweendegat = 4000;
        pv=40;
        degat = 20;
        prix = 20;
        range = 1;
        rangeCara= 6;
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
                CarapaceTours car = switch(pouvoir) {
                    case 1 -> new CarapaceTours(1);
                    case 2 -> new CarapaceTours(2);
                    default -> new CarapaceTours(0);};
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
        Fleur.super.toFlower(this);
        rangeCara +=1;
        url="tours/mario/marioFleur/mario";
        nextImage();
        pouvoir = 1;
    }

    public void toStar(){
        Etoile.super.toStar(this);
        rangeCara +=2;
        url="tours/mario/marioEtoile/mario";
        nextImage();
        pouvoir = 2;
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
