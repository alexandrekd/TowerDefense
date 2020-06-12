
// Comparot est une tourelle de zone, ses projectiles creent des zones infligeant des degats aux ennemis se trouvant dedans

package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Poison;

public class Comparot extends Tourelle {
    public Comparot(int x, int y, Environnement env) {
        super(0, x, y, 20, 150, env,"Comparot", 5);
    }

    @Override
    public Missile creerMissile(Attaquant cible){
        return new Missile(this, cible, env,new Poison(2,env,20));
    }
}
