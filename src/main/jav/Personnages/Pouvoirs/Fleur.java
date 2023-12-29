package jav.Personnages.Pouvoirs;

import jav.Game;
import jav.Personnages.Tours.Tours;

public interface Fleur extends Pouvoirs{

    public void toFlower();
    
    default void toFlower(Tours t){
        t.addDegat(20);  
    }
}
