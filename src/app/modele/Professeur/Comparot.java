package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Default;
import app.modele.TypeMissile.Poison;

public class Comparot extends Tourelle {
    public Comparot(int x, int y, Environnement env) {
        super(0, x, y, 20, 150, env,1,"Comparot");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env,new Poison(2,env)));
            setDernierTire(0);
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
