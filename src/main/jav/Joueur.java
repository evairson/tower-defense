package jav;
import java.util.HashMap;



public class Joueur {
    private int monnaie;
    private HashMap<String,Integer> inventaire;
    private HashMap<String,Integer> boutique;

    public int getMonnaie() {
        return monnaie;
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
        inventaire.put("tank",0);
        inventaire.put("tuyau",0);
        inventaire.put("fleur",0);
        inventaire.put("etoile",0);

        boutique = new HashMap<>();
        boutique.put("mario",20);
        boutique.put("luigi",20);
        boutique.put("peach",20);
        boutique.put("tank",30);
        boutique.put("tuyau",30);
        boutique.put("fleur",25);
        boutique.put("etoile",50);

    }
    public void addTours(String tours){
        inventaire.put(tours,inventaire.get(tours)+1);
    }

    public void removeTours(int n,String tours){
        if (n >= inventaire.get(tours)){
            inventaire.put(tours,0);
        }else{
            inventaire.put(tours,inventaire.get(tours)-n);
        }
    }

    public void acheter(String tours) {
        addTours(tours);
        monnaie -= boutique.get(tours);
    }

    public HashMap<String,Integer> getBoutique(){
        return this.boutique;
    }
    
    public void afficheInventaire(){
        System.out.println();
        System.out.println("| Inventaire |");
        for (String key : inventaire.keySet()){
            System.out.print(key+": "+inventaire.get(key)+", ");
        }
        System.out.println();
    }

    public void afficheBoutique(){
        System.out.println();
        System.out.println("Vous disposez de "+this.monnaie +" pièces d'or en banque, que souhaitez vous acheter ?");
        for (String key : boutique.keySet()){
            System.out.print(key+": "+boutique.get(key)+", ");
        }
        System.out.println();
    }
    public int getNbTours(String t){
        return inventaire.get(t);
    }
    
}