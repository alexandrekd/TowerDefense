package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;

public class Mateo extends Attaquant {
    public Mateo(Environnement env, int x, int y){
        super(env,100,3,x,y,1);
    }

    public void agit() {// Cette methode permet a l'attaquant de se deplacer, meme s'il peut changer de direction
        BFS.BFS(this,env);
    }
}