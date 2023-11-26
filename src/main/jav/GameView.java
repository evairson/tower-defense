package jav;

import javax.swing.JFrame;

public class GameView extends JFrame{
    private GameController control;
    private App game;

    GameView(){
        this.setTitle("Tower Defense");
        this.setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }


}
