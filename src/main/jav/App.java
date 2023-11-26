package jav;

import java.util.Scanner;

public class App {
    private GameView view;
    private JeuTexte jeuTexte;
    private Game game;

    public static void main(String[] args){
        App app = new App();
        app.TypeGame();
    }

    public void TypeGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Jeu avec l'interface graphique 2: Jeu sur le terminal");
        int rep = Integer.valueOf(sc.nextLine());
        switch(rep){
            case 1: Game();
            case 2: GameTerminal();
            default: TypeGame();;
        }
    }

    public void Game(){
        view = new GameView();
        view.setVisible(true);
    }

    public void GameTerminal(){
        jeuTexte = new JeuTexte(5, 20, 5);
    }
}
