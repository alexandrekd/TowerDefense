package app.modele.TypeMissile;

import app.modele.Attaquant;
import app.modele.Effets;
import app.modele.Missile;
import app.modele.Professeur.Homps;

import static app.modele.Utile.goToCaseDepart;

public class Debut implements Effets {
    @Override
    public void agit() {

    }

    @Override
    public void Entrer(Attaquant acteur) {

    }

    @Override
    public void Sortir(Attaquant acteur) {

    }

    @Override
    public void Explosion(Missile missile) {
        missile.getFinActeur().recevoirTir(missile.getDebActeur().getDegat());
            if(Math.random()*10 <= 2)
                goToCaseDepart(missile);
    }
}
