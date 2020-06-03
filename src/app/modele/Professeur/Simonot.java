package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Invocation;

public class Simonot extends Tourelle {
    public Simonot(int x, int y, Environnement env) {
        super(0, x, y, 20, 300, env,1,"Simonot", 5);
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null){
                env.addProject(new Missile(this, cible, env,new Invocation(env,50)));
            }
            setDernierTire(0);
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
