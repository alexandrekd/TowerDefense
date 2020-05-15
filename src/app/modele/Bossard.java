package app.modele;

public class Bossard extends Tourelle {
    public Bossard(int x, int y, Environnement env) {
        super(5, x, y, 2, 150, env,2,"GREEN");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible));
            setDernierTire(0);;
        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
