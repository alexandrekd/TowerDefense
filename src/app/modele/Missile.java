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
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
        explosion = false;
    }

        public Tourelle getDebActeur() {
            return debActeur;
        }

        public String getId() {
        return id;
    }

    public void bouge() {
        int xarv = this.getX() - finActeur.getX();
        int yarv = this.getY() - finActeur.getY();

        if (xarv > 0) {
            this.setX(this.getX() - 1);
        }
        else if (xarv < 0) {
            this.setX(this.getX() + 1);
        }

        if (yarv == 0 && xarv == 0) {
            this.finActeur.recevoirTir(debActeur.getDegat());
            this.finActeur.setX();

            this.explosion = true;
        }
        else if (yarv < 0) {
            this.setY(this.getY() + 1);
        }
        else {
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



