package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Default;
import app.modele.TypeMissile.Degat;

public class Bonnot extends Tourelle {
    private int degat = 5 ;
    public Bonnot(int x, int y, Environnement env) {
        super(5, x, y, 20, 150, env,1,"Bonnot"/*, 1*/);
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env,new Degat(cible, degat )));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}


