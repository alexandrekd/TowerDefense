package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;

public class Lamolle extends Tourelle {
    public Lamolle(int x, int y, Environnement env) {
        super(15, x, y, 100, 250, env,1,1);
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
}
