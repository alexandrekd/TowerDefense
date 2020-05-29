package app.modele.Professeur;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Missile;
import app.modele.Tourelle;
import app.modele.TypeMissile.Default;
import app.modele.TypeMissile.Degat;

public class Rety extends Tourelle {

    int boost = 10;
    int vers = 0;
    public Rety(int x, int y, Environnement env) {
        super(5, x, y, 2, 150, env,3,"Rety");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            if (vers == 0){
                Attaquant cible = getCible();
                if (cible != null)
                    env.addProject(new Missile(this, cible, env,new Degat()));
                setDernierTire(0);
                if (boost == -10) {
                    vers++;
                    setRechargement(20);
                    /*boost = 0;*/
                }
                boost--;
            }
            else{
                Attaquant cible = getCible();
                if (cible != null)
                    env.addProject(new Missile(this, cible, env,new Degat()));
                setDernierTire(0);
                if (boost == -6) {
                    boost = 10;
                    vers--;
                    setRechargement(2);
                }
                boost++;
            }


        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
