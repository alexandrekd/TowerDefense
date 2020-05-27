package app.modele.Professeur;

import app.modele.Acteur;
import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Zone;

import java.util.ArrayList;
import java.util.Collections;
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
    }

    @Override
    public void agit() {
        if (attraper == false){
            this.cible = null;

            for (int i = 0; i < this.env.getActeurs().size();i++)
                if (this.env.getActeurs().get(i) instanceof Attaquant)
                    if(env.getActeurs().get(i).getX() <= this.getX()+portee && env.getActeurs().get(i).getX() >= this.getX()-portee && env.getActeurs().get(i).getY() <= this.getY()+portee && env.getActeurs().get(i).getY() >= this.getY()-portee){
                        if (!dejaChezQuelquun(this.cible))
                        cible = (Attaquant) env.getActeurs().get(i);
                    }

            if(this.cible != null){
                cible.setX(this.x);
                attraper = true;
                cible.setY(this.y);
            }

        }
        else{
            if (env.getNbTours()%5 == 0)
            this.vie = this.vie - cible.getPv();
        }
    }

    public boolean dejaChezQuelquun(Attaquant ci){
        List<Mur> mur = Collections.singletonList((Mur) env.getActeurs().parallelStream().filter(n -> n instanceof Mur).collect(Collectors.toList()));
        Boolean result = false;
        for (int i = 0;i < mur.size();i++){
            if(mur.get(i).getCible() == ci)
                result = true;
        }
        return result;
    }

    public boolean estVivant(){
        if (this.vie <= 0) {
            return false;
        }
        return true;
    }

    public Attaquant getCible() {
        return cible;
    }
}
