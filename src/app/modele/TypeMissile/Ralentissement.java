
// Projectile dont la zone ralentit les ennemis

package app.modele.TypeMissile;

import app.modele.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;

public class Ralentissement implements EffetsZone {
    private double ralentissement;
    private Environnement env;
    private Zone zone;
    private DoubleProperty vie;
    private double temps;
    private String id;

    public Ralentissement(double ralentissement,Environnement env,double temps){
        this.ralentissement=ralentissement;
        this.env = env;
        this.zone = null;
        this.temps = temps;
        this.vie = new SimpleDoubleProperty(1);
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
    }

    @Override
    public void agit() {
        if (zone != null){
            zone.agit();
        }
        gererVie();
    }

    @Override
    public void Entrer(Attaquant acteur) {
        acteur.setVitesse((int) (acteur.getConsVitesse() * ralentissement));
    }

    @Override
    public void Sortir(Attaquant acteur){
        acteur.setVitesse(acteur.getConsVitesse());
    }

    @Override
    public void Explosion(Missile missile) {
        this.env.getEffects().add(this);
        this.zone = new Zone(50,"GREEN",missile.getX(),missile.getY(),env,id);
        env.getZone().add(this.zone);
        ListChangeListener<Acteur> liste= c->{
            while (c.next()) {
                for(Acteur acteur : c.getAddedSubList()) {
                    Entrer((Attaquant) acteur);
                }

                for(Acteur acteur : c.getRemoved()) {
                    Sortir((Attaquant) acteur);
                }
            }
        };
        this.zone.getActeursDansLaZone().addListener(liste);
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void gererVie(){
        this.setVie(this.getVie() - (1/this.temps));
    }

    public boolean estVivant() {
        if (this.getVie() <= 0) {
            while (this.zone.getActeursDansLaZone().size() != 0){
                this.zone.getActeursDansLaZone().remove(0);
            }
            env.getZone().remove(this.zone);
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
