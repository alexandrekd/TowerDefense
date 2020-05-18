package app.modele;

import app.controleur.Controleur;
import javafx.scene.shape.Circle;


public class Zone {
    private int taille;
    private String couleur;
    private double temps;
    private int degats;
    private String id;
    private int x;
    private int y;
    private double opacity;
    public Zone(int taille,String Couleur,double temps,int degats,int x,int y){
        this.taille = taille;
        this.couleur = Couleur;
        this.temps = temps;
        this.degats = degats;
        this.x = x;
        this.y = y;
        this.opacity = 1;
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void agit(){
        gererOpacity();
    }

    public void gererOpacity(){
        this.opacity = this.opacity - (1/this.temps);
    }

    public boolean estVivant() {
        if (this.opacity <= 0) {
            return false;
        }
        return true;
    }


    public String getId() {
        return id;
    }

    public int getDegats() {
        return degats;
    }

    public double getTemps() {
        return temps;
    }

    public String getCouleur() {
        return couleur;
    }

    public int getTaille() {
        return taille;
    }

    public double getOpacity() {
        return opacity;
    }
}
