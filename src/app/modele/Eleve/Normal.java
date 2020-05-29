package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Utile;

public class Normal extends Attaquant {
    public Normal(Environnement env, int x, int y){
        super(env,20,7,x,y,1);
    }

    public void agit() {// Cette methode permet a l'attaquant de se deplacer, meme s'il peut changer de direction

        int xB = Utile.toTexture(this.getX());
        int yB = Utile.toTexture(this.getY());
        int bestPos = 10000;

        if (RegardeUnVoisin(env.getUnNode(xB - 1, yB)) < bestPos) {
            donneDirection(-1, 0);
            bestPos = RegardeUnVoisin(env.getUnNode(xB - 1, yB));
        }
        if (RegardeUnVoisin(env.getUnNode(xB + 1, yB)) < bestPos) {
            donneDirection(1, 0);
            bestPos = RegardeUnVoisin(env.getUnNode(xB + 1, yB));
        }
        if (RegardeUnVoisin(env.getUnNode(xB, yB - 1)) < bestPos) {
            donneDirection(0, -1);
            bestPos = RegardeUnVoisin(env.getUnNode(xB, yB - 1));
        }
        if (RegardeUnVoisin(env.getUnNode(xB, yB + 1)) < bestPos) {
            donneDirection(0, 1);
            bestPos = RegardeUnVoisin(env.getUnNode(xB, yB + 1));
        }

        this.setX(this.getX() + getVitesse() * getDx());
        //Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
        this.setY(this.getY() + getVitesse() * getDy());
        //Pas besoin de else, vu que si le prochain deplacement en y depasse l'environnement, il ne bouge pas
    }
}

