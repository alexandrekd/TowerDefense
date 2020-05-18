package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;

public class Ricordo extends Tourelle {
    public Ricordo(int x, int y, Environnement env) {
        super(5, x, y, 2, 150, env,1,1);
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }

    @Override
    public Attaquant getCible() {
        Attaquant cible = null;

        for (int i = 0; i < this.env.getActeurs().size();i++)
            if (this.env.getActeurs().get(i) instanceof Attaquant && !((Attaquant) this.env.getActeurs().get(i)).estRalentit())
                if(env.getActeurs().get(i).getX() <= this.getX()+getPortee() && env.getActeurs().get(i).getX() >= this.getX()-getPortee() && env.getActeurs().get(i).getY() <= this.getY()+getPortee() && env.getActeurs().get(i).getY() >= this.getY()-getPortee()){
                    cible = (Attaquant) env.getActeurs().get(i);
                }
        return cible;
    }
}
