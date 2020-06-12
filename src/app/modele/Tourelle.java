package app.modele;

import app.modele.TypeMissile.Degat;

import java.util.ArrayList;

public abstract class Tourelle extends Acteur{

    private int degat;
    private int rechargement;
    private int portee;
    private int dernierTire;
    private Attaquant cible;
    private int prix;

    public Tourelle(int degat, int x, int y, int rechargement, int portee, Environnement env, String name, int prix) {
        super(x, y, env, name);
        this.degat = degat;
        this.rechargement = rechargement;
        this.portee = portee;
        this.dernierTire = rechargement;
        this.cible = null;
        this.prix = prix;
    }

    public int getPrix(){
        return this.prix;
    }

    public void setRechargement(int rechargement) {
        this.rechargement = rechargement;
    }

    public int getPortee(){
        return this.portee;
    }

    @Override
    public void agit(){
        tirer();
    }
    public void tirer(){
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(this.creerMissile(cible));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }

    public abstract Missile creerMissile(Attaquant cible);


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