package app.modele;

public class BFS {

    public static void BFS(Attaquant attaquant,Environnement env) {
        int xB = Utile.toTexture(attaquant.getX());
        int yB = Utile.toTexture(attaquant.getY());
        int bestPos = 10000;

        if (attaquant.RegardeUnVoisin(env.getUnNode(xB - 1, yB)) < bestPos) {
            attaquant.donneDirection(-1, 0);
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB - 1, yB));
        }
        if (attaquant.RegardeUnVoisin(env.getUnNode(xB + 1, yB)) < bestPos) {
            attaquant.donneDirection(1, 0);
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB + 1, yB));
        }
        if (attaquant.RegardeUnVoisin(env.getUnNode(xB, yB - 1)) < bestPos) {
            attaquant.donneDirection(0, -1);
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB, yB - 1));
        }
        if (attaquant.RegardeUnVoisin(env.getUnNode(xB, yB + 1)) < bestPos) {
            attaquant.donneDirection(0, 1);
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB, yB + 1));
        }

        attaquant.setX(attaquant.getX() + attaquant.getVitesse() * attaquant.getDx());
        //Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
        attaquant.setY(attaquant.getY() + attaquant.getVitesse() * attaquant.getDy());
        //Pas besoin de else, vu que si le prochain deplacement en y depasse l'environnement, il ne bouge pas
    }
}
