package app.modele;

import java.util.Random;

public abstract class Acteur {

    static String id;
    private int x;
    private int y;
    public static int compteur = 0;
    public Environnement env;

    public Acteur(int x, int y, Environnement env){
        this.id = "A"+compteur;
        compteur++;
        this.x = x;
        this.y = y;
        this.env = env;
    }

    public Acteur(Environnement env){
        this.env = env;
        this.id = "A"+compteur;
        Random random=new Random();
        this.x = random.nextInt(env.getWidth()-1);
        this.y = random.nextInt(env.getHeight()-1);
        System.out.println("Etape 1 :\nx : " + x + "\ny : " + y + "\n");
    }

    public int getX(){
        System.out.println("this is X : " + this.x);
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y = y;
    }

    public String getId(){
        return this.id;
    }

}
