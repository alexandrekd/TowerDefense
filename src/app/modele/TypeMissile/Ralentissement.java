package app.modele.TypeMissile;

import app.modele.*;

public class Ralentissement implements Effets {
    private double ralentissement;
    private Environnement env;

    public Ralentissement(double ralentissement,Environnement env){
        this.ralentissement=ralentissement;
        this.env = env;
    }

    @Override
    public void agit() {

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
        env.getZone().add(new Zone(50,"GREEN",20,x,y,this,env));
    }

}
