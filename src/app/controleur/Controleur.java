package app.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import jdk.nashorn.internal.ir.WhileNode;
import sun.management.snmp.util.MibLogger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controleur implements Initializable {
    private Timeline gameLoop;

    private int temps;

    @FXML
    private RadioButton Bonnot;

    @FXML
    private ToggleGroup Tour;

    @FXML
    private RadioButton Bossard;

    @FXML
    private RadioButton Comparot;

    @FXML
    private RadioButton Ricordo;

    @FXML
    private RadioButton Lamolle;

    @FXML
    private RadioButton Homps;

    @FXML
    private RadioButton Rety;

    @FXML
    private RadioButton Simonot;

    @FXML
    private TilePane map;

    @FXML
    private Pane plateau;

    @FXML
    private RadioButton btTourelleSimple;

    @FXML
    private Button btStart;

    @FXML
    private Button btLancer;

    @FXML
    private TextField tfNbTour;

    private Environnement env;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.env = new Environnement(1600, 800);

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
        initAnimation();
        env.faireRang(10,4);


    }

    @FXML
    void poserTourelle(MouseEvent event) {


        Node source = (Node)event.getSource();
        int colIndex = (int) event.getX();
        int colRow = (int) event.getY();
        if ((env.getMap().get(Utile.toWidth(Utile.toTexture(colRow)) + Utile.toTexture(colIndex)) )% 2 == 1) {
            if (Bonnot.isSelected()) {
                Acteur tourelle = new Bonnot(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if (Bossard.isSelected()){
                Acteur tourelle = new Bossard(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if (Comparot.isSelected()){
                Acteur tourelle = new Comparot(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(Ricordo.isSelected()){
                Acteur tourelle = new Ricordo(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(Lamolle.isSelected()){
                Acteur tourelle = new Lamolle(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(Homps.isSelected()){
                Acteur tourelle = new Homps(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(Rety.isSelected()){
                Acteur tourelle = new Rety(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(Simonot.isSelected()){
                Acteur tourelle = new Simonot(colIndex, colRow, this.env);
                add(tourelle);
            }
        }
    }
     public void add(Acteur tourelle){
         this.env.getActeurs().add(tourelle);
         creerSprite(tourelle);
     }

    public void creerSprite(Acteur acteur){
        if(acteur instanceof Tourelle) {
            Circle c = new Circle(10);
            c.setId(acteur.getId());
            c.setFill(Color.valueOf(acteur.getCouleur()));
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

        ImageView project = new ImageView("resources/missiles/"+missile.getDebActeur().getTypeMissile()+".png");
        project.setId(missile.getId());
        project.setTranslateX(missile.getX());
        project.setTranslateY(missile.getY());
        project.translateXProperty().bind(missile.getXProperty());
        project.translateYProperty().bind(missile.getYProperty());
        plateau.getChildren().add(project);
    }

    public void removeSprite(String id){
        plateau.getChildren().remove(plateau.lookup("#"+id));
    }

    @FXML
    void clickStart(MouseEvent event) {
        int random = (int) (Math.random() * env.getMap().parallelStream().filter(n-> n/900 == 1).collect(Collectors.toList()).size());
        int x,y,count = 0;
        for (int i = 0 ; i < env.getMap().size(); i++){
            if(env.getMap().get(i)/900 ==1){
                if (count == random){
                    Acteur attaquant = new Normal(env,Utile.toX(i),Utile.toPixel(Utile.toY(i)) + (int) (Math.random()*50));
                    this.env.getActeurs().add(attaquant);
                    count = 0;
                }
                else
                    count++;
            }
        }
    }
//env.getActeurs().parallelStream().filter(n -> n instanceof Attaquant).collect(Collectors.toList()).size() == 0
    @FXML
    void faireTours() {
        gameLoop.play();
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

    private void initAnimation(){
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (event -> {
                    if(env.getActeurs().parallelStream().filter(n -> n instanceof Attaquant).collect(Collectors.toList()).size() == 0){
                        while (env.getProject().size() != 0){
                            env.getProject().remove(0);
                        }
                        gameLoop.stop();
                    }
                    else if (temps%5==0){
                        this.env.unTour();
                    }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}
