package app.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Missile {
    private String id;
    private Tourelle debActeur;
    private Attaquant finActeur;
    private IntegerProperty xProperty, yProperty;
    private boolean explosion;

    public Missile(Tourelle depart, Attaquant fin){
        this.debActeur = depart;
        this.finActeur = fin;
        this.xProperty = new SimpleIntegerProperty(depart.getX());
        this.yProperty = new SimpleIntegerProperty(depart.getY());
        this.id = depart.getId();
        explosion = false;
    }

    public String getId() {
        return id;
    }

    public void bouge() {
        int xarv = this.getX() - finActeur.getX();
        int yarv = this.getY() - finActeur.getY();

        if (xarv > 0) {
            System.out.println("x - 1");
            this.setX(this.getX() - 1);
        }
        else if (xarv < 0) {
            System.out.println("x + 1");
            this.setX(this.getX() + 1);
        }

        if (yarv == 0 && xarv == 0) {
            System.out.println("==");
        finActeur.recevoirTir(debActeur.getDegat());
        this.explosion = true;
        }
        else if (yarv < 0) {
            System.out.println("y + 1");
            this.setY(this.getY() + 1);
        }
        else {
            System.out.println("y - 1");
            this.setY(this.getY() - 1);
        }

    }
    public  int getX() {
        return xProperty.getValue();
    }
    public  void setX(int n){
        xProperty.setValue(n);
    }
    public final IntegerProperty getXProperty(){ return xProperty; }

    public  int getY() {
        return yProperty.getValue();
    }
    public  void setY(int n){
        yProperty.setValue(n);
    }
    public final IntegerProperty getYProperty(){ return yProperty; }

    public boolean isExplosion() {
        return explosion;
    }
}



