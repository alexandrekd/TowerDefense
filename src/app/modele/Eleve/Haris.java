package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;
import app.modele.Missile;
import javafx.collections.ObservableList;

public class Haris extends Attaquant {
    public Haris(Environnement env, int x, int y){
        super(env,10,20,x,y,99999); //valeurs à modifier
    }

    @Override
    public void recevoirTir(int dégatsReçus) { //Cette méthode permet à Haris d'avoir un certain pourcentage de chance d'éviter les dégats de l'attaque d'une tourelle.

        double tentativePourEviter = Math.random() * 1;

        if (tentativePourEviter > 0.5){
            this.setPv(this.getPv() - dégatsReçus);
        }

        else{
            this.setPv(this.getPv());
        }
    }

    public void agit() {
        BFS.BFS(this,env);
    }


}
