package app.modele.TypeMissile;

import app.modele.*;
import app.modele.Professeur.Mur;

import java.util.stream.Collectors;

public class Invocation implements Effets {
    private Environnement env;
    private int portee;
    private int x,y;
    public Invocation(Environnement env, int portee){
        this.env = env;
        this.portee = portee;
    }

    @Override
    public void agit() {

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


    @Override
    public void Entrer(Attaquant acteur) {

    }

    @Override
    public void Sortir(Attaquant acteur) {

    }

    @Override
    public void Explosion(Missile missile) {
        spawnMur(missile.getX(), missile.getY());
    }

}
