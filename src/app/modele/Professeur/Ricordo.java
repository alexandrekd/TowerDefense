
// Ricordo est une tourelle de zone, ses projectiles creent des zones ralentissent les ennemis se trouvant dedans

package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Ralentissement;

public class Ricordo extends Tourelle {
    public Ricordo(int x, int y, Environnement env) {
        super(0, x, y, 20, 150, env,1,"Ricordo", 5);
    }

    @Override
    public Missile creerMissile(Attaquant cible) {
        return new Missile(this, cible, env,new Ralentissement(0.5,env,20));
    }

}
