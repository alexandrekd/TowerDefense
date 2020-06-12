package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;
import app.modele.TypeMissile.Ralentissement;

import java.util.List;
import java.util.stream.Collectors;

public class Thomas extends Attaquant {
    Ralentissement ral;
    int portee;
    public Thomas(Environnement env, int x, int y) {
        super(env, 75, 11, x, y, "Thomas");
        portee = 30;
        ral = new Ralentissement(1.5,env,999999999);
        ral.ExplosionCo(x,y);
    }

    @Override
    public void agit() {

        this.setVitesse(11);
        BFS.BFS(this,env);
        ral.getZone().setX(this.getX());
        ral.getZone().setY(this.getY());

    }
}
