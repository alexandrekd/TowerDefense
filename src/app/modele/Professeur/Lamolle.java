
// Lamolle est une tourelle avec une longue portee

package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Degat;

public class Lamolle extends Tourelle {
    public Lamolle(int x, int y, Environnement env) {
        super(50, x, y, 30, 1000000, env,"Lamolle", 5);
    }


    @Override
    public Missile creerMissile(Attaquant cible) {
        return new Missile(this, cible, env,new Degat());
    }
}
