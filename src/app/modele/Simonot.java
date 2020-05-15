package app.modele;

public class Simonot extends Tourelle {
    public Simonot(int x, int y, Environnement env) {
        super(5, x, y, 2, 150, env,1,"YELLOW");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible, env));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
