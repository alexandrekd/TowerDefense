package app.modele.TypeMissile;

import app.modele.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;

import java.util.ArrayList;

public class Poison implements EffetsZone {
    private int degat;
    private Environnement env;
    private Zone zone;
    private double temps;
    private DoubleProperty vie;
    private String id;

    public Poison(int degat,Environnement env,double temps){
        this.degat = degat;
        this.env = env;
        this.zone = null;
        this.temps = temps;
        this.vie = new SimpleDoubleProperty(1);
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;


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
        gererVie();
    }

    public void gererVie(){
        this.setVie(this.getVie() - (1/this.temps));
    }

    @Override
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
    public void Entrer(Attaquant acteur) {

    }

    @Override
    public void Sortir(Attaquant acteur) {

    }

    @Override
    public void Explosion(Missile missile) {
        this.env.getEffects().add(this);
        this.zone = new Zone(50,"ORANGE",missile.getX(),missile.getY(),env,id);
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

    public final DoubleProperty vieProperty() {
        return vie;
    }

    public final double getVie(){ return this.vie.get(); }

    public final void setVie(double newVie){  this.vie.set(newVie); }

}
