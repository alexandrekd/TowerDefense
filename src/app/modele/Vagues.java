package app.modele;

import java.util.ArrayList;

public class Vagues {
    private Environnement env;
    private ArrayList<ArrayList<Attaquant>> vagues; // liste de listes de vagues
    private int numVague;   // numéro de la vague en cours

    public Vagues(Environnement env){
        this.env = env;
        this.vagues = new ArrayList<>();
        this.numVague = 0;
    }

    public ArrayList<ArrayList<Attaquant>> getVagues() {
        return this.vagues;
    }

    // Cree des vagues de x ennemis
    public ArrayList<Attaquant> creerVague(int nbEnnemis){
        ArrayList<Attaquant> vague = new ArrayList<>();
        for(int i = 0; i < nbEnnemis; i++)
            vague.add((Attaquant) Utile.creerEnnemi(this.env));
        return vague;
    }

    // Recupere le premier ennemi de la vague et l'ajoute a l'environnement le retire de la liste tous les 15 tours
    // Si la liste est vide on incremente numVague et on stop celle en cours
    public void fetchEnnemi(){
        if(this.env.getNbTours()%15 == 0){
            this.env.getActeurs().add(this.vagues.get(numVague).get(0));
            this.vagues.get(numVague).remove(0);
        }

        if (this.vagues.get(numVague).size() == 0) {
            this.numVague++;
            this.env.stopVague();
        }
    }

}
