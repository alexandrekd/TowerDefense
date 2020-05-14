package app.modele;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;

public class Environnement {
    private int width, height;
    /*private List<Tourelle> tourelles;
    private ObservableList<Attaquants> attaquants;*/
    private ObservableList<Acteur> acteurs;
    private List<Integer> map;
    private ObservableList<Missile> project;
    private int nbTours;
    private List<node> rang;

    public Environnement(int width, int height){
        acteurs = FXCollections.observableArrayList();
        this.width = width;
        this.height = height;
        project = FXCollections.observableArrayList();
        rang = new ArrayList<node>();
        map = new ArrayList<Integer>(Arrays.asList(101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                900, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 104,
                101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101
        ));

    }
    public List<Integer> getMap(){
        return this.map;
    }
    public int getWidth(){
        return this.width;
    }


    public int getHeight(){
        return this.height;
    }

    public ObservableList<Acteur> getActeurs(){
        return this.acteurs;
    }

    public void faireRang(int x,int y){
        rang.add(new node(0,x,y));
        for (int i = 0; i < getMap().parallelStream().filter(n-> n%2==0 ).collect(Collectors.toList()).size(); i++){
            ArrayList<node> voisins = getVoisin(rang.get(i));
            for (int v = 0; v < voisins.size(); v++){
                rang.add(voisins.get(v));
            }

        }
    }
    public ArrayList<node> getVoisin(node maison){
        ArrayList<node> voisin = new ArrayList<node>();
        if (Utile.toPixel(maison.getX() - 1) >= 0) {
            if (map.get(maison.getX() - 1 + Utile.toWidth(maison.getY())) % 2 == 0) {
                if (!estDejaLa(maison.getX() - 1,maison.getY()))
                    voisin.add(new node(maison.getDistance() + 1, maison.getX() - 1,maison.getY()));

            }
        }
        if (Utile.toPixel(maison.getX() + 1) < this.width) {
            if (map.get(maison.getX() + 1 + Utile.toWidth(maison.getY())) % 2 == 0) {
                if (!estDejaLa(maison.getX() + 1,maison.getY()))
                    voisin.add(new node(maison.getDistance() + 1, maison.getX() + 1,maison.getY()));

            }
        }
        if (Utile.toPixel(maison.getY() - 1) >= 0) {
            if (map.get(Utile.toWidth(maison.getY() - 1) + maison.getX()) % 2 == 0) {
                if (!estDejaLa(maison.getX(),maison.getY() - 1))
                    voisin.add(new node(maison.getDistance() + 1,maison.getX(), maison.getY() - 1));

            }
        }
        if (Utile.toPixel(maison.getY() + 1) < this.height) {
            if (map.get(Utile.toWidth(maison.getY() + 1) + maison.getX()) % 2 == 0) {
                if (!estDejaLa(maison.getX(),maison.getY() + 1))
                    voisin.add(new node(maison.getDistance() + 1,maison.getX(), maison.getY() + 1));

            }
        }
        return voisin;

    }

    public List<node> getRang() {
        return rang;
    }
    public node getUnNode(int x , int y){

        node result = null;
        for (int i = 0; i < rang.size(); i++){
            if (rang.get(i).getX() == x && rang.get(i).getY() == y)
                result = rang.get(i);
        }
        return result;
    }

    public boolean estDejaLa(int x,int y){
        boolean result = false;
        for (int i = 0; i < rang.size() ; i++){
            if (rang.get(i).getY() == y && rang.get(i).getX() == x)
                result = true;
        }
        return result;
    }

    public ObservableList<Missile> getProject() {
        return project;
    }

    public void addProject(Missile missile){
        project.add(missile);
    }

    public Acteur getActeur(String id) {
        for(Acteur a:this.acteurs){
            if(a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }


    public void unTour(){
        for (int a = 0; a < 20; a++) {
            for (int i = 0; i < this.project.size(); i++) {
                this.project.get(i).bouge();
                if (this.project.get(i).isExplosion()) {
                    this.project.remove(i);
                    i--;
                }
            }
        }

        /*for(int i = 0; i < this.tourelles.size(); i++){
            Tourelle tourelle = this.tourelles.get(i);
            tourelle.agit();
        }

        for(int i = 0; i < this.attaquants.size(); i++){
            Attaquants attaquant = this.attaquants.get(i);
            attaquant.seDeplacer();
        }*/

        for(int i = 0; i < this.acteurs.size(); i++){
            acteurs.get(i).agit();

            if(acteurs.get(i) instanceof Attaquant) {
                if (!((Attaquant) acteurs.get(i)).estVivant()) {
                    this.acteurs.remove(i);
                    i--;
                }
            }
        }

        this.nbTours++;
    }
}
