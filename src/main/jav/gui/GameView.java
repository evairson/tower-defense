package jav.gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import jav.Maps.RealCoordonnee;
import jav.Personnages.Perso;
import jav.Personnages.Ennemis.Ennemis;
import jav.Personnages.Tours.Mario;
import jav.Personnages.Tours.Tours;
import jav.Personnages.Tours.Tuyau;
import jav.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameView extends JFrame {
    public GameController control;
    private App app;

    private JPanel inventairePane;
    private JPanel plateauGame;
    private JPanel plateauSprite;
    private Game game;
    private int sizeCase;
    private JPanel[][] casesPanel;
    private HashMap<String, JButton> buttonTours;
    private HashMap<String, JLabel> imageTours;
    private JPanel panelTour;
    private JLabel argent;
    private JButton acheter;
    private boolean achat;

    // | Setter Getter : | ----------------------------------------

    public JPanel getPanelTour(){
        return panelTour;
    }

    public GameController getControl(){
        return control;
    }

    public JPanel getInventairePane(){
        return inventairePane;
    }

    public JLabel getArgent(){
        return argent;
    }

    public boolean IsmodeAchat(){
        return achat;
    }

    public void setModeAchat(boolean b){
        achat = b;
    }

    public JButton getAcheter(){
        return acheter;
    }

    public JLabel getImageTours(String s){
        return imageTours.get(s);
    }

    public JButton getButton(String s){
        return buttonTours.get(s);
    }

    public void setGame(Game g){
        this.game = g;
    }

    public Game getGame(){
        return game;
    }

    // -----------------------------------------------------

    public GameView(int longeur, int largeur, int ennemis, App app){  
        this.app = app;

        buttonTours = new HashMap<>();
        imageTours = new HashMap<>();

        game = new Game(longeur, largeur, ennemis, this);
        this.control = new GameController(this, app);  


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        sizeCase = (int)Math.min(width/largeur, height/(longeur+1));
        Game.sizecase = sizeCase;

        Mario mario = new Mario(new RealCoordonnee(1, 3));
        game.addToursEnJeu(mario);
        Mario mario2 = new Mario(new RealCoordonnee(1, 2));
        game.addToursEnJeu(mario2);


        this.setTitle("Tower Defense");
        this.setSize(sizeCase*largeur, sizeCase*(longeur+1));
        this.setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        panelTour = new JPanel();
        panelTour.setLayout(null);
        panelTour.setOpaque(false);
        panelTour.setBounds(0, 0, sizeCase*largeur, sizeCase*(longeur+1));


        try {
            createImageTourPanel();
        }catch (IOException exception) {
            exception.printStackTrace();
        }

        inventairePane = createInventairePane(largeur);

        plateauGame = new JPanel();
        plateauGame.setLayout(new GridLayout(game.getMap().getLongeur(), game.getMap().getLargeur()));
        plateauGame.setBounds(0, sizeCase, sizeCase*largeur, sizeCase*longeur);


        casesPanel = new ImagePane[game.getMap().getLongeur()][game.getMap().getLargeur()];

        for(int i =0;i<game.getMap().getLongeur();i++){
            for(int j =0; j<game.getMap().getLargeur();j++){
                if(i==game.getMap().getLongeur()-1){
                    casesPanel[i][j] = new ImagePane(game, "case-bas.png");
                }
                else if(i==0){
                    casesPanel[i][j] = new ImagePane(game, "case-haut.png");
                }
                else {
                    casesPanel[i][j] = new ImagePane(game, "case-milieu.png");
                }
                this.plateauGame.add(casesPanel[i][j]);
            }
        }

        plateauSprite = new JPanel();
        plateauSprite.setBounds(0, 0, sizeCase*largeur, sizeCase*(longeur+1));
        plateauSprite.setLayout(null);
        plateauSprite.setOpaque(false);

        this.getContentPane().add(panelTour, 0);
        this.getContentPane().add(plateauSprite,1);
        this.getContentPane().add(plateauGame,2);
        this.getContentPane().add(inventairePane,3);


        
        this.setImage();
        game.update();
    }

    public JPanel createInventairePane(int largeur){
        inventairePane = new JPanel();
        inventairePane.setLayout(null);
        inventairePane.setBounds(0, 0, sizeCase*largeur, sizeCase);
        inventairePane.setBackground(new Color(148, 235, 251));

        try{
            int i = 0;
            for(String tour : Tours.listTour ){
            JButton tourButton = createButtonInventaireTours(tour, i);
            buttonTours.put(tour, tourButton);
            inventairePane.add(tourButton);
            control.updateButtonInventaireTours(tour);
            inventairePane.repaint();
            i++;
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        for(String tour : Tours.listTour){
            getButton(tour).addActionListener(utiliserTour(tour));
        }


        

        argent = new JLabel("Argent : " + String.valueOf(game.getJoueur().getMonnaie()), SwingConstants.CENTER);
        argent.setBackground(new Color(121,203,219));
        argent.setFont(new Font("Serif", Font.BOLD, 20));
        argent.setForeground(Color.white);
        argent.setBounds((largeur-2) * sizeCase, sizeCase/3, sizeCase , sizeCase/4);
        inventairePane.add(argent);
        argent.setOpaque(true);

        acheter = new JButton("Acheter");
        acheter.setBackground(new Color(121,203,219));
        acheter.setFont(new Font("Serif", Font.BOLD, 20));
        acheter.setBounds((largeur-4) * sizeCase, sizeCase/3, sizeCase , sizeCase/4);
        inventairePane.add(acheter);

        acheter.addActionListener(modeAchat());
        
        return inventairePane;
    }

    public void createImageTourPanel() throws IOException{
        for(String tour : Tours.listTour){
                File file = new File(App.currentDirectory + "/src/main/resources/tours/"+tour+"/"+tour+"1.png");
                Image bufferedImage = ImageIO.read(file);
                ImageIcon imageIcon = new ImageIcon(bufferedImage);
                int width = imageIcon.getIconWidth();
                int height = imageIcon.getIconHeight();
                ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(sizeCase))/height)), (int)(sizeCase), Image.SCALE_DEFAULT));
                JLabel lab = new JLabel(image);
                lab.setBounds(0, 0,(int)(((width*(sizeCase))/height)), (int)(sizeCase));
                lab.setVisible(false);
                panelTour.add(lab);
                imageTours.put(tour, lab);
        }

    }

    public JButton createButtonInventaireTours(String tour, int x) throws IOException {
        File file = new File(App.currentDirectory + "/src/main/resources/tours/"+tour+"/"+tour+"1.png");
        Image bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(sizeCase))/height)*0.5), (int)(sizeCase*0.5), Image.SCALE_DEFAULT));
        JButton tourButton = new JButton(String.valueOf(game.getJoueur().getInventaire().get(tour)),image);
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
                    try {
                        ImageIcon image = new ImageIcon(imageIcon.getImage().getScaledInstance((int)(((width*(sizeCase))/height)*p.getScale()), (int)(sizeCase*p.getScale()), Image.SCALE_DEFAULT));
                        // Attention l'image doit être plus grande en hauteur qu'en largeur !! 
                        p.setImage(new JLabel(image));
                        p.getImage().setBounds((int)p.getPos().getX(), (int)(p.getPos().getY()+sizeCase) -image.getIconHeight(), (int)(sizeCase*p.getScale()), (int)(sizeCase*p.getScale()));
                        plateauSprite.add(p.getImage());
                        plateauSprite.repaint();
         
                    } catch (IllegalArgumentException e){
                        System.out.println("attention (width*(sizeCase))/height)*p.getScale() ou sizeCase*p.getScale() donne une valeur inférieure à 1");
                    }
                    
                    } 
    
                catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else {
                
                p.getImage().setBounds((int)p.getPos().getX(), (int)(p.getPos().getY()+12*sizeCase/7) -p.getImage().getIcon().getIconHeight(), (int)(sizeCase*p.getScale())+1, (int)(sizeCase*p.getScale())+1);
                plateauSprite.repaint();
            }

    }
    }

    public void mouseMoved(String s){
        MouseMotionAdapter mouse = new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                control.suivreMouse(e, s);
            }
        };
        addMouseMotionListener(mouse);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                removeMouseMotionListener(mouse);
                control.ajouterTour(s);
                removeMouseListener(this);
            }
        });
    }

    public ActionListenerTour acheterTour(String s)  {
        ActionListenerTour achat = new ActionListenerTour() {
            @Override
            public boolean isAchat() {
                return true;
            }
            public void actionPerformed(ActionEvent e) {
                control.addTourInventaire(s);
            }
        };
        return achat;
    }

    public ActionListenerTour utiliserTour(String s)  {
        
        ActionListenerTour utiliser = new ActionListenerTour() {

            public void actionPerformed(ActionEvent e) {
                if(game.getJoueur().getInventaire().get(s)!=0){
                    control.Selectionnercase(s);
                    mouseMoved(s);
                }
            }
        };
        return utiliser;
    }

    public ActionListener modeAchat(){
        ActionListenerTour achat = new ActionListenerTour() {
            public void actionPerformed(ActionEvent e) {
                for(String tour : Tours.listTour){
                control.changeButton(tour);
                }
                control.changeInterfaceButton();
            }
        };
        return achat;
    }


}
