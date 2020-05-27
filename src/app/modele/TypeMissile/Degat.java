package app.modele.TypeMissile;

import app.modele.Attaquant;
import app.modele.Effets;
import app.modele.Missile;

public class Degat implements Effets {

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
    }
}
