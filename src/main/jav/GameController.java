package jav;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.text.View;
import jav.*;

import jav.Maps.Case;
import jav.Maps.Coordonnee;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Pouvoirs.Etoile;
import jav.Personnages.Pouvoirs.Fleur;
import jav.gui.GameOverView;
import jav.gui.GameView;

public class GameController {
    private GameView view;
    private Game game;
    private App app;
    private int xSouris;
    private int ySouris;


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
        buttonTourUpdate(s);
        changeArgent();
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
            buttonTourUpdate(s);
           }
    }

    public void levelSuivant(){
        view.dispose();
        System.out.println(Game.getLvl());
        EventQueue.invokeLater( () -> {
            switch(Game.getLvl()){
                case 1 : Game.setNextLvl(); view = new GameView(3,10,10, app, app.getLevelDificulty());  break;
                case 2 : Game.setNextLvl(); view = new GameView(4,10,20, app, app.getLevelDificulty());  break;
                case 3 : Game.setNextLvl();  view = new GameView(5,10,50, app, app.getLevelDificulty()); break;
                case 4 : new GameOverView(app, "ecranWin"); break;
            }
            
            view.setVisible(true);
        });
    }

    public void afficheGameOver(){
        view.dispose();
        EventQueue.invokeLater( () -> {
            new GameOverView(app, "gameOver");
        });
    }

    public void changeButton(String s){
        if(((ActionListenerTour)view.getButton(s).getActionListeners()[0]).isAchat()){
            view.getButton(s).removeActionListener(view.getButton(s).getActionListeners()[0]);
            view.getButton(s).addActionListener(view.utiliserTour(s));
        }
        else {
            view.getButton(s).removeActionListener(view.getButton(s).getActionListeners()[0]);
            view.getButton(s).addActionListener(view.acheterTour(s));
        }
        buttonTourUpdate(s);
    }

    public boolean canAddTour(String s){
        int cordXSouris = RealCoordonnee.getIntCoordonneeXY(xSouris);
        int cordYSouris = RealCoordonnee.getIntCoordonneeXY(ySouris);
        if (cordXSouris>=0 && cordXSouris < game.getMap().getLargeur()-1 // est bien dans le plateau
        && cordYSouris >=1 && cordYSouris <= game.getMap().getLongeur()) {

            Case caseTour = game.getMap().getCase(cordXSouris,  cordYSouris-1);
            if((caseTour.getContenuEnnemis() == null || caseTour.getContenuEnnemis().size() == 0) || (caseTour.getContenuTours() instanceof Fleur && s=="fleur" 
            || caseTour.getContenuTours() instanceof Etoile && s=="etoile")){ //pas d'ennemis dessus ou pouvoir

                if(caseTour.getContenuTours() == null || (caseTour.getContenuTours() != null && (caseTour.getContenuTours() instanceof Fleur && s=="fleur" 
                || caseTour.getContenuTours() instanceof Etoile && s=="etoile"))){
                    return true;
                }
            }
         }
        return false;
    }

    public void updateButtonInventaireTours(String tour) throws IOException {
        JButton b = view.getButton(tour);
        String couleur = game.getJoueur().getInventaire().get(tour)==0 ? "N" : "1";
        File file = new File(App.currentDirectory + "/src/main/resources/tours/"+tour+"/"+tour+couleur+".png");
        Image bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(Game.sizecase))/height)*0.5), (int)(Game.sizecase*0.5), Image.SCALE_DEFAULT));
        b.setIcon(image);
        if(view.getButton(tour).getActionListeners().length != 0 && ((ActionListenerTour)view.getButton(tour).getActionListeners()[0]).isAchat()){
            b.setText(String.valueOf(game.getJoueur().getBoutique().get(tour)) + "$");
        }
        else {
            b.setText(String.valueOf(game.getJoueur().getInventaire().get(tour)));
        }

        b.setVerticalTextPosition(SwingConstants.TOP);
        if(game.getJoueur().getInventaire().get(tour)==0){
            b.setBackground(Color.gray);
        }
        b.setHorizontalTextPosition(SwingConstants.CENTER);
        b.setBounds(view.getButton(tour).getX(), Game.sizecase/8, 3*Game.sizecase/4, 3*Game.sizecase/4); 

    }

    public void buttonTourUpdate(String s){
        try{
            updateButtonInventaireTours(s);
            view.getInventairePane().repaint();
        } catch (IOException exception) {
            exception.printStackTrace();
        }    
    }  

    public void changeArgent(){
        view.getArgent().setText("Argent : " + String.valueOf(game.getJoueur().getMonnaie()));
        view.getInventairePane().repaint();
    }

}
