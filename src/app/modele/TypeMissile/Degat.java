package app.modele.TypeMissile;

import app.modele.Acteur;
import app.modele.Attaquant;
import app.modele.Effets;
import app.modele.Missile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Degat implements Effets {

    private DoubleProperty vie = new SimpleDoubleProperty(1);
    private String id;

    public Degat(){
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
    }

    public final double getVie(){ return this.vie.get(); }

    public final void setVie(double newVie){  this.vie.set(newVie); }

    @Override
    public void Explosion(Missile missile) {
        missile.getFinActeur().recevoirTir(missile.getDebActeur().getDegat());
        setVie(0);
    }

    @Override
    public String getId() {
        return this.id;
    }
}
