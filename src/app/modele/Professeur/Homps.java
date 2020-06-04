package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Debut;

public class Homps extends Tourelle {
    public Homps(int x, int y, Environnement env) {
        super(3, x, y, 10, 150, env,1,"Homps", 5);
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env,new Debut()));
            setDernierTire(0);
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
