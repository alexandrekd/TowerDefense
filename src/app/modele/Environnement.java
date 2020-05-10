package modele;

import java.util.ArrayList;

public class Environnement {
    private int width, height;
    private ArrayList<Tourelle> tourelles;
    private ArrayList<Attaquants> attaquants;
    private int nbTours;

    public Environnement(int width, int height){

        tourelles = new ArrayList<Tourelle>();
        attaquants = new ArrayList<Attaquants>();
        this.width = width;
        this.height = height;

    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }


    public void ajouterTourelle(Tourelle tourelle){
        this.tourelles.add(tourelle);
        System.out.println("c'est bon bg\nx: " + tourelle.getX() + "; y: " + tourelle.getY() + "\n");
    }

    public void ajouterAttaquants(Attaquants attaquant){
        this.attaquants.add(attaquant);
    }


    public ArrayList<Tourelle> getTourelles(){
        return this.tourelles;
    }

    public ArrayList<Attaquants> getAttaquants(){
        return this.attaquants;
    }


    public void unTour(){
        System.out.println("tour " + this.nbTours);

        for(int i=0;i<tourelles.size(); i++){
            Tourelle tourelle = tourelles.get(i);
            tourelle.agit();
        }
        for(int i=0;i<attaquants.size(); i++){
            Attaquants attaquant = attaquants.get(i);
            attaquant.seDeplacer();
        }

        for(int i=attaquants.size()-1; i>=0;i--){
            Attaquants a = attaquants.get(i);
            if(!a.estVivant()){
                System.out.println("mort de : " + a);
                attaquants.remove(i);
            }
        }
        this.nbTours++;
    }
}
