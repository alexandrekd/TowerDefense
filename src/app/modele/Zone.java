package app.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Zone{
    private ObservableList<Acteur> acteursDansLaZone;
    private int taille;
    private String couleur;
    private String id;
    private int x;
    private int y;
    private Environnement env;
    public Zone(int taille,String Couleur,int x,int y,Environnement env,String id){
        this.taille = taille;
        this.couleur = Couleur;
        this.x = x;
        this.y = y;
        this.id = id;
        this.env = env;
        acteursDansLaZone = FXCollections.observableArrayList();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void agit(){
        quiEstDansLaZone();
    }

    public void quiEstDansLaZone(){
        for (int i = 0;i < env.getActeurs().size() ; i++){
            if (dansLaListe(env.getActeurs().get(i))) {
                if (!(env.getActeurs().get(i).getX() <= this.taille + x && env.getActeurs().get(i).getX() >= x - this.taille && env.getActeurs().get(i).getY() <= this.taille + y && env.getActeurs().get(i).getY() >= y - this.taille)) {
                    acteursDansLaZone.remove(env.getActeurs().get(i));
                    i--;
                }
            }
            else{
                if(env.getActeurs().get(i).getX() <= this.taille+x && env.getActeurs().get(i).getX() >= x-this.taille && env.getActeurs().get(i).getY() <= this.taille+y && env.getActeurs().get(i).getY() >= y-this.taille)
                    if (env.getActeurs().get(i) instanceof Attaquant) {
                        acteursDansLaZone.add(env.getActeurs().get(i));
                    }
            }
        }
    }

    public ArrayList<Attaquant> getListeAttanquants(){
        ArrayList<Attaquant> list = new ArrayList<Attaquant>();
        for (int i = 0; i < acteursDansLaZone.size();i++){
                list.add((Attaquant) acteursDansLaZone.get(i));
        }
        return list;
    }

    public boolean dansLaListe(Acteur acteur){
        for (int i = 0; i < acteursDansLaZone.size();i++){
            if (acteursDansLaZone.get(i) == acteur){
                return true;
            }
        }
        return false;
    }

    public Effets trouveTonAmeSoeur(){
        Effets effets = null;
        for (int i = 0 ;i < env.getEffects().size(); i++)
            if (this.id == env.getEffects().get(i).getId())
                effets = env.getEffects().get(i);
            return effets;
    }



    public String getId() {
        return id;
    }

    public String getCouleur() {
        return couleur;
    }

    public int getTaille() {
        return taille;
    }

    public ObservableList<Acteur> getActeursDansLaZone() {
        return acteursDansLaZone;
    }

}
