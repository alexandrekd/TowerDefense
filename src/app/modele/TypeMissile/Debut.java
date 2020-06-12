
// A une chance de envoiyer les ennemis à la case départ

package app.modele.TypeMissile;

import app.modele.Acteur;
import app.modele.Effets;
import app.modele.Missile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import static app.modele.Utile.goToCaseDepart;

public class Debut implements Effets {
    private DoubleProperty vie = new SimpleDoubleProperty(1);
    private String id;

    public Debut(){
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
    }


    @Override
    public void Explosion(Missile missile) {
        missile.getFinActeur().recevoirTir(missile.getDebActeur().getDegat());
            if(Math.random()*10 <= 2)
                goToCaseDepart(missile);
            setVie(0);

    }

    @Override
    public String getId() {
        return this.id;
    }

    public final double getVie(){ return this.vie.get(); }

    public final void setVie(double newVie){  this.vie.set(newVie); }
}
