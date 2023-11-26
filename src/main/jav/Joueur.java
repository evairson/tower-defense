package jav;
import java.util.HashMap;

import jav.Personnages.Luigi;
import jav.Personnages.Mario;
import jav.Personnages.Peach;
import jav.Personnages.Tours;
import jav.Personnages.TuyauTank;


public class Joueur {
    private int monnaie;
    private HashMap<String,Integer> inventaire;

    public int getMonnaie() {
        return monnaie;
    }

    public void acheter(int n) {
        monnaie -= n;
    }

    public void gagner(int n) {
        monnaie += n;
    }
    public HashMap<String,Integer> getInventaire(){
        return this.inventaire;
    }


    public Joueur(){
        monnaie = 60;
        inventaire = new HashMap<>();
        inventaire.put("mario",1);
        inventaire.put("luigi",1);
        inventaire.put("peach",1);
        inventaire.put("tuyauTank",0);

    }
    public void addTours(int n,String tours){
        inventaire.put(tours,inventaire.get(tours)+n);
    }
    public void removeTours(int n,String tours){
        if (n >= inventaire.get(tours)){
            inventaire.put(tours,0);
        }else{
            inventaire.put(tours,inventaire.get(tours)-n);
        }
    }
    public void afficheInventaire(){
        for (String key : inventaire.keySet()){
            System.out.println(key+" ,"+inventaire.get(key));
        }
    }
    public int getNbTours(String t){
        return inventaire.get(t);
    }
    
}