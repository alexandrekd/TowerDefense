package app.modele;

import java.util.ArrayList;

public abstract class Tourelle extends Acteur{

    private int degat;
    private int rechargement;
    private int portee;
    private int typeMissile;
    private int dernierTire;
    private Attaquant cible;

    public Tourelle(int degat, int x, int y, int rechargement, int portee, Environnement env, int typeMissile, String name) {
        super(x, y, env, name);
        this.degat = degat;
        this.rechargement = rechargement;
        this.portee = portee;
        this.dernierTire = rechargement;
        this.typeMissile = typeMissile;
        this.cible = null;
    }

    public void setRechargement(int rechargement) {
        this.rechargement = rechargement;
    }

    public int getTypeMissile() {
        return typeMissile;
    }

    public int getPortee(){
        return this.portee;
    }

    // Cette méthode récupère une cible a la portée de la tour, à moins d'en avoir deja une
    public Attaquant getCible() {
        if (this.cible == null)
            this.cible = trouverCible();

        else if (!(this.cible.getX() <= this.getX() + portee && this.cible.getX() >= this.getX() - portee && this.cible.getY() <= this.getY() + portee && this.cible.getY() >= this.getY() - portee) || !this.cible.estVivant())
            this.cible = trouverCible();

        return cible;
    }

    public Attaquant trouverCible(){
        Attaquant cible = null;
        for (int i = 0; i < this.env.getActeurs().size(); i++)
            if (this.env.getActeurs().get(i) instanceof Attaquant)
                if (env.getActeurs().get(i).getX() <= this.getX() + portee && env.getActeurs().get(i).getX() >= this.getX() - portee && env.getActeurs().get(i).getY() <= this.getY() + portee && env.getActeurs().get(i).getY() >= this.getY() - portee)
                    return cible = (Attaquant) env.getActeurs().get(i);
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