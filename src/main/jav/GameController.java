package jav;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import jav.Maps.Case;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Pouvoirs.Fleur;

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
        if(canAddTour(s)){
            game.createTours(s, RealCoordonnee.getIntCoordonneeXY(xSouris), RealCoordonnee.getIntCoordonneeXY(ySouris) - 1);
            view.buttonTourUpdate(s);
           }
    }
    public void afficheGameOver(){
        app.afficheGameOver();
    }

    public boolean canAddTour(String s){
        int cordXSouris = RealCoordonnee.getIntCoordonneeXY(xSouris);
        int cordYSouris = RealCoordonnee.getIntCoordonneeXY(ySouris);
        Case caseTour = game.getMap().getCase(cordXSouris,  cordYSouris-1);
        if (cordXSouris>=0 && cordXSouris < game.getMap().getLargeur()-1 // est bien dans le plateau
        && cordYSouris >=1 && cordYSouris <= game.getMap().getLongeur()) {

            if((caseTour.getContenuEnnemis() == null || caseTour.getContenuEnnemis().size() == 0)){ //pas d'ennemis dessus

                if(caseTour.getContenuTours() == null || (caseTour.getContenuTours() != null && caseTour.getContenuTours() instanceof Fleur && s=="fleur")){

                    return true;
                }
            }
         }
        return false;
    }

}
