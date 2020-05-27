package app.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Zone{
    private ObservableList<Acteur> acteursDansLaZone;
    private int taille;
    private String couleur;
    private double temps;
    private Effets effet;
    private String id;
    private int x;
    private int y;
    private DoubleProperty opacityProperty;
    private Environnement env;
    public Zone(int taille,String Couleur,double temps,int x,int y,Effets effet,Environnement env){
        this.taille = taille;
        this.couleur = Couleur;
        this.temps = temps;
        this.effet = effet;
        this.x = x;
        this.y = y;
        this.opacityProperty = new SimpleDoubleProperty(1);
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
        this.env = env;
        acteursDansLaZone = FXCollections.observableArrayList();
        ListChangeListener<Acteur> liste= c->{
            while (c.next()) {
                for(Acteur acteur : c.getAddedSubList()) {
                    effet.Entrer((Attaquant) acteur);
                }

                for(Acteur acteur : c.getRemoved()) {
                    effet.Sortir((Attaquant) acteur);
                }
            }
        };
        acteursDansLaZone.addListener(liste);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void agit(){
        quiEstDansLaZone();
        gererOpacity();
    }

    public void quiEstDansLaZone(){
        for (int i = 0;i < env.getActeurs().size() ; i++){
            if (dansLaListe(env.getActeurs().get(i))) {
                if (!(env.getActeurs().get(i).getX() <= this.taille + x && env.getActeurs().get(i).getX() >= x - this.taille && env.getActeurs().get(i).getY() <= this.taille + y && env.getActeurs().get(i).getY() >= y - this.taille)) {
                    acteursDansLaZone.remove(env.getActeurs().get(i));
                    i--;
                }
            }
            else{
                if(env.getActeurs().get(i).getX() <= this.taille+x && env.getActeurs().get(i).getX() >= x-this.taille && env.getActeurs().get(i).getY() <= this.taille+y && env.getActeurs().get(i).getY() >= y-this.taille)
                    if (env.getActeurs().get(i) instanceof Attaquant) {
                        acteursDansLaZone.add(env.getActeurs().get(i));
                    }
            }
        }
    }

    public ArrayList<Attaquant> getListeAttanquants(){
        ArrayList<Attaquant> list = new ArrayList<Attaquant>();
        for (int i = 0; i < acteursDansLaZone.size();i++){
                list.add((Attaquant) acteursDansLaZone.get(i));
        }
        return list;
    }

    public boolean dansLaListe(Acteur acteur){
        for (int i = 0; i < acteursDansLaZone.size();i++){
            if (acteursDansLaZone.get(i) == acteur){
                return true;
            }
        }
        return false;
    }

    public void gererOpacity(){
        this.setOpacity(this.getOpacity() - (1/this.temps));
    }

    public boolean estVivant() {
        if (this.getOpacity() <= 0) {
            env.getEffects().remove(effet);
            return false;
        }
        return true;
    }


    public String getId() {
        return id;
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

    public final DoubleProperty opacityProperty() {
        return opacityProperty;
    }

    public final double getOpacity(){ return this.opacityProperty.get(); }

    public final void setOpacity(double opacity){  this.opacityProperty.set(opacity); }

    public ObservableList<Acteur> getActeursDansLaZone() {
        return acteursDansLaZone;
    }
}
