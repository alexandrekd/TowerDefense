package app.modele.Professeur;

import app.modele.Acteur;
import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Zone;

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
    }

    @Override
    public void agit() {
        if (attraper == false){
            this.cible = null;

            for (int i = 0; i < this.env.getActeurs().size();i++)
                if (this.env.getActeurs().get(i) instanceof Attaquant)
                    if(env.getActeurs().get(i).getX() <= this.getX()+portee && env.getActeurs().get(i).getX() >= this.getX()-portee && env.getActeurs().get(i).getY() <= this.getY()+portee && env.getActeurs().get(i).getY() >= this.getY()-portee){
                        cible = (Attaquant) env.getActeurs().get(i);
                    }

            if(this.cible != null)
                attraper = true;
        }
        else{
            cible.setX(this.x);
            cible.setY(this.y);
            this.vie = this.vie - cible.getPv();
        }
    }

    public boolean estVivant(){
        if (this.vie <= 0) {
            return false;
        }
        return true;
    }


}
