package app.modele;

import app.modele.TypeMissile.EffetsZone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Zone{
    private ObservableList<Acteur> acteursDansLaZone;
    private int taille;
    private String couleur;
    private String id;
    private IntegerProperty xProperty,yProperty;
    private Environnement env;

    public Zone(int taille,String Couleur,int x,int y,Environnement env,String id){
        this.taille = taille;
        this.couleur = Couleur;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = id;
        this.env = env;
        acteursDansLaZone = FXCollections.observableArrayList();
    }


    public void agit(){
        quiEstDansLaZone();
    }

    public void quiEstDansLaZone(){
        for (int i = 0;i < env.getActeurs().size() ; i++)
            if (!dansLaListe(env.getActeurs().get(i)))
                if (env.getActeurs().get(i).getX() <= this.taille + getX() && env.getActeurs().get(i).getX() >= getX() - this.taille && env.getActeurs().get(i).getY() <= this.taille + getY() && env.getActeurs().get(i).getY() >= getY() - this.taille)
                    if (env.getActeurs().get(i) instanceof Attaquant)
                        acteursDansLaZone.add(env.getActeurs().get(i));

        for (int i = 0 ; i < acteursDansLaZone.size() ; i++)
            if ((!(acteursDansLaZone.get(i).getX() <= this.taille + getX() && acteursDansLaZone.get(i).getX() >= getX() - this.taille && acteursDansLaZone.get(i).getY() <= this.taille + getY() && acteursDansLaZone.get(i).getY() >= getY() - this.taille))||(!acteurEstDansLaZone(acteursDansLaZone.get(i)))) {
                acteursDansLaZone.remove(acteursDansLaZone.get(i));
                i--;
            }

    }

    public ArrayList<Attaquant> getListeAttanquants(){
        ArrayList<Attaquant> list = new ArrayList<Attaquant>();
        for (int i = 0; i < acteursDansLaZone.size();i++)
                list.add((Attaquant) acteursDansLaZone.get(i));
        return list;
    }

    public boolean dansLaListe(Acteur acteur){
        for (int i = 0; i < acteursDansLaZone.size();i++)
            if (acteursDansLaZone.get(i) == acteur)
                return true;
        return false;
    }

    public EffetsZone trouveTonAmeSoeur(){
        EffetsZone effets = null;
        for (int i = 0 ;i < env.getEffects().size(); i++)
            if (this.id.equals(env.getEffects().get(i).getId()))
                effets = env.getEffects().get(i);
            return effets;
    }

    public boolean acteurEstDansLaZone(Acteur acteur){
        boolean result = false;
        for (int i = 0; i < env.getActeurs().size(); i++)
            if (env.getActeurs().get(i) == acteur)
                result = true;
        return result;
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


    public  int getX() {
        return xProperty.getValue();
    }
    public  void setX(int n){
        xProperty.setValue(n);
    }
    public final IntegerProperty getXProperty(){ return xProperty; }

    public  int getY() {
        return yProperty.getValue();
    }
    public  void setY(int n){
        yProperty.setValue(n);
    }
    public final IntegerProperty getYProperty(){ return yProperty; }
}
