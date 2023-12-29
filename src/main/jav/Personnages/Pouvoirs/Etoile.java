package jav.Personnages.Pouvoirs;

import jav.Personnages.Tours.Tours;

public interface Etoile extends Pouvoirs {

   
   public void toStar();
    
   default void toStar(Tours t){
       t.addDegat(10);  
       t.lessTimeBetweenDegat(1000);
   }
}
