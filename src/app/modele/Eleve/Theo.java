
// Theo est un ennemi qui a la capacite de regenerer la vie de ses allies a portee

package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;
import java.util.List;
import java.util.stream.Collectors;

public class Theo extends Attaquant {
    private int portee;
    public Theo(Environnement env, int x, int y){
        super(env,20,7,x,y,1);
        this.portee = 1000;
    }

    @Override
    public void agit() {

        BFS.BFS(this,env);
        List<Attaquant> liste = env.getActeurs().parallelStream().filter(n -> n instanceof Attaquant).map(n -> (Attaquant) n).collect(Collectors.toList());

        if (env.getNbTours() % 5 == 0)
            for (int i = 0; i < liste.size(); i++)
                if (liste.get(i).getPv() < liste.get(i).getPV())
                    if (liste.get(i).getX() <= this.getX() + this.portee && liste.get(i).getX() >= this.getX() - this.portee && liste.get(i).getY() <= this.getY() + this.portee && liste.get(i).getY() >= this.getY() - this.portee)
                        liste.get(i).gagneDeLaVie(1);
    }
}
