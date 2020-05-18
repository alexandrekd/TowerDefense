package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;

import java.util.ArrayList;

public class Bossard extends Tourelle {
    public Bossard(int x, int y, Environnement env) {
        super(5, x, y, 10, 150, env,2,1);
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            ArrayList<Attaquant> cibles = getListeCible();
            int aleatoire = (int) (Math.random()*cibles.size());
            if (aleatoire != 0)
                env.addProject(new Missile(this, cibles.get(aleatoire), env));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
