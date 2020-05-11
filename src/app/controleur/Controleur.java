package app.controleur;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import app.modele.*;
import sun.management.snmp.util.MibLogger;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private TilePane map;

    @FXML
    private Pane plateau;

    @FXML
    private RadioButton btTourelleSimple;

    @FXML
    private Button btStart;

    @FXML
    private ToggleGroup Tour;

    @FXML
    private Button btLancer;

    @FXML
    private TextField tfNbTour;

    private Environnement env;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.env = new Environnement(550, 400);

        ListChangeListener<Acteur> listen= c->{
            while (c.next()) {
                for(Acteur acteur : c.getAddedSubList()) {
                    creerSprite(acteur);
                }

                for(Acteur acteur : c.getRemoved()) {
                    removeSprite(acteur.getId());
                }
            }
        };
        this.env.getActeurs().addListener(listen);

        ListChangeListener<Missile> listen2= c ->{
            while (c.next()) {
                for(Missile missile : c.getAddedSubList()) {
                    creerSpriteMissile(missile);
                }

                for(Missile missile : c.getRemoved()) {
                    removeSprite(missile.getId());
                }
            }
        };
        this.env.getProject().addListener(listen2);


        setmap();
    }

    @FXML
    void poserTourelle(MouseEvent event) {
        Node source = (Node)event.getSource();
        int colIndex = (int) event.getX();
        int colRow = (int) event.getY();

        Tourelle tourelle = new Tourelle(5, colIndex, colRow, 3, 500, this.env);
        this.env.getActeurs().add(tourelle);
        creerSprite(tourelle);
    }

    public void creerSprite(Acteur acteur){
        if(acteur instanceof Tourelle) {
            Circle c = new Circle(10);
            c.setId(acteur.getId());
            c.setFill(Color.RED);
            c.setTranslateX(acteur.getX());
            c.setTranslateY(acteur.getY());
            c.translateXProperty().bind(acteur.getXProperty());
            c.translateYProperty().bind(acteur.getYProperty());
            plateau.getChildren().add(c);
        }
        else if(acteur instanceof Attaquant){
            Circle c = new Circle(5);
            c.setId(acteur.getId());
            c.setFill(Color.BLUE);
            c.setTranslateX(acteur.getX());
            c.setTranslateY(acteur.getY());
            c.translateXProperty().bind(acteur.getXProperty());
            c.translateYProperty().bind(acteur.getYProperty());
            plateau.getChildren().add(c);
        }
    }

    public void creerSpriteMissile(Missile missile){
        Circle c = new Circle(2);
        c.setId(missile.getId());
        c.setFill(Color.GREEN);
        c.setTranslateX(missile.getX());
        c.setTranslateY(missile.getY());
        c.translateXProperty().bind(missile.getXProperty());
        c.translateYProperty().bind(missile.getYProperty());
        plateau.getChildren().add(c);
    }

    public void removeSprite(String id){
        plateau.getChildren().remove(plateau.lookup("#"+id));
    }

    @FXML
    void clickStart(MouseEvent event) {
        Attaquant attaquant = new Attaquant(this.env, 20, 5);
        this.env.getActeurs().add(attaquant);
    }

    @FXML
    void faireTours(MouseEvent event) {
        int nbtour;
        while(env.getProject().size() != 0 ){
            for (int i = 0 ; i < env.getProject().size() ; i++){
                env.getProject().get(i).bouge();
                if (env.getProject().get(i).isExplosion()){
                    env.getProject().remove(i);
                    i--;
                }
            }
        }
        try{
            nbtour = Integer.valueOf(tfNbTour.getText());
        }catch (Exception e){
            nbtour = 0;
        }

        for(int i = 0; i < nbtour; i++)
            this.env.unTour();

        /*update();*/
    }

    /*void update(){
        plateau.getChildren().clear();
        for(int i = 0; i < this.env.getActeurs().size(); i++){
            String id = "A" + i;
            creerSprite(this.env.getActeur(id));
        }
    }*/

    public void setmap() {
        for (int i = 0; i < env.getMap().size(); i++) {
            ImageView texture = new ImageView("resources/textures/" + env.getMap().get(i) + ".png");
            map.getChildren().add(texture);

        }
    }
}
