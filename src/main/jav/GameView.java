package jav;

import javax.imageio.ImageIO;
import javax.swing.*;

import jav.Personnages.Ennemis.Ennemis;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameView extends JFrame {
    private GameController control;
    private App app;
    private JPanel inventairePane;
    private JPanel plateauGame;
    private JPanel plateauSprite;
    private Game game;
    private int sizeCase;
    private JPanel[][] casesPanel;

    public void setGame(Game g){
        this.game = g;
    }

    GameView(int longeur, int largeur, int ennemis){    

        game = new Game(longeur, largeur, ennemis, this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        sizeCase = (int)Math.min(width/largeur, height/(longeur+1));
        Game.sizecase = sizeCase;

        this.setTitle("Tower Defense");
        this.setSize(sizeCase*largeur, sizeCase*(longeur+1));
        this.setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        inventairePane = new JPanel();
        inventairePane.setBounds(0, 0, sizeCase*largeur, sizeCase);
        inventairePane.setBackground(new Color(250, 237, 205));
        plateauGame = new JPanel();
        plateauGame.setLayout(new GridLayout(game.getMap().getLongeur(), game.getMap().getLargeur()));
        plateauGame.setBounds(0, sizeCase, sizeCase*largeur, sizeCase*longeur);


        casesPanel = new JPanel[game.getMap().getLongeur()][game.getMap().getLargeur()];

        for(int i =0;i<game.getMap().getLongeur();i++){
            for(int j =0; j<game.getMap().getLargeur();j++){
                casesPanel[i][j] = new JPanel();
                if((i+j)%2==0){
                    casesPanel[i][j].setBackground(new Color(233, 237, 201));
                }
                else {
                    casesPanel[i][j].setBackground(new Color(204, 213, 169));
                }
                this.plateauGame.add(casesPanel[i][j]);
            }
        }

        plateauSprite = new JPanel();
        plateauSprite.setBounds(0, sizeCase, sizeCase*largeur, sizeCase*(longeur));
        plateauSprite.setLayout(null);
        plateauSprite.setOpaque(false);


        this.getContentPane().add(inventairePane,0);
        this.getContentPane().add(plateauSprite,1);
        this.getContentPane().add(plateauGame,2);


        
        this.setImage();
        game.update();


    }

    public void setImage()  {
        for(Ennemis e : game.getEnnemis()){
            if(e.getImage() == null){
                try {
                    String currentDirectory = System.getProperty("user.dir");
                    File file = new File(currentDirectory + "/src/main/resources/" + e.getUrl()+"1.png");
                    Image bufferedImage = ImageIO.read(file);
                    ImageIcon imageIcon = new ImageIcon(bufferedImage);
                    ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance(3*sizeCase/4, 3*sizeCase/4, Image.SCALE_DEFAULT));
                    e.setImage(new JLabel(image));
                    e.getImage().setBounds((int)e.getPos().getX(), (int)e.getPos().getY()+sizeCase/6, 3*sizeCase/4, 3*sizeCase/4);
                    plateauSprite.add(e.getImage());
                    plateauSprite.repaint();
    
                    } 
    
                catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else {
                
                e.getImage().setBounds((int)e.getPos().getX(), (int)e.getPos().getY()+sizeCase/6, 3*sizeCase/4, 3*sizeCase/4);
                plateauSprite.repaint();
            }

                }

            }


}
