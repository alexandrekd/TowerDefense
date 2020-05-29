package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.Environnement;
import app.modele.Utile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Theo extends Attaquant {
    private int portee;
    public Theo(Environnement env, int x, int y){
        super(env,20,7,x,y,1);
        this.portee = 1000;
    }

    @Override
    public void agit() {
        int xB = Utile.toTexture(this.getX());
        int yB = Utile.toTexture(this.getY());
        int bestPos = 10000;

        if (RegardeUnVoisin(env.getUnNode(xB - 1, yB)) < bestPos) {
            donneDirection(-1, 0);
            bestPos = RegardeUnVoisin(env.getUnNode(xB - 1, yB));
        }
        if (RegardeUnVoisin(env.getUnNode(xB + 1, yB)) < bestPos) {
            donneDirection(1, 0);
            bestPos = RegardeUnVoisin(env.getUnNode(xB + 1, yB));
        }
        if (RegardeUnVoisin(env.getUnNode(xB, yB - 1)) < bestPos) {
            donneDirection(0, -1);
            bestPos = RegardeUnVoisin(env.getUnNode(xB, yB - 1));
        }
        if (RegardeUnVoisin(env.getUnNode(xB, yB + 1)) < bestPos) {
            donneDirection(0, 1);
            bestPos = RegardeUnVoisin(env.getUnNode(xB, yB + 1));
        }

        this.setX(this.getX() + getVitesse() * getDx());
        //Pas besoin de else, vu que si le prochain deplacement en x depasse l'environnement, il ne bouge pas
        this.setY(this.getY() + getVitesse() * getDy());
        //Pas besoin de else, vu que si le prochain deplacement en y depasse l'environnement, il ne bouge pas

        List<Attaquant> liste = env.getActeurs().parallelStream().filter(n -> n instanceof Attaquant).map(n -> (Attaquant) n).collect(Collectors.toList());

        if (env.getNbTours() % 5 == 0) {
            for (int i = 0; i < liste.size(); i++) {
                System.out.println(liste.get(i).getPv() + "///" + liste.get(i).getPV());
                if (liste.get(i).getPv() < liste.get(i).getPV()) {
                    System.out.println(liste.get(i).getPv() + "///" + liste.get(i).getPV());
                    if (liste.get(i).getX() <= this.getX() + this.portee && liste.get(i).getX() >= this.getX() - this.portee && liste.get(i).getY() <= this.getY() + this.portee && liste.get(i).getY() >= this.getY() - this.portee) {
                        System.out.println(liste.get(i).getPv() + "bodel");
                        liste.get(i).gagneDeLaVie(1);
                        System.out.println(liste.get(i).getPv());
                    }
                }
            }
        }
    }
}
