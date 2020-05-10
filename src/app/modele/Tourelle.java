package modele;

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
        dernierTire = 10;
    }

    //Cette méthode récupère une cible a la portée de la tour
    public Attaquants getCible() {
        Attaquants cible = null;

        for (int i = 0; i < env.getAttaquants().size();i++)
            if(env.getAttaquants().get(i).getX() <= this.getX()+portee && env.getAttaquants().get(i).getX() >= this.getX()-portee && env.getAttaquants().get(i).getY() <= this.getY()+portee && env.getAttaquants().get(i).getY() >= this.getY()-portee){
                cible = env.getAttaquants().get(i);
            }
        return cible;
    }

    //Permet de tire sur l'étudiant
    public void tire() {
        Attaquants cible = getCible();
        if (cible != null)
            cible.recevoirTir(this.degat);
    }

    public void agit(){
        tire();
    }
}
