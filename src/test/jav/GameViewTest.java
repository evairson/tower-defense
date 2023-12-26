package test.jav;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import jav.App;
import jav.Game;
import jav.GameView;
import jav.Maps.RealCoordonnee;
import jav.Personnages.Tours.Luigi;
import jav.Personnages.Tours.Mario;
import jav.Personnages.Tours.Peach;
import jav.Personnages.Tours.Tuyau;

public class GameViewTest {
    
    @Test 
    public void URLTest() {

        assertEquals(
            App.currentDirectory + "/src/main/resources/" + (new Mario(new RealCoordonnee(0, 0))).getUrl()+"1.png",
            App.currentDirectory + "/src/main/resources/tours/mario/mario1.png" );
        assertEquals(
            App.currentDirectory + "/src/main/resources/" + (new Luigi(new RealCoordonnee(0, 0))).getUrl()+"1.png",
            App.currentDirectory + "/src/main/resources/tours/luigi/luigi1.png" );  
        assertEquals(
            App.currentDirectory + "/src/main/resources/" + (new Peach(new RealCoordonnee(0, 0))).getUrl()+"1.png",
            App.currentDirectory + "/src/main/resources/tours/peach/peach1.png" );  
        assertEquals(
            App.currentDirectory + "/src/main/resources/" + (new Tuyau(new RealCoordonnee(0, 0))).getUrl()+"1.png",
            App.currentDirectory + "/src/main/resources/tours/tuyau/tuyau1.png" );  
        
    }
}
