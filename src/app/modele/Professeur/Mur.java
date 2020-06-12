package app.modele.Professeur;

import app.modele.Acteur;
import app.modele.Attaquant;
import app.modele.Environnement;

import java.util.List;
import java.util.stream.Collectors;

public class Mur extends Acteur {
    private boolean attraper;
    private Attaquant cible;
    private int portee;
    private int vie = 100;
    private int x,y;

    public Mur(int x, int y, Environnement env, String name,int portee) {
        super(x, y, env, name);
        this.x = x;
        this.y = y;
        attraper = false;
        this.portee = portee;
        this.cible = null;
    }

    @Override
    public void agit() {
        if (!attraper){
            this.vie -= 5;

            for (int i = 0; i < this.env.getActeurs().size();i++)
                if (this.env.getActeurs().get(i) instanceof Attaquant)
                    if(env.getActeurs().get(i).getX() <= this.getX()+portee && env.getActeurs().get(i).getX() >= this.getX()-portee && env.getActeurs().get(i).getY() <= this.getY()+portee && env.getActeurs().get(i).getY() >= this.getY()-portee)
                        if (!dejaChezQuelquun((Attaquant) this.env.getActeurs().get(i))){
                            this.cible = (Attaquant) env.getActeurs().get(i);
                            attraper = true;
                          }

            if(this.cible != null){
                this.cible.setX(this.x);
                this.cible.setY(this.y);
            }

        }
        else{
            if(this.cible != null){
                this.cible.setX(this.x);
                this.cible.setY(this.y);
            }

            if (env.getNbTours()%5 == 0)
            this.vie -= this.cible.getPv();
        }
        this.vie-= 2;
    }

    public boolean dejaChezQuelquun(Attaquant ci){
        List<Mur> mur =  env.getActeurs().parallelStream().filter(n -> n instanceof Mur).map(n ->(Mur) n).collect(Collectors.toList());
        for (int i = 0;i < mur.size();i++)
            if(mur.get(i).getCible() == ci)
                return true;

        return false;
    }

    public boolean estVivant(){
        if (this.vie <= 0) {
            return false;
        }
        return true;
    }

    public Attaquant getCible() {
        return this.cible;
    }
}
