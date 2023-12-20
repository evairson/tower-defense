package test.jav;

import jav.Exception.choisirToursException;
import jav.Exception.choixActionException;
import jav.Game;
import jav.JeuTexte;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.fail;

public class JeuTexteTest {
    
    @Test
    public void choisirTourTest() throws choisirToursException {
        String[] tours = {"mario\n", "luigi\n", "peach\n", "tuyau\n", "tank\n"};
        for(String input : tours){
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());

            Scanner scanner = new Scanner(inputStream);
    
            try {
                JeuTexte.choisirTour(scanner);
            } catch (choisirToursException e) {
                fail("Cette tour peut être utilisé !");
            }
        }

    }

    @Test(expected = choisirToursException.class)
    public void choisirMauvaiseTourTest() throws choisirToursException {
        
        String input= "mari";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Scanner scanner = new Scanner(inputStream);
 
        JeuTexte.choisirTour(scanner);

    }

    @Test 
    public void choisirCorTest() throws choisirToursException, StringIndexOutOfBoundsException {
        String toursJouer = "mario";
        Game g = new Game(5, 20, 10, null);

        String input= "A3"; //Test avec un chiffre 
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Scanner scanner = new Scanner(inputStream); 
        try {
            JeuTexte.choisirCor(g, scanner, toursJouer);;
        } catch (choisirToursException e) {
            fail("Cette tour peut être placé !");
        }
        

        input= "C12"; //Test avec deux chiffres 
        inputStream = new ByteArrayInputStream(input.getBytes());

        scanner = new Scanner(inputStream); 
        try {
            JeuTexte.choisirCor(g, scanner, toursJouer);;
        } catch (choisirToursException e) {
            fail("Cette tour peut être placé !");
        }
    }

    @Test 
    public void choisirMauvaiseCorTest() throws choisirToursException, StringIndexOutOfBoundsException {
        String toursJouer = "mario";
        Game g = new Game(5, 20, 10, null);

        String[] actionImpossible = {"23","BD","A","M3","A21"};
        for(String input : actionImpossible){
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());

            Scanner scanner = new Scanner(inputStream); 

            try{
                JeuTexte.choisirCor(g, scanner, toursJouer);
                fail();
            }
            catch (choisirToursException e){
            }
            
        
        }
    }

    @Test
    public void updateUtilisateurTest() throws choixActionException {
        Game g = new Game(5, 20, 10, null);

        String[] actionImpossible = {"5","20","b","3b"};
        for(String input : actionImpossible){
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());

            Scanner scanner = new Scanner(inputStream); 

            try{
                JeuTexte.updateUtilisateur(g, scanner);
                fail();
            }
            catch (choixActionException e){
                
            }
            
        

        }
    }

    


}