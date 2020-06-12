
// Permet aux attaquants de ce deplasser

package app.modele;

public class BFS {

    public static void BFS(Attaquant attaquant,Environnement env) {

        int xB = Utile.toTexture(attaquant.getX());
        int yB = Utile.toTexture(attaquant.getY());

        int directionX = 0;
        int directionY = 0;

        int bestPos = 10000;

        try {
            if (attaquant.RegardeUnVoisin(env.getUnNode(xB - 1, yB)) < bestPos) {
                directionX = -1;
                directionY = sensY(attaquant, xB, yB, env);
                bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB - 1, yB));
            }
            if (attaquant.RegardeUnVoisin(env.getUnNode(xB + 1, yB)) < bestPos) {
                directionX = 1;
                directionY = sensY(attaquant, xB, yB, env);
                bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB + 1, yB));
            }
            if (attaquant.RegardeUnVoisin(env.getUnNode(xB, yB - 1)) < bestPos) {
                directionX = sensX(attaquant, xB, yB, env);
                directionY = -1;
                bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB, yB - 1));
            }
            if (attaquant.RegardeUnVoisin(env.getUnNode(xB, yB + 1)) < bestPos) {
                directionX = sensX(attaquant, xB, yB, env);
                directionY = 1;
            }


            attaquant.donneDirection(directionX,directionY);

            attaquant.setX(attaquant.getX() + attaquant.getVitesse() * attaquant.getDx());
            attaquant.setY(attaquant.getY() + attaquant.getVitesse() * attaquant.getDy());

        } catch (Exception e){
            System.out.println("Un ennemi a essaye de sortir du chemin");
            attaquant.setX(getMilieuChemin(attaquant)[0]);
            attaquant.setY(getMilieuChemin(attaquant)[1]);
        }
    }

    public static int[] getMilieuChemin(Attaquant attaquant){
        int[] coordoneesMilieu = new int[2];

        node node = null;
        for (int i = 0; i < attaquant.getEnv().getRang().size(); i++){
            if (Utile.toTexture(attaquant.getEnv().getRang().get(i).getX()) <= Utile.toTexture(attaquant.getX())+1 && Utile.toTexture(attaquant.getEnv().getRang().get(i).getX()) >= Utile.toTexture(attaquant.getX())-1 &&
                Utile.toTexture(attaquant.getEnv().getRang().get(i).getY()) <= Utile.toTexture(attaquant.getY())+1 && Utile.toTexture(attaquant.getEnv().getRang().get(i).getY()) >= Utile.toTexture(attaquant.getY())-1)
                node = attaquant.getEnv().getRang().get(i);
        }
        System.out.println("node " + node);
        coordoneesMilieu[0] = Utile.toPixel(node.getX()) + 25;
        coordoneesMilieu[1] = Utile.toPixel(node.getX()) + 25;

        return coordoneesMilieu;
    }

    public static int sensX(Attaquant attaquant,int x,int y,Environnement env){
        int direction;
        if (attaquant.getX() == Utile.toPixel(env.getUnNode(x , y).getX()) + 25 + attaquant.getDistanceMilieu()){
            direction = 0;
        }
        else if (attaquant.getX() < Utile.toPixel(env.getUnNode(x , y).getX()) + 25 + attaquant.getDistanceMilieu()){
            direction = 1;
        }
        else
            direction = -1;

        return direction;
    }

    public static int sensY(Attaquant attaquant,int x,int y,Environnement env){
        int direction;

        if (attaquant.getY() == Utile.toPixel(env.getUnNode(x , y).getY()) + 25 + attaquant.getDistanceMilieu()){
            direction = 0;
        }
        else if (attaquant.getY() < Utile.toPixel(env.getUnNode(x , y).getY()) + 25 + attaquant.getDistanceMilieu()){
            direction = 1;
        }
        else
            direction = -1;

        return direction;
    }


}
