package jav;

import javax.imageio.ImageIO;
import javax.swing.*;

import jav.Maps.RealCoordonnee;
import jav.Personnages.Perso;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Tours.Mario;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameView extends JFrame {
    private GameController control;
    private App app;
    private JPanel inventairePane;
    private JPanel plateauGame;
    private JPanel plateauSprite;
    private Game game;
    private int sizeCase;
    private JPanel[][] casesPanel;
    private HashMap<String, JButton> buttonTours;

    public void setGame(Game g){
        this.game = g;
    }

    GameView(int longeur, int largeur, int ennemis){    

        buttonTours = new HashMap<>();

        game = new Game(longeur, largeur, ennemis, this);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        sizeCase = (int)Math.min(width/largeur, height/(longeur+1));
        Game.sizecase = sizeCase;

        Mario mario = new Mario(new RealCoordonnee(1, 3));
        game.addToursEnJeu(mario);
        Mario mario2 = new Mario(new RealCoordonnee(1, 2));
        game.addToursEnJeu(mario2);
        game.getMap().updateContenu(game);

        this.setTitle("Tower Defense");
        this.setSize(sizeCase*largeur, sizeCase*(longeur+1));
        this.setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);


        inventairePane = createInventairePane(largeur);

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

    public JPanel createInventairePane(int largeur){
        inventairePane = new JPanel();
        inventairePane.setLayout(null);
        inventairePane.setBounds(0, 0, sizeCase*largeur, sizeCase);
        inventairePane.setBackground(new Color(250, 237, 205));
        
        try{
            JButton mario = createButtonInventaireTours("mario", 0);
            buttonTours.put("mario", mario);
            inventairePane.add(mario);
            JButton luigi = createButtonInventaireTours("luigi", 1);
            buttonTours.put("luigi", luigi);
            inventairePane.add(luigi);
            JButton peach = createButtonInventaireTours("peach", 2);
            buttonTours.put("peach", peach);
            inventairePane.add(peach);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }


        return inventairePane;
    }

    public JButton createButtonInventaireTours(String tour, int x) throws IOException {
        File file = new File(App.currentDirectory + "/src/main/resources/tours/"+tour+"/"+tour+"1.png");
        Image bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(sizeCase))/height)*0.5), (int)(sizeCase*0.5), Image.SCALE_DEFAULT));
        JButton tourButton = new JButton("1",image);
        tourButton.setVerticalTextPosition(SwingConstants.TOP);
        
        tourButton.setHorizontalTextPosition(SwingConstants.CENTER);
        tourButton.setBounds(x*sizeCase + sizeCase/8, sizeCase/8, 3*sizeCase/4, 3*sizeCase/4);  // sizeCase/8 * 2 + 3*sizeCase/4 = sizeCase

        return tourButton;
    }



    public void setImage()  {
            setImagePerso(game.getEnnemis());
            setImagePerso(game.getToursEnJeu());
        }



    public <T extends Perso> void setImagePerso(ArrayList<T> l){
        for(Perso p : l){
            if(p.getImage() == null){
                try {
                    File file = new File(App.currentDirectory + "/src/main/resources/" + p.getUrl()+"1.png");
                    Image bufferedImage = ImageIO.read(file);
                    ImageIcon imageIcon = new ImageIcon(bufferedImage);
                    int width = imageIcon.getIconWidth();
                    int height = imageIcon.getIconHeight();
                    ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(sizeCase))/height)*p.getScale()), (int)(sizeCase*p.getScale()), Image.SCALE_DEFAULT));
                    p.setImage(new JLabel(image));
                    p.getImage().setBounds((int)p.getPos().getX(), (int)p.getPos().getY(), (int)(sizeCase*p.getScale())+1, (int)(sizeCase*p.getScale())+1);
                    plateauSprite.add(p.getImage());
                    plateauSprite.repaint();
    
                    } 
    
                catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else {
                
                p.getImage().setBounds((int)p.getPos().getX(), (int)p.getPos().getY(), (int)(sizeCase*p.getScale())+1, (int)(sizeCase*p.getScale())+1);
                plateauSprite.repaint();
            }

    }
    }


}
