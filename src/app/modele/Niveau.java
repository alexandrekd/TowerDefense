package app.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Arrays;

public class Niveau {

    private String id;
    private static int compteur = 0;
    private Environnement env;
    private int vie;
    private IntegerProperty argentProperty;
    private ArrayList<Integer> map;
    private Vagues vagues;
    private int numVague;
    //private ArrayList<int> tourellesDispo;

    public Niveau(Environnement env){
        this.id = "Niv" + compteur++;
        this.env = env;
        this.numVague = 0;
        this.vie = 500;
        this.argentProperty = new SimpleIntegerProperty(20);
        this.map = new ArrayList<Integer>(Arrays.asList(101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                                                        101, 103, 101, 102, 102, 102, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 101, 101, 101,
                                                        101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                                                        101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 101, 102, 101, 101, 101,
                                                        101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                                                        101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 103, 103, 103, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                                                        900, 102, 102, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 101, 102, 101, 101, 101,
                                                        101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 102, 102, 104,
                                                        101, 101, 102, 102, 102, 102, 101, 103, 103, 103, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                                                        101, 101, 102, 101, 101, 101, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 103, 103, 103, 101, 102, 101, 103, 101, 101, 101, 101, 101,
                                                        101, 101, 102, 101, 103, 103, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                                                        101, 101, 102, 101, 101, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                                                        101, 101, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 101, 101,
                                                        101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 103, 103, 103, 103, 101, 101, 101, 101, 101, 101, 101, 101, 101, 103, 101, 101, 101, 101, 101,
                                                        101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                                                        101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101)
        );

        this.vagues = new Vagues(this.env);
        this.vagues.getVagues().add(this.vagues.creerVague(5));
        this.vagues.getVagues().add(this.vagues.creerVague(5));
    }

    public Vagues getVagues(){
        return this.vagues;
    }

    public void incrementerArgent(int argent){
        setArgent(this.getArgent() + argent);
        System.out.println("\nTu a gagné " + argent + "€\nTu as " + this.getArgent() + "€");
    }

    public ArrayList<Integer> getMap() {
        return map;
    }

    public IntegerProperty getArgentProperty() {
        return argentProperty;
    }
    public int getArgent(){
        return this.argentProperty.getValue();
    }
    public void setArgent(int v){
        this.argentProperty.setValue(v);
    }

    public int getVie() {
        return vie;
    }

    public String getId() {
        return id;
    }

}
