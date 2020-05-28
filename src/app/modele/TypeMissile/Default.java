package app.modele.TypeMissile;

import app.modele.Acteur;
import app.modele.Attaquant;
import app.modele.Effets;
import app.modele.Missile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Default implements Effets {

    private DoubleProperty vie = new SimpleDoubleProperty(1);
    private String id;

    public Default(){
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
    }
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
        setVie(0);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean estVivant() {
        if (this.getVie() <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public final DoubleProperty vieProperty() {
        return vie;
    }

    public final double getVie(){ return this.vie.get(); }

    public final void setVie(double newVie){  this.vie.set(newVie); }


}
