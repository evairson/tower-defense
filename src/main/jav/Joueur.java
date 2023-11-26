package jav;
import java.util.HashMap;

import jav.Personnages.Tours.Tours;


public class Joueur {
    private int monnaie;
    private HashMap<Tours,Integer> inventaire;

    public int getMonnaie() {
        return monnaie;
    }

    public void acheter(int n) {
        monnaie -= n;
    }

    public void gagner(int n) {
        monnaie += n;
    }


    public Joueur(){
        monnaie = 300;
        inventaire = new HashMap<>(8);
    }
    public void addTours(int n,Tours t){
        inventaire.put(t,inventaire.get(t)+n);
    }
    public void removeTours(int n,Tours t){
        if (n >= inventaire.get(t)){
            inventaire.put(t,0);
        }else{
            inventaire.put(t,inventaire.get(t)-n);
        }
    }
    
}