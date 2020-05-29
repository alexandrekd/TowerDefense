package app.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public abstract class Acteur {

    private String id;
    private IntegerProperty xProperty, yProperty;
    public static int compteur = 0;
    public Environnement env;
    private String name;

    public Acteur(int x, int y, Environnement env, String name){
        this.id = "A"+compteur;
        compteur++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.env = env;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Acteur(Environnement env){
        this.env = env;
        this.id = "A"+compteur;
        compteur++;
        Random random=new Random();
        int x = random.nextInt(env.getWidth()-1);
        int y = random.nextInt(env.getHeight()-1);
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
    public abstract void agit();
    public int getY() {
        return yProperty.getValue();
    }
    public void setY(int n){
        yProperty.setValue(n);
    }
    public final IntegerProperty getYProperty(){ return yProperty; }

    public String getId(){
        return this.id;
    }

}
