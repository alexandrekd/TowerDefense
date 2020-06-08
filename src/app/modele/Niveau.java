package app.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Arrays;

import static app.modele.Utile.creerEnnemi;
import static app.modele.Utile.toTexture;

public class Niveau {

    private String id;
    private static int compteur = 0;
    private Environnement env;
    private IntegerProperty vieProperty;
    private IntegerProperty argentProperty;
    private static Vagues vagues;
    private static int idVagues;
    //private ArrayList<int> tourellesDispo;

    public Niveau(Environnement env){
        this.id = "Niv" + compteur++;
        this.env = env;
        this.vieProperty = new SimpleIntegerProperty(1);
        this.argentProperty = new SimpleIntegerProperty(20);

        vagues = new Vagues(this.env);
        setVagues();
    }

    public static void setIdVagues(int id){
        idVagues = id;
    }

    public void setVagues(){

        switch (idVagues){
            case 1:
                vagues.getVagues().add(vagues.creerVague(1));/*
                vagues.getVagues().get(0).add(0, Utile.creerEnnemi(this.env, 3));
                vagues.getVagues().add(vagues.creerVague(4));
                vagues.getVagues().get(1).add(1, Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().get(1).add(4, Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().get(1).add(Utile.creerEnnemi(this.env, 3));*/
                break;

            case 2:
                vagues.getVagues().add(vagues.creerVague(15));
                vagues.getVagues().get(0).add(0, Utile.creerEnnemi(this.env, 3));
                vagues.getVagues().add(vagues.creerVague(10));
                vagues.getVagues().get(1).add(2, Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().get(1).add(4, Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().get(1).add(6, Utile.creerEnnemi(this.env, 2));
                break;
        }

    }

    public Vagues getVagues(){
        return this.vagues;
    }

    public void incrementerArgent(int argent){
        setArgent(this.getArgent() + argent);
    }

    // Cette méthode a pour rôle de s'occuper de l'ennemi lorsqu'il arrive à la fin de la map,
    // c'est à dire, infliger des dégats au joueur et disparaitre.
    public void ennemiAttaqueJoueur(Attaquant ennemi){
        int x = toTexture(ennemi.getX());
        int y = toTexture(ennemi.getY()); //x et y sont les coordonées de la tuile sur laquelle l'ennemi se situe

        int xArrive = env.getArrivé().getX();
        int yArrive = env.getArrivé().getY(); //xArrive et yArrive sont les coordonées de la tuile d'arrivé

        if (x == xArrive && y == yArrive ){
            this.setVie(this.getVie() - ennemi.getPv());
            env.getActeurs().remove(ennemi);
        }

    }

    public boolean joueurVivant(){  //Cette méthode renvoie true si le joueur est vivant ou renvoie false si le jour à des pv inférieurs ou égal à 0.
        if(this.getVie() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean joueurAvoirGagne(){  //Cette méthode renvoie true si le joueur a gagné (plus d'ennemis sur le plateau et plus d'ennemis qui
                                        //vont apparaitre), ou renvoie false si le joueur est encore en cours de partie

        if(this.env.getNiveau().getVagues().getVagues().size() == 0 && this.env.getAttaquantsInActeurs().size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public IntegerProperty getArgentProperty() {
        return argentProperty;
    }
    public int getArgent(){
        return this.argentProperty.getValue();
    }
    public void setArgent(int v){
        this.argentProperty.setValue(v);
    }

    public IntegerProperty getVieProperty() {
        return vieProperty;
    }
    public int getVie(){
        return this.vieProperty.getValue();
    }
    public void setVie(int v){
        this.vieProperty.setValue(v);
    }

    public String getId() {
        return id;
    }

}
