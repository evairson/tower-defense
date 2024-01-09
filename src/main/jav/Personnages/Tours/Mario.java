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
        timebetweendegat = 2000;
        pv=40;
        degat = 5;
        prix = 20;
        range = 1;
        rangeCara= 4;
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
                CarapaceTours car;
                switch(pouvoir) {
                    case 1 : car =  new CarapaceTours(1); break;
                    case 2 : car = new CarapaceTours(2); break;
                    default : car = new CarapaceTours(0); break;
                }
                int i = 1;
                while(dejaUneTour(g, new RealCoordonnee(pos.getIntCoordonnee().getX()+i, pos.getIntCoordonnee().getY()))){
                    i ++;
                }
                car.setPos(new RealCoordonnee(pos.getIntCoordonnee().getX()+i, pos.getIntCoordonnee().getY()));
                g.getToursEnJeu().add(car);
                try {
                    g.getMap().updateContenu(g);
                } catch(DeuxToursMemeCase exc){
                    System.out.println("Attention Deux tours sur la même case !! c'est un problème de Mario");
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

    public boolean dejaUneTour(Game g, RealCoordonnee r){
        for(Tours t : g.getToursEnJeu()){
            if(r.getIntCoordonnee().getX() == t.getPos().getIntCoordonnee().getX() 
            && r.getIntCoordonnee().getY() == t.getPos().getIntCoordonnee().getY()){
                return true;
            }
        }
        return false;
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
