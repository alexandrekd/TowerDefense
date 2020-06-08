package app.modele;

import javafx.beans.property.DoubleProperty;

public interface Effets {

    public void Explosion(Missile missile);
    public String getId();
    public DoubleProperty vieProperty();
    public boolean estVivant();
}
