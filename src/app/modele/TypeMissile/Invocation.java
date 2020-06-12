
// Projectile faisant apparaître des murs

package app.modele.TypeMissile;

import app.modele.*;
import app.modele.Professeur.Mur;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Invocation implements Effets {
    private Environnement env;
    private int portee;
    private int x,y;
    private String id;
    private DoubleProperty vie;

    public Invocation(Environnement env, int portee){
        vie = new SimpleDoubleProperty(1);
        this.env = env;
        this.portee = portee;
        this.id = "A" + Acteur.compteur;
        Acteur.compteur++;
    }

    public void spawnMur(int xarr,int yarr){
        donneCoAléatoireSurChemmin(xarr,yarr);
        env.getActeurs().add(new Mur(this.x,this.y,env,"Bonnot",this.portee));
        donneCoAléatoireSurChemmin(xarr,yarr);
        env.getActeurs().add(new Mur(this.x,this.y,env,"Bonnot",this.portee));
        donneCoAléatoireSurChemmin(xarr,yarr);
        env.getActeurs().add(new Mur(this.x,this.y,env,"Bonnot",this.portee));
    }

    private void donneCoAléatoireSurChemmin(int xarr,int yarr){
        do {
            this.x = ((int) (Math.random() * this.portee)) + xarr;
            this.y = ((int) (Math.random() * this.portee)) + yarr;
        }while (!(env.estDejaLa(Utile.toTexture(this.x),Utile.toTexture(this.y))));
    }



    public final double getVie(){ return this.vie.get(); }

    public final void setVie(double newVie){  this.vie.set(newVie); }

    @Override
    public void Explosion(Missile missile) {
        spawnMur(missile.getX(), missile.getY());
        setVie(0);
    }

    @Override
    public String getId() {
        return this.id;
    }

}
