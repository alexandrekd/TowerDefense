package app.modele.TypeMissile;

import app.modele.Attaquant;
import app.modele.Effets;
import app.modele.Missile;
import javafx.beans.property.DoubleProperty;

public interface EffetsZone extends Effets {

    public void Entrer(Attaquant acteur);
    public void Sortir(Attaquant acteur);
    public void agit();
    public DoubleProperty vieProperty();
    public boolean estVivant();
}
