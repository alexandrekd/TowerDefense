package app.modele;

public class Rety extends Tourelle{

    int boost = 10;
    int vers = 0;
    public Rety(int x, int y, Environnement env) {
        super(5, x, y, 2, 150, env,1,"PURPLE");
    }

    @Override
    public void agit() {
        tire();
    }

    public void tire() {
        if(getRechargement() == getDernierTire()) {
            if (vers == 0){
                Attaquant cible = getCible();
                if (cible != null)
                    env.addProject(new Missile(this, cible, env));
                setDernierTire(0);
                if (boost == -50) {
                    vers++;
                    setRechargement(50);
                    boost = 0;
                }
                boost--;
            }
            else{
                Attaquant cible = getCible();
                if (cible != null)
                    env.addProject(new Missile(this, cible, env));
                setDernierTire(0);
                if (boost == 10) {
                    vers--;
                    setRechargement(10);
                }
                boost++;
            }


        }
        else
            setDernierTire(getDernierTire()+1);
    }
}
