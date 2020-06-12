
// Rety est une tourelle dont la vitesse de rechargement alterne entre rapide et normal

package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Degat;

public class Rety extends Tourelle {
    int boost = 10;
    int vers = 0;

    public Rety(int x, int y, Environnement env) {
        super(5, x, y, 2, 150, env,"Rety", 5);
    }

    @Override
    public void agit() {
        if(getRechargement() == getDernierTire()) {

            // Mode rapide
            if (this.vers == 0){
                Attaquant cible = getCible();
                if (cible != null)
                    env.addProject(creerMissile(cible));
                setDernierTire(0);

                if (this.boost == -10) {
                    this.vers++;
                    setRechargement(20);
                }
                this.boost--;
            }

            // Mode lent
            else{
                Attaquant cible = getCible();
                if (cible != null)
                    env.addProject(creerMissile(cible));
                setDernierTire(0);

                if (this.boost == -6) {
                    this.boost = 10;
                    this.vers--;
                    setRechargement(2);
                }
                this.boost++;
            }


        }
        else
            setDernierTire(getDernierTire()+1);
    }

    @Override
    public Missile creerMissile(Attaquant cible) {
        return new Missile(this, cible, env,new Degat());
    }


}
