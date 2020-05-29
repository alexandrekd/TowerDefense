package app.modele;

import javafx.beans.property.DoubleProperty;

public interface Effets {

    public void agit();
    public void Entrer(Attaquant acteur);
    public void Sortir(Attaquant acteur);
    public void Explosion(Missile missile);
    public String getId();
    public DoubleProperty vieProperty();
    public boolean estVivant();
}
