package app.modele;

import java.util.ArrayList;

public abstract class Tourelle extends Acteur{
    /*private String id;
    public static int compteur = 0;
    private int x;
    private int y;
    private Environnement env;*/

    private int degat;
    private int rechargement;
    private int portee;
    private int typeMissile;
    private int dernierTire;
    private int skin;


    public Tourelle(int degat, int x, int y, int rechargement, int portee, Environnement env,int typeMissile,int couleur) {
        super(x, y, env,couleur);
        this.degat = degat;
        this.rechargement = rechargement;
        this.portee = portee;
        dernierTire = rechargement;
        this.typeMissile = typeMissile;
    }

    public void setRechargement(int rechargement) {
        this.rechargement = rechargement;
    }

    public int getTypeMissile() {
        return typeMissile;
    }

    //Cette méthode récupère une cible a la portée de la tour
    public Attaquant getCible() {
        Attaquant cible = null;

        for (int i = 0; i < this.env.getActeurs().size();i++)
            if (this.env.getActeurs().get(i) instanceof Attaquant)
                if(env.getActeurs().get(i).getX() <= this.getX()+portee && env.getActeurs().get(i).getX() >= this.getX()-portee && env.getActeurs().get(i).getY() <= this.getY()+portee && env.getActeurs().get(i).getY() >= this.getY()-portee){
                    cible = (Attaquant) env.getActeurs().get(i);
                }
        return cible;
    }

    public ArrayList<Attaquant> getListeCible(){
        ArrayList<Attaquant> cibles = new ArrayList<>();

        for (int i = 0; i < this.env.getActeurs().size();i++)
            if (this.env.getActeurs().get(i) instanceof Attaquant)
                if(env.getActeurs().get(i).getX() <= this.getX()+portee && env.getActeurs().get(i).getX() >= this.getX()-portee && env.getActeurs().get(i).getY() <= this.getY()+portee && env.getActeurs().get(i).getY() >= this.getY()-portee){
                    cibles.add((Attaquant) env.getActeurs().get(i));
                }
        return cibles;
    }

    public int getDegat(){
        return this.degat;
    }

    public int getRechargement() {
        return rechargement;
    }

    public void setDernierTire(int dernierTire) {
        this.dernierTire = dernierTire;
    }

    public int getDernierTire() {
        return dernierTire;
    }

}
