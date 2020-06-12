package app.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public abstract class Acteur {

    private String id;
    private IntegerProperty xProperty, yProperty;
    public static int compteur = 0;
    private int distanceMilieu; // Les attaquants restent toujours a la meme distance du milieu du chemin pour ne pas longer les murs ou en sortir
    public Environnement env;
    private String name;

    public Acteur(int x, int y, Environnement env, String name){
        this.id = "A"+compteur;
        compteur++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.env = env;
        this.name = name;
        this.distanceMilieu = Utile.toXPixel(y)-25;
    }

    public String getName() {
        return name;
    }

    public Acteur(Environnement env,String name){
        this.env = env;
        this.name = name;
        this.id = "A"+compteur;
        compteur++;
        Random random=new Random();
        int x = random.nextInt(env.getWidth()-1);
        int y = random.nextInt(env.getHeight()-1);
        this.distanceMilieu = x - 25;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
    }

    public int getX() {
        return xProperty.getValue();
    }
    public void setX(int n){
        xProperty.setValue(n);
    }
    public final IntegerProperty getXProperty(){ return xProperty; }

    public int getY() {
        return yProperty.getValue();
    }
    public void setY(int n){
        yProperty.setValue(n);
    }
    public final IntegerProperty getYProperty(){ return yProperty; }

    public abstract void agit();

    public String getId(){
        return this.id;
    }

    public int getDistanceMilieu() {
        return distanceMilieu;
    }
}
