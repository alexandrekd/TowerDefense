package app.modele;

import app.modele.Eleve.Mateo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static app.modele.Utile.toTexture;

public class Niveau {

    private String id;
    private static int compteur = 0;
    private Environnement env;
    private IntegerProperty vieProperty;
    private IntegerProperty argentProperty;
    private static Vagues vagues;
    private static int idVagues;
    private int totalEnnemis;

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
                vagues.getVagues().add(vagues.creerVague(2));
                vagues.getVagues().add(vagues.creerVague(0));
                vagues.getVagues().get(1).add(Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().add(vagues.creerVague(0));
                vagues.getVagues().get(2).add(Utile.creerEnnemi(this.env, 3));
                break;

            case 2:
                vagues.getVagues().add(vagues.creerVague(15));
                /*vagues.getVagues().get(0).add(Utile.creerEnnemi(this.env,5));
                vagues.getVagues().get(0).add(0, Utile.creerEnnemi(this.env, 3));
                vagues.getVagues().add(vagues.creerVague(10));
                vagues.getVagues().get(1).add(2, Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().get(1).add(4, Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().get(1).add(6, Utile.creerEnnemi(this.env, 2));
                vagues.getVagues().get(0).add(Utile.creerEnnemi(this.env,4));
                vagues.getVagues().get(0).add(Utile.creerEnnemi(this.env,6));*/
                break;
        }

        // L'ennemi Mateo invoque des ennemis, on ajoute donc ce nombre au total
        for(int vague = 0; vague < this.getVagues().getVagues().size(); vague++)
            for (int ennemi = 0; ennemi < this.getVagues().getVagues().get(vague).size(); ennemi++) {
                if (this.getVagues().getVagues().get(vague).get(ennemi) instanceof Mateo)
                    totalEnnemis += 23;
                totalEnnemis++;
            }
    }

    public Vagues getVagues(){
        return this.vagues;
    }

    public int getTotalEnnemis(){
        return totalEnnemis;
    }

    public void incrementerArgent(int argent){
        setArgent(this.getArgent() + argent);
    }

    // Cette méthode a pour rôle de s'occuper de l'ennemi lorsqu'il arrive à la fin de la map,
    // c'est à dire, infliger des dégats au joueur et disparaitre.
    public void ennemiAttaqueJoueur(Attaquant ennemi){
        int x = toTexture(ennemi.getX());
        int y = toTexture(ennemi.getY());
        int xArrive = env.getArrivé().getX();
        int yArrive = env.getArrivé().getY();

        if (x == xArrive && y == yArrive ){
            this.setVie(this.getVie() - ennemi.getPv());
            env.getActeurs().remove(ennemi);
        }

    }

    public boolean joueurVivant(){
        if(this.getVie() > 0)
            return true;
        return false;
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
