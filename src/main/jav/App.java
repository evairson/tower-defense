package jav;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Scanner;

public class App {
    private GameView view;
    private JeuTexte jeuTexte;

    private MenuDepartView viewMenuDepart;
    private GameOverView gameOverView;
    public static final String currentDirectory = System.getProperty("user.dir");

    public static void main(String[] args) {
        App app = new App();
        app.TypeGame();
    }

    public void TypeGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Jeu avec l'interface graphique 2: Jeu sur le terminal");
        try {
            int rep = Integer.valueOf(sc.nextLine()); 
            switch(rep){
                case 1: afficheMenu(); break;
                case 2: GameTerminal(); break;
                default: TypeGame(); break;
            }}
        catch(NumberFormatException e){
            System.out.println("Rééssaayer");
            TypeGame();return;
        }

    }

    public void GameInterface(){
        view = new GameView(5,10,5);
        view.setVisible(true);
        view.control = new GameController(view,this);
        view.getButton("mario").addChangeListener((event) -> {
            if(view.getGame().getJoueur().getInventaire().get("mario")!=0){
                view.control.Selectionnercase("mario");
                mouseMoved("mario");
            }
        });
        view.getButton("luigi").addChangeListener((event) -> {
            view.control.Selectionnercase("luigi");
            mouseMoved("luigi");
        });
        view.getButton("peach").addChangeListener((event) -> {
            view.control.Selectionnercase("peach");
            mouseMoved("peach");
        });
    }

    public void mouseMoved(String s){
        MouseMotionAdapter mouse = new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                view.control.suivreMouse(e, s);
            }
        };
        view.addMouseMotionListener(mouse);
        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                view.removeMouseMotionListener(mouse);
                view.control.ajouterTour(s);
            }
        });
    }

    public void GameTerminal(){
        jeuTexte = new JeuTexte(5, 20, 5);
    }
    public void afficheMenu(){
        viewMenuDepart = new MenuDepartView(this);
    }
    public void lanceJeuInterface(){
        viewMenuDepart.dispose();
        GameInterface();
    }
    public void afficheGameOver(){
        view.dispose();
        gameOverView = new GameOverView();
    }
}
