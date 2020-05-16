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

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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

    private ArrayList<ImageView> imageList;
    private ArrayList<ImageView> checkList;
    private ArrayList<BooleanProperty> checkedList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.env = new Environnement(1600, 800);
        imageList = new ArrayList<ImageView>(Arrays.asList(img1,img2,img3,img4,img5,img6,img7,img8));
        checkList = new ArrayList<ImageView>(Arrays.asList(imgCheck1,imgCheck2,imgCheck3,imgCheck4,imgCheck5,imgCheck6,imgCheck7,imgCheck8));
        checkedList = new ArrayList<BooleanProperty>(Arrays.asList(img1checked,img2checked,img3checked,img4checked,img5checked,img6checked,img7checked,img8checked));

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
    }

    @FXML
    void poserTourelle(MouseEvent event) {


        Node source = (Node)event.getSource();
        int colIndex = (int) event.getX();
        int colRow = (int) event.getY();
        if ((env.getMap().get(Utile.toWidth(Utile.toTexture(colRow)) + Utile.toTexture(colIndex)) )% 2 == 1) {
            String choix = select();
            if (choix == "Bonnot") {
                Acteur tourelle = new Bonnot(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if (choix == "Bossard"){
                Acteur tourelle = new Bossard(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if (choix == "Comparot"){
                Acteur tourelle = new Comparot(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(choix == "Ricordo"){
                Acteur tourelle = new Ricordo(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(choix == "Lamolle"){
                Acteur tourelle = new Lamolle(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(choix == "Homps"){
                Acteur tourelle = new Homps(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(choix == "Rety"){
                Acteur tourelle = new Rety(colIndex, colRow, this.env);
                add(tourelle);
            }
            else if(choix == "Simonot"){
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
    @FXML
    void clickChoix(MouseEvent event) {
        int nombre = (int) (event.getY()/75);
        System.out.println(nombre);
        System.out.println(imageList.get(0).getY());
        System.out.println(this.imageList.get(nombre).getY());
        this.imageList.get(nombre).scaleXProperty().bindBidirectional(this.imageList.get(nombre).scaleYProperty());
        this.checkList.get(nombre).visibleProperty().bind(this.checkedList.get(nombre));
        this.checkedList.get(nombre).bind(this.imageList.get(nombre).scaleXProperty().isNotEqualTo(1));

        this.imageList.get(nombre).setOnMouseClicked(e -> {
            reset(imageList.get(nombre));
            if (this.checkedList.get(nombre).get())
                this.imageList.get(nombre).setScaleX(1);
            else
                this.imageList.get(nombre).setScaleX(0.8);
        });



    }

    public void reset(ImageView Actuel){
        for (int i = 0; i < this.imageList.size() ; i++){
            if (this.imageList.get(i) != Actuel)
                this.imageList.get(i).setScaleX(1);
        }
    }

    public String select(){
        String choix = "";
        if (this.img1.getScaleX() == 0.8)
            choix = "Bonnot";
        else if (this.img2.getScaleX() == 0.8)
            choix = "Rety";
        else if (this.img3.getScaleX() == 0.8)
            choix = "Comparot";
        else if (this.img4.getScaleX() == 0.8)
            choix = "Ricordo";
        else if (this.img5.getScaleX() == 0.8)
            choix = "Lamolle";
        else if (this.img6.getScaleX() == 0.8)
            choix = "Homps";
        else if (this.img7.getScaleX() == 0.8)
            choix = "Bossard";
        else if (this.img8.getScaleX() == 0.8)
            choix = "Simonot";

        System.out.println(choix);
        return choix;
    }
}
