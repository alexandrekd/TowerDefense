package app.modele.TypeMissile;

import app.modele.Attaquant;
import app.modele.Effets;
import app.modele.Environnement;
import app.modele.Zone;

import java.util.ArrayList;

public class Poison implements Effets {
    private int degat;
    private Environnement env;
    private Zone zone;

    public Poison(int degat,Environnement env){
        this.degat = degat;
        this.env = env;
        this.zone = null;
        this.env.getEffects().add(this);
    }
    @Override
    public void agit() {
        if (zone != null) {
            zone.agit();
            if (env.getNbTours() % 5 == 0) {
                ArrayList<Attaquant> liste = zone.getListeAttanquants();
                for (int i = 0; i < liste.size(); i++) {
                    Attaquant z = liste.get(i);
                    z.recevoirTir(degat);
                }
            }
        }
    }

    @Override
    public void Entrer(Attaquant acteur) {

    }

    @Override
    public void Sortir(Attaquant acteur) {

    }

    @Override
    public void Explosion(int x, int y) {
        this.zone = new Zone(50,"ORANGE",20,x,y,this,env);
        env.getZone().add(this.zone);
    }
}
