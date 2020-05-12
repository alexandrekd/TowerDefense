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
import sun.management.snmp.util.MibLogger;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controleur implements Initializable {
    private Timeline gameLoop;

    private int temps;

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
        initAnimation();
    }

    @FXML
    void poserTourelle(MouseEvent event) {
        Node source = (Node)event.getSource();
        int colIndex = (int) event.getX();
        int colRow = (int) event.getY();
        int x = colIndex/10;
        int y = colRow/10;
        if (env.getMap().get(y*55 + x) % 2 == 1) {
            Tourelle tourelle = new Tourelle(5, colIndex, colRow, 2, 150, this.env);
            this.env.getActeurs().add(tourelle);
            creerSprite(tourelle);
        }
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
        for (int i = 0 ; i < 50; i++) {
            Attaquant attaquant = new Attaquant(this.env, 20, 5);
            this.env.getActeurs().add(attaquant);
        }
    }

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
