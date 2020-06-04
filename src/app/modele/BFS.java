package app.modele;

public class BFS {

    public static void BFS(Attaquant attaquant,Environnement env) {



        int xB = Utile.toTexture(attaquant.getX());
        int yB = Utile.toTexture(attaquant.getY());

        int directionX = 0;
        int directionY = 0;

        int bestPos = 10000;

        if (attaquant.RegardeUnVoisin(env.getUnNode(xB - 1, yB)) < bestPos) {
            directionX = -1;
            directionY = sensY(attaquant,xB,yB,env);
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB - 1, yB));
        }
        if (attaquant.RegardeUnVoisin(env.getUnNode(xB + 1, yB)) < bestPos) {
            directionX = 1;
            directionY = sensY(attaquant,xB,yB,env);
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB + 1, yB));
        }
        if (attaquant.RegardeUnVoisin(env.getUnNode(xB, yB - 1)) < bestPos) {
            directionX = sensX(attaquant,xB,yB,env);
            directionY = -1;
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB, yB - 1));
        }
        if (attaquant.RegardeUnVoisin(env.getUnNode(xB, yB + 1)) < bestPos) {
            directionX = sensX(attaquant,xB,yB,env);
            directionY = 1;
            bestPos = attaquant.RegardeUnVoisin(env.getUnNode(xB, yB + 1));
        }

        attaquant.donneDirection(directionX,directionY);

        attaquant.setX(attaquant.getX() + attaquant.getVitesse() * attaquant.getDx());
        //Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
        attaquant.setY(attaquant.getY() + attaquant.getVitesse() * attaquant.getDy());
        //Pas besoin de else, vu que si le prochain deplacement en y depasse l'environnement, il ne bouge pas
    }

    public static int sensX(Attaquant attaquant,int x,int y,Environnement env){
        //System.out.println(Utile.toPixel(env.getUnNode(xB , yB).getX()));
        int direction;
        if (attaquant.getX() == Utile.toPixel(env.getUnNode(x , y).getX()) + 25
                + attaquant.getDistanceMilieu()){
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
       // System.out.println(attaquant.getY()+"////"+attaquant.getX());

        //System.out.println(Utile.toPixel(env.getUnNode(xB , yB).getX()));
        int direction;
        if (attaquant.getY() == Utile.toPixel(env.getUnNode(x , y).getY()) + 25
                + attaquant.getDistanceMilieu()){
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
