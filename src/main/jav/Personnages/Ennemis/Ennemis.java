package jav.Personnages.Ennemis;

import jav.*;
import jav.gui.*;
import jav.Personnages.Perso;
import jav.Personnages.Tours.Tours;
import jav.Personnages.Tours.Tuyau;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;

public abstract class Ennemis extends Perso {

    protected int valeur;
    protected int timebetweenMov;
    protected int timeBetweenAnim;
    protected long timeMov;
    

    public static final int frame = 100;

    Ennemis(){
        timeMov=System.currentTimeMillis();
        timeBetweenAnim = 200;
    }

    public int getValeur(){
        return this.valeur;
    }
    public int getTimebetweenMov(){
        return this.timebetweenMov;
    }


    public boolean attaque(Tours t){
        if(t.getPos().getY()==pos.getY() && !(t instanceof Tuyau)){
            if(this.pos.getIntCoordonnee().getX() - t.getPos().getIntCoordonnee().getX()<= range){
                t.enleverPv(this.degat);
                t.setAttacked(true);
            }
        }
        return false;
    }

    public boolean canMove(Game g){
        for(Tours t : g.getToursEnJeu()){
            if(!(t instanceof Tuyau)){
                if(t.getPos().getIntCoordonnee().getY()==pos.getIntCoordonnee().getY() && pos.getX()-t.getPos().getX() <= 3*Game.sizecase/4){
                    return false;
                }
            }
        }
        return true;
    }


    public void avancer(Game g){
        if(canMove(g)){
            pos.setX(pos.getX()-(Game.sizecase/ frame));
        }
    }

    public void attaquer(ArrayList<Tours> tours){
        int i=0;
        if(tours.size()!=0){
            while(i<tours.size() && !attaque(tours.get(i))){
                i++;
            }
        }
    }

    public void mort(ArrayList<Ennemis> ennemis, Game g){
        ennemis.remove(this);
        g.getJoueur().gagner(valeur);
        if(g.getView() != null){
            g.getView().getControl().changeArgent();
        }
    }


    public void update(Game game){
        this.pouvoir(game);

        if(image!=null){
            if(System.currentTimeMillis() - timeAnim > timeBetweenAnim){
                nextImage();
                timeAnim =System.currentTimeMillis();
            }
            if(attacked){
                if(System.currentTimeMillis() - timeanimationAttaqued > 200){
                    changeImageAttacked();
                    timeanimationAttaqued = System.currentTimeMillis();
                }
            }
        }


        if(System.currentTimeMillis() - timeMov > (timebetweenMov / frame)){
            avancer(game);
            timeMov =System.currentTimeMillis();
        }


        if(System.currentTimeMillis() - timeAttaque > timebetweendegat){
            attaquer(game.getToursEnJeu());
            timeAttaque =System.currentTimeMillis();
        }

        if(mort){
            if(game.getView()!=null){
                image.setIcon(null);
            }
            mort(game.getEnnemis(), game);
        }

        if(pos.getX()==0){
            game.gameOver();
        }
    }



}


