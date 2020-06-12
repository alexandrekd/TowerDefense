
// Ricordo est une tourelle qui envoie des murs sur les ennemis

package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Invocation;

public class Simonot extends Tourelle {
    public Simonot(int x, int y, Environnement env) {
        super(0, x, y, 20, 300, env,"Simonot", 5);
    }


    @Override
    public Missile creerMissile(Attaquant cible) {
        return new Missile(this, cible, env,new Invocation(env,50));
    }
}
