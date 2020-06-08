package app.modele;

import app.modele.Professeur.Mur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;

public class Environnement {
    private int width, height;
    private ObservableList<Acteur> acteurs;
    private static List<Integer> map;
    private ObservableList<Missile> project;
    private int nbTours;
    private List<node> rang;
    private ObservableList<Zone> zone;
    private ArrayList<Effets> effects;
    private boolean vagueEnCours;
    private Niveau niveau;
    private IntegerProperty numVagueProperty;
    private int xFin, yFin;

    public Environnement(int width, int height){
        this.vagueEnCours = false;
        this.acteurs = FXCollections.observableArrayList();
        this.width = width;
        this.height = height;
        this.zone = FXCollections.observableArrayList();
        this.project = FXCollections.observableArrayList();
        this.effects = new ArrayList<Effets>();
        this.rang = new ArrayList<node>();

        this.niveau = new Niveau(this);
        this.numVagueProperty = new SimpleIntegerProperty(0);

        int i;
        for(i = 0; i < this.map.size() && this.map.get(i) != 104; i++){}
        this.xFin = Utile.toX(i);
        this.yFin = Utile.toY(i);
    }

    public static void setMap(ArrayList<Integer> list){
        map = list;
    }

    public ArrayList<Effets> getEffects() {
        return effects;
    }

    public static List<Integer> getMap(){
        return map;
    }

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

    public Niveau getNiveau(){
        return this.niveau;
    }

    public int getNbTours(){
        return this.nbTours;
    }

    public int getNumVague(){
        return this.numVagueProperty.getValue();
    }
    public IntegerProperty getNumVagueProperty(){
        return this.numVagueProperty;
    }
    public void setNumVague (int v){
        this.numVagueProperty.set(v);
    }

    public ObservableList<Acteur> getActeurs(){
        return this.acteurs;
    }

    // Cette fonction permet tout simplement de renvoyer la tuile d'arrivé
    public node getArrivé() {
        for (node tuile : rang) {
            if (tuile.getDistance() == 0) {
                return tuile;
            }
        }
        return rang.get(0); //Cette partie la ne sert à rien, mais on est obligé d'avoir un autre return en dehors de la boucle
    }

    public void faireRang(){
        rang.add(new node(0,xFin,yFin));
        for (int i = 0; i < getMap().parallelStream().filter(n-> n%2==0 ).collect(Collectors.toList()).size(); i++){
            ArrayList<node> voisins = getVoisin(rang.get(i));
            for (int v = 0; v < voisins.size(); v++)
                rang.add(voisins.get(v));
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

    public static int[] getMilieuChemin(Attaquant attaquant){
        int[] coordoneesMilieu = new int[2];

        node node = null;
        for (int i = 0; i < attaquant.getEnv().getRang().size(); i++){
            if (Utile.toTexture(attaquant.getEnv().getRang().get(i).getX()) <= Utile.toTexture(attaquant.getX())+2 && Utile.toTexture(attaquant.getEnv().getRang().get(i).getX()) >= Utile.toTexture(attaquant.getX())-2 &&
                    Utile.toTexture(attaquant.getEnv().getRang().get(i).getY()) <= Utile.toTexture(attaquant.getY())+2 && Utile.toTexture(attaquant.getEnv().getRang().get(i).getY()) >= Utile.toTexture(attaquant.getY())-2)
                node = attaquant.getEnv().getRang().get(i);
        }
        System.out.println("node " + node);
        coordoneesMilieu[0] = Utile.toPixel(node.getX()) + 25;
        coordoneesMilieu[1] = Utile.toPixel(node.getX()) + 25;

        return coordoneesMilieu;
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

    public ObservableList<Zone> getZone() {
        return zone;
    }


    public void unTour(){
        gererProj();
        gererEffet();
        // Si une vague est en cours, va chercher un ennemi a ajouter
        ajouterEnnemi();
        gererActeur();

        this.nbTours++;
    }

    private void gererProj(){
        for (int a = 0; a < 15; a++) {
            for (int i = 0; i < this.project.size(); i++) {
                this.project.get(i).bouge();
                if (this.project.get(i).isExplosion()) {
                    this.project.remove(i);
                    i--;
                }
            }
        }
    }

    private void gererEffet(){
        for (int i = 0; i < effects.size();i++){
            effects.get(i).agit();
            if (!(effects.get(i).estVivant())){
                this.effects.remove(effects.get(i));
            }
        }
    }

    private void ajouterEnnemi(){
        if (this.vagueEnCours && this.nbTours%15 == 0)
            this.acteurs.add(this.niveau.getVagues().fetchEnnemi());
    }

    private void gererActeur(){
        for(int i = 0; i < this.acteurs.size(); i++){
            this.acteurs.get(i).agit();

            if(acteurs.get(i) instanceof Attaquant) {
                if (!((Attaquant) acteurs.get(i)).estVivant()) {
                    this.acteurs.remove(i);
                    this.niveau.incrementerArgent(5);
                    i--;
                }
                else
                    niveau.ennemiAttaqueJoueur(((Attaquant) acteurs.get(i)));
            }
            else if(this.acteurs.get(i) instanceof Mur) {
                if (!((Mur) acteurs.get(i)).estVivant()) {
                    this.acteurs.remove(i);
                    i--;
                }
            }
        }
    }


    public void zoneMorte(Zone zone){
        while (zone.getActeursDansLaZone().size() != 0){
            zone.getActeursDansLaZone().remove(0);
        }
        this.zone.remove(zone);
    }

    public void startVague(){
        this.vagueEnCours = true;
        setNumVague(this.getNumVague()+1);
    }
    public void stopVague(){
        this.vagueEnCours = false;
    }

    public ArrayList<Attaquant> getAttaquantsInActeurs(){
        ArrayList<Attaquant> attaquants = new ArrayList<>();
        for(int i = 0; i < this.getActeurs().size(); i++)
            if (this.getActeurs().get(i) instanceof Attaquant)
                attaquants.add((Attaquant) this.getActeurs().get(i));
        return attaquants;
    }

}
