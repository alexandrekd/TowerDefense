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
    public Missile creerMissile(Attaquant cible) {
        return new Missile(this, cible, env,new Debut());
    }

}
