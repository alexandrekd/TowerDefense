package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;
import app.modele.Utile;

public class Normal extends Attaquant {
    public Normal(Environnement env, int x, int y){
        super(env,20,7,x,y,1);
    }

    public void agit() {// Cette methode permet a l'attaquant de se deplacer, meme s'il peut changer de direction
        BFS.BFS(this,env);
    }
}

