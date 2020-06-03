package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Degat;

public class Lamolle extends Tourelle {
    public Lamolle(int x, int y, Environnement env) {
        super(50, x, y, 30, 1000000, env,1,"Lamolle", 5);
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env,new Degat()));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
