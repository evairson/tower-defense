package main.java;


public class Joueur {
    private int monnaie;

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
    }
}