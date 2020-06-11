
// Telio et un ennemi normal qui ne fait rien de special

package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;

public class Telio extends Attaquant {
    public Telio(Environnement env, int x, int y){
        super(env,20,7,x,y,1);
    }

    public void agit() {// Cette methode permet a l'attaquant de se deplacer, meme s'il peut changer de direction
            BFS.BFS(this, env);
    }
}

