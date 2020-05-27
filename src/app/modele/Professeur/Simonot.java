package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Default;
import app.modele.TypeMissile.Invocation;

import java.util.stream.Collectors;

public class Simonot extends Tourelle {
    public Simonot(int x, int y, Environnement env) {
        super(0, x, y, 10, 300, env,1,"Simonot");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null){
                if (env.getActeurs().parallelStream().filter(n-> n instanceof Mur).collect(Collectors.toList()).size() == 0)
                env.addProject(new Missile(this, cible, env,new Invocation(env,50)));
            }
            setDernierTire(0);
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
