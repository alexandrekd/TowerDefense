package app.modele;

public class Homps extends Tourelle{
    public Homps(int x, int y, Environnement env) {
        super(3, x, y, 50, 150, env,1,"ORANGE");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null){
                env.addProject(new Missile(this, cible, env));
            }
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
