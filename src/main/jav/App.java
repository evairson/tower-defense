package jav;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Scanner;

import javax.print.attribute.standard.RequestingUserName;

public class App {
    private GameView view;
    private JeuTexte jeuTexte;
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
                case 1: GameInterface(); break;
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
        view.control = new GameController(view);

        view.getButton("mario").addActionListener(utiliserTour("mario"));
        view.getButton("luigi").addActionListener(utiliserTour("luigi"));
        view.getButton("peach").addActionListener(utiliserTour("peach"));

        view.getAcheter().addActionListener(modeAchat());
    }

    public ActionListener modeAchat(){
        ActionListenerTour achat = new ActionListenerTour() {
            public void actionPerformed(ActionEvent e) {
                changeButton("mario");
                changeButton("luigi");
                changeButton("peach");
                view.control.changeInterfaceButton();
            }
        };
        return achat;
    }

    public ActionListenerTour utiliserTour(String s)  {
        
        ActionListenerTour utiliser = new ActionListenerTour() {

            public void actionPerformed(ActionEvent e) {
                if(view.getGame().getJoueur().getInventaire().get(s)!=0){
                    view.control.Selectionnercase(s);
                    mouseMoved(s);
                }
            }
        };
        return utiliser;
    }

    public void changeButton(String s){
        if(((ActionListenerTour)view.getButton(s).getActionListeners()[0]).isAchat()){
            view.getButton(s).removeActionListener(view.getButton(s).getActionListeners()[0]);
            view.getButton(s).addActionListener(utiliserTour(s));
        }
        else {
            view.getButton(s).removeActionListener(view.getButton(s).getActionListeners()[0]);
            view.getButton(s).addActionListener(acheterTour(s));
        }
    }

    public ActionListenerTour acheterTour(String s)  {
        ActionListenerTour achat = new ActionListenerTour() {
            @Override
            public boolean isAchat() {
                return true;
            }
            public void actionPerformed(ActionEvent e) {
                view.control.addTour(s);
            }
        };
        return achat;
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
}
