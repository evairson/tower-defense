package jav;

import java.util.Scanner;

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
    }

    public void GameTerminal(){
        jeuTexte = new JeuTexte(5, 20, 5);
    }
}
