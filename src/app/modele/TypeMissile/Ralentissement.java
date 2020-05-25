package app.modele.TypeMissile;

import app.modele.*;

public class Ralentissement implements Effets {
    private double ralentissement;
    private Environnement env;
    private Zone zone;

    public Ralentissement(double ralentissement,Environnement env){
        this.ralentissement=ralentissement;
        this.env = env;
        env.getEffects().add(this);
        this.zone = null;
    }

    @Override
    public void agit() {
        if (zone != null){
            zone.agit();
        }
    }

    @Override
    public void Entrer(Attaquant acteur) {
        double speed = acteur.getConsVitesse() * ralentissement;
        System.out.println((int) speed);
        acteur.setVitesse((int) speed);
    }

    @Override
    public void Sortir(Attaquant acteur){
        acteur.setVitesse(acteur.getConsVitesse());
    }

    @Override
    public void Explosion(int x,int y) {
        this.zone = new Zone(50,"GREEN",20,x,y,this,env);
        env.getZone().add(this.zone);
    }

}
