package app.modele;

import java.util.ArrayList;

public class Environnement {
    private int width, height;
    private ArrayList<Tourelle> tourelles;
    private ArrayList<Attaquants> attaquants;
    private ArrayList<Acteur> acteurs;
    private int nbTours;

    public Environnement(int width, int height){

        tourelles = new ArrayList<Tourelle>();
        attaquants = new ArrayList<Attaquants>();
        acteurs = new ArrayList<Acteur>();
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

    public ArrayList<Acteur> getActeurs(){
        return this.acteurs;
    }

    public Acteur getActeur(String id) {
        for(Acteur a:this.acteurs){
            if(a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }


    public void unTour(){
        System.out.println("tour " + this.nbTours);

        for(int i = 0; i < this.tourelles.size(); i++){
            Tourelle tourelle = this.tourelles.get(i);
            tourelle.agit();
        }

        for(int i = 0; i < this.attaquants.size(); i++){
            Attaquants attaquant = this.attaquants.get(i);
            attaquant.seDeplacer();
        }

        for(int i=attaquants.size()-1; i>=0;i--){
            Attaquants a = this.attaquants.get(i);
            if(!a.estVivant()){
                System.out.println(a.getId() + " est mort");
                /*removeSprite(this.attaquants.get(i).getId());*/
                this.attaquants.remove(i);
            }
        }
        this.nbTours++;
    }
}
