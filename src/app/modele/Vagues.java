package app.modele;

import java.util.ArrayList;

public class Vagues {
    private Environnement env;
    private ArrayList<ArrayList<Attaquant>> vagues; // liste de listes de vagues

    public Vagues(Environnement env){
        this.env = env;
        this.vagues = new ArrayList<>();
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
    // Si la liste est vide on la suprime et on stop celle en cours
    public Acteur fetchEnnemi(){
        Acteur acteur = this.vagues.get(0).get(0);
        this.vagues.get(0).remove(0);

        if (this.vagues.get(0).size() == 0) {
            this.vagues.remove(0);
            this.env.stopVague();
        }

        return acteur;
    }

}