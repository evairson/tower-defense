package jav;

import java.awt.event.MouseEvent;

import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public class GameController {
    private GameView view;
    private Game game;
    private int xSouris;
    private int ySouris;

    public GameController(GameView view){
        this.view = view;
        this.game = view.getGame();
    }

    public void Selectionnercase(String s){
        view.getImageTours(s).setVisible(true);
        view.getPanelTour().repaint();
    }

    public void suivreMouse(MouseEvent e, String s) {
        xSouris = e.getX();
        ySouris = e.getY();
        view.getImageTours(s).setLocation(xSouris - view.getImageTours(s).getWidth()/2, ySouris - view.getImageTours(s).getHeight()/2);
        view.getPanelTour().repaint();
    }

    public void ajouterTour(String s){
        view.getImageTours(s).setVisible(false);
        RealCoordonnee cordSouris = new RealCoordonnee(xSouris, ySouris);
        if(RealCoordonnee.getIntCoordonneeXY(xSouris)>=0 && RealCoordonnee.getIntCoordonneeXY(xSouris) < game.getMap().getLargeur()-1
        && RealCoordonnee.getIntCoordonneeXY(ySouris)>=1 && RealCoordonnee.getIntCoordonneeXY(ySouris)<= game.getMap().getLongeur()){
            game.createTours(s, RealCoordonnee.getIntCoordonneeXY(xSouris), RealCoordonnee.getIntCoordonneeXY(ySouris)-1);
            view.buttonTourMoins(s);
        }
    }

}
