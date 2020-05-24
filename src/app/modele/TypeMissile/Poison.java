package app.modele.TypeMissile;

import app.modele.Attaquant;
import app.modele.Effets;
import app.modele.Environnement;
import app.modele.Zone;

public class Poison implements Effets {
    private int degat;
    private Environnement env;
    private Zone zone;

    public Poison(int degat,Environnement env){
        this.degat = degat;
        this.env = env;
    }
    @Override
    public void agit() {
        for (int i = 0; i < zone.getActeursDansLaZone().size();i++){
            Attaquant z = (Attaquant) zone.getActeursDansLaZone().get(i);
            z.recevoirTir(degat);
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
        this.zone = new Zone(50,"GREEN",20,x,y,this,env);
        env.getZone().add(zone);
    }
}
