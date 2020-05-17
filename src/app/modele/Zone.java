package app.modele;

import app.controleur.Controleur;
import javafx.scene.shape.Circle;


public class Zone {
    private int taille;
    private String couleur;
    private int temps;
    private int degats;
    private String id;
    private Missile missile;
    private double opacity;
    public Zone(int taille,String Couleur,int temps,int degats,Missile missile){
        this.taille = taille;
        this.couleur = Couleur;
        this.temps = temps;
        this.degats = degats;
        this.missile = missile;
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
    }

    public Missile getMissile() {
        return missile;
    }

    public void agit(){
        Controleur.gererOpacity();
    }


    public String getId() {
        return id;
    }

    public int getDegats() {
        return degats;
    }

    public int getTemps() {
        return temps;
    }

    public String getCouleur() {
        return couleur;
    }

    public int getTaille() {
        return taille;
    }
}
