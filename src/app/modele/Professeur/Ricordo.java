package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Ralentissement;

public class Ricordo extends Tourelle {
    public Ricordo(int x, int y, Environnement env) {
        super(0, x, y, 20, 150, env,1,"Ricordo");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env,new Ralentissement(0.5,env,20)));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }

    @Override
    public Attaquant getCible() {
        Attaquant cible = null;

        for (int i = 0; i < this.env.getActeurs().size();i++)
            if (this.env.getActeurs().get(i) instanceof Attaquant) {
                if (env.getActeurs().get(i).getX() <= this.getX() + getPortee() && env.getActeurs().get(i).getX() >= this.getX() - getPortee() && env.getActeurs().get(i).getY() <= this.getY() + getPortee() && env.getActeurs().get(i).getY() >= this.getY() - getPortee()) {
                    cible = (Attaquant) env.getActeurs().get(i);
                }
            }
        return cible;
    }
}
