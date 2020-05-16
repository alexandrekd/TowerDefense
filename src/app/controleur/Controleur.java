package app.controleur;

import app.modele.Professeur.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import app.modele.*;
import javafx.util.Duration;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.net.URL;
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

    @FXML
    private ImageView img1;

    @FXML
    private ImageView imgCheck1;
    private BooleanProperty img1checked = new SimpleBooleanProperty();

    @FXML
    private ImageView img2;
    private BooleanProperty img2checked = new SimpleBooleanProperty();

    @FXML
    private ImageView imgCheck2;


    @FXML
    private ImageView img3;

    @FXML
    private ImageView imgCheck3;
    private BooleanProperty img3checked = new SimpleBooleanProperty();

    @FXML
    private ImageView img4;

    @FXML
    private ImageView imgCheck4;
    private BooleanProperty img4checked = new SimpleBooleanProperty();

    @FXML
    private ImageView img5;

    @FXML
    private ImageView imgCheck5;
    private BooleanProperty img5checked = new SimpleBooleanProperty();

    @FXML
    private ImageView img6;

    @FXML
    private ImageView imgCheck6;
    private BooleanProperty img6checked = new SimpleBooleanProperty();

    @FXML
    private ImageView img7;

    @FXML
    private ImageView imgCheck7;
    private BooleanProperty img7checked = new SimpleBooleanProperty();

    @FXML
    private ImageView img8;

    @FXML
    private ImageView imgCheck8;
    private BooleanProperty img8checked = new SimpleBooleanProperty();



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
        env.faireRang(31,7);
        menu();
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

            ImageView c = new ImageView("resources/skins/"+acteur.getCouleur()+".png");
            c.setId(acteur.getId());
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
                Duration.seconds(0.07),    //  0.017
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

    // NE PAS MONTRER A SIMONOT
    // QUESTION DE VIE OU DE MORT
    // SVPPPPPPPPP
    // J AI PAS EU LE TEMPS DE FINIR
    public void menu(){
        this.img1.scaleXProperty().bindBidirectional(this.img1.scaleYProperty());
        this.imgCheck1.visibleProperty().bind(this.img1checked);
        this.img1checked.bind(this.img1.scaleXProperty().isNotEqualTo(1));

        this.img1.setOnMouseClicked(e -> {
            reset();
            if (this.img1checked.get())
                this.img1.setScaleX(1);
            else
                this.img1.setScaleX(0.8);
        });

        this.img2.scaleXProperty().bindBidirectional(this.img2.scaleYProperty());
        this.imgCheck2.visibleProperty().bind(this.img2checked);
        this.img2checked.bind(this.img2.scaleXProperty().isNotEqualTo(1));

        this.img2.setOnMouseClicked(e -> {
            reset();
            if (this.img2checked.get())
                this.img2.setScaleX(1);
            else
                this.img2.setScaleX(0.8);
        });

        this.img3.scaleXProperty().bindBidirectional(this.img3.scaleYProperty());
        this.imgCheck3.visibleProperty().bind(this.img3checked);
        this.img3checked.bind(this.img3.scaleXProperty().isNotEqualTo(1));

        this.img3.setOnMouseClicked(e -> {
            reset();
            if (this.img3checked.get())
                this.img3.setScaleX(1);
            else
                this.img3.setScaleX(0.8);
        });

        this.img4.scaleXProperty().bindBidirectional(this.img4.scaleYProperty());
        this.imgCheck4.visibleProperty().bind(this.img4checked);
        this.img4checked.bind(this.img4.scaleXProperty().isNotEqualTo(1));

        this.img4.setOnMouseClicked(e -> {
            reset();
            if (this.img4checked.get())
                this.img4.setScaleX(1);
            else
                this.img4.setScaleX(0.8);
        });

        this.img5.scaleXProperty().bindBidirectional(this.img5.scaleYProperty());
        this.imgCheck5.visibleProperty().bind(this.img5checked);
        this.img5checked.bind(this.img5.scaleXProperty().isNotEqualTo(1));

        this.img5.setOnMouseClicked(e -> {
            reset();
            if (this.img5checked.get())
                this.img5.setScaleX(1);
            else
                this.img5.setScaleX(0.8);
        });
        this.img6.scaleXProperty().bindBidirectional(this.img6.scaleYProperty());
        this.imgCheck6.visibleProperty().bind(this.img6checked);
        this.img6checked.bind(this.img6.scaleXProperty().isNotEqualTo(1));

        this.img6.setOnMouseClicked(e -> {
            reset();
            if (this.img6checked.get())
                this.img6.setScaleX(1);
            else
                this.img6.setScaleX(0.8);
        });
        this.img7.scaleXProperty().bindBidirectional(this.img7.scaleYProperty());
        this.imgCheck7.visibleProperty().bind(this.img7checked);
        this.img7checked.bind(this.img7.scaleXProperty().isNotEqualTo(1));

        this.img7.setOnMouseClicked(e -> {
            reset();
            if (this.img7checked.get())
                this.img7.setScaleX(1);
            else
                this.img7.setScaleX(0.8);
        });
        this.img8.scaleXProperty().bindBidirectional(this.img8.scaleYProperty());
        this.imgCheck8.visibleProperty().bind(this.img8checked);
        this.img8checked.bind(this.img8.scaleXProperty().isNotEqualTo(1));

        this.img8.setOnMouseClicked(e -> {
            reset();
            if (this.img8checked.get())
                this.img8.setScaleX(1);
            else
                this.img8.setScaleX(0.8);
        });
    }

    public void reset(){
        this.img2.setScaleX(1);
        this.img1.setScaleX(1);
        this.img3.setScaleX(1);
        this.img4.setScaleX(1);
        this.img5.setScaleX(1);
        this.img6.setScaleX(1);
        this.img7.setScaleX(1);
        this.img8.setScaleX(1);
    }
}
