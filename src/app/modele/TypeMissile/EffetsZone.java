
// Projectile ayant un effet de zone

package app.modele.TypeMissile;

import app.modele.Attaquant;
import app.modele.Effets;
import javafx.beans.property.DoubleProperty;

public interface EffetsZone extends Effets {

    void Entrer(Attaquant acteur);
    void Sortir(Attaquant acteur);
    void agit();
    DoubleProperty vieProperty();
    boolean estVivant();
}
