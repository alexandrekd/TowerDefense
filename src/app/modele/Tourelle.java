package app.modele;

public class Tourelle extends Acteur{
    /*private String id;
    public static int compteur = 0;
    private int x;
    private int y;
    private Environnement env;*/

    private int degat;
    private int rechargement;
    private int portee;
    private int dernierTire;


    public Tourelle(int degat, int x, int y, int rechargement, int portee, Environnement env) {
        /*this.env = env;*/
        /*this.id = "A"+compteur;
        compteur++;*/
        /*this.x = x;
        this.y = y;*/

        super(x, y, env);
        this.degat = degat;
        this.rechargement = rechargement;
        this.portee = portee;
        dernierTire = rechargement;
    }

    //Cette méthode récupère une cible a la portée de la tour
    public Attaquant getCible() {
        Attaquant cible = null;

        for (int i = 0; i < this.env.getActeurs().size();i++)
            if (this.env.getActeurs().get(i) instanceof Attaquant)
                if(env.getActeurs().get(i).getX() <= this.getX()+portee && env.getActeurs().get(i).getX() >= this.getX()-portee && env.getActeurs().get(i).getY() <= this.getY()+portee && env.getActeurs().get(i).getY() >= this.getY()-portee){
                    cible = (Attaquant) env.getActeurs().get(i);
                }
        return cible;
    }

    public int getDegat(){
        return this.degat;
    }

    //Permet de tire sur l'étudiant
    public void tire() {
        if(rechargement == dernierTire) {
            Attaquant cible = getCible();
            if (cible != null)
                env.addProject(new Missile(this, cible));
            dernierTire = 0;
        }
        else
            dernierTire++;
    }

    public void agit(){
        tire();
    }
}
