package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;
import app.modele.Utile;

// Cet ennemi fait apparaitre
public class Mateo extends Attaquant {
    public Mateo(Environnement env, int x, int y){
        super(env,100,3,x,y,1);
    }

    public void agit() {
        BFS.BFS(this,env);
        if(!this.estVivant())
            for (int i = 0; i < 20; i++)
                this.env.getActeurs().add(new Telio(this.env, Utile.toPixel(Utile.toTexture(this.getX())) + 25 + (int) (Math.random() * 15), Utile.toPixel(Utile.toTexture(this.getY())) + 25 + (int) (Math.random() * 15)));
    }
}