package jav;

import java.awt.event.MouseEvent;

import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;

public class GameController {
    private GameView view;
    private Game game;
    private int xSouris;
    private int ySouris;

    private App app;

    public GameController(GameView view, App app){
        this.app = app;
        this.view = view;
        this.game = view.getGame();
    }

    public void Selectionnercase(String s){
        view.getImageTours(s).setVisible(true);
        view.getPanelTour().repaint();
    }

    public void addTourInventaire(String s){
        if(game.getJoueur().getMonnaie()>= game.getJoueur().getBoutique().get(s)){
            game.getJoueur().acheter(s);
        }
        view.buttonTourUpdate(s);
        view.changeArgent();
    }

    public void changeInterfaceButton(){
        if(view.getAcheter().getText().equals("Acheter")){
            view.getAcheter().setText("Utiliser");
        }
        else {
            view.getAcheter().setText("Acheter");
        }
    }

    public void suivreMouse(MouseEvent e, String s) {
        xSouris = e.getX();
        ySouris = e.getY();
        view.getImageTours(s).setLocation(xSouris - view.getImageTours(s).getWidth()/2, ySouris - view.getImageTours(s).getHeight()/2);
        view.getPanelTour().repaint();
    }

    public void ajouterTour(String s){
        view.getImageTours(s).setVisible(false);
        if(RealCoordonnee.getIntCoordonneeXY(xSouris)>=0 && RealCoordonnee.getIntCoordonneeXY(xSouris) < game.getMap().getLargeur()-1
        && RealCoordonnee.getIntCoordonneeXY(ySouris)>=1 && RealCoordonnee.getIntCoordonneeXY(ySouris)<= game.getMap().getLongeur()){
            if((game.getMap().getCase(RealCoordonnee.getIntCoordonneeXY(xSouris),  RealCoordonnee.getIntCoordonneeXY(ySouris)-1).getContenuEnnemis() == null ||
            game.getMap().getCase(RealCoordonnee.getIntCoordonneeXY(xSouris),  RealCoordonnee.getIntCoordonneeXY(ySouris)-1).getContenuEnnemis().size() == 0)
            &&  (game.getMap().getCase(RealCoordonnee.getIntCoordonneeXY(xSouris),  RealCoordonnee.getIntCoordonneeXY(ySouris)-1).getContenuTours() == null || s=="fleur") )
            game.createTours(s, RealCoordonnee.getIntCoordonneeXY(xSouris), RealCoordonnee.getIntCoordonneeXY(ySouris)-1);
            view.buttonTourUpdate(s);
        }
    }
    public void afficheGameOver(){
        app.afficheGameOver();
    }

}
