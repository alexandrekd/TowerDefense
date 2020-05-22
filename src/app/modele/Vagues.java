package app.modele;

import java.util.ArrayList;

public class Vagues {
    private Environnement env;
    private ArrayList<ArrayList<Attaquant>> vagues;
    private int numVague;

    public Vagues(Environnement env){
        this.env = env;
        this.vagues = new ArrayList<>();
        this.numVague = 0;
    }

    public ArrayList<ArrayList<Attaquant>> getVagues() {
        return this.vagues;
    }

    public ArrayList<Attaquant> creerVague(int nbEnnemis){
        ArrayList<Attaquant> vague = new ArrayList<>();
        for(int i = 0; i < nbEnnemis; i++)
            vague.add((Attaquant) Utile.creerEnnemi(this.env));
        return vague;
    }

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
