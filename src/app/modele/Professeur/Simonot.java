package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Default;

public class Simonot extends Tourelle {
    public Simonot(int x, int y, Environnement env) {
        super(5, x, y, 2, 20, env,1,"Simonot");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env,new Default()));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
