package app.controleur;

import app.modele.Eleve.Mateo;
import app.modele.Eleve.Normal;
import app.modele.Eleve.Theo;
import app.modele.Professeur.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import app.modele.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Timeline gameLoop;

    @FXML
    private TilePane map;

    @FXML
    private Pane plateau;

    @FXML
    private Pane paneActeur;


    @FXML
    private RadioButton btTourelleSimple;

    @FXML
    private Button btStart;

    @FXML
    private Button btLancer;

    @FXML
    private TextField tfNbTour;

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

    @FXML
    private Label argent;

    @FXML
    private Label round;

    @FXML
    private Label vie;

    @FXML
    private Label finLabel;

    private ArrayList<ImageView> imageList;
    private ArrayList<ImageView> checkList;
    private ArrayList<BooleanProperty> checkedList;
    private HashMap<String, String> skins;
    private Environnement env;
    private int temps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.env = new Environnement(1600, 800);
        imageList = new ArrayList<ImageView>(Arrays.asList(img1,img2,img3,img4,img5,img6,img7,img8));
        checkList = new ArrayList<ImageView>(Arrays.asList(imgCheck1,imgCheck2,imgCheck3,imgCheck4,imgCheck5,imgCheck6,imgCheck7,imgCheck8));
        checkedList = new ArrayList<BooleanProperty>(Arrays.asList(img1checked,img2checked,img3checked,img4checked,img5checked,img6checked,img7checked,img8checked));

        this.argent.textProperty().bind(this.env.getNiveau().getArgentProperty().asString());
        this.round.textProperty().bind(this.env.getNumVagueProperty().asString());
        this.vie.textProperty().bind(this.env.getNiveau().getVieProperty().asString());

        skins = new HashMap<String, String>();
            skins.put("Bonnot" , "resources/skins/1.png");
            skins.put("Ricordo" , "resources/skins/1.png");
            skins.put("Bossard" , "resources/skins/1.png");
            skins.put("Homps" , "resources/skins/6.png");
            skins.put("Lamolle" , "resources/skins/1.png");
            skins.put("Rety" , "resources/skins/2.png");
            skins.put("Comparot" , "resources/skins/3.png");
            skins.put("Simonot" , "resources/skins/8.png");

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

        ListChangeListener<Zone> listen3= c->{
            while (c.next()) {
                for(Zone zone : c.getAddedSubList()) {
                    creerCercle(zone);
                }

                for(Zone zone : c.getRemoved()) {
                    removeSprite(zone.getId());
                }
            }
        };
        this.env.getZone().addListener(listen3);


        setmap();
        initAnimation();
        env.faireRang();
    }

    public void afficher(){
        for (int i = 0; i < plateau.getChildren().size();i++)
        System.out.println(plateau.getChildren().get(i).getOpacity());
    }

    @FXML
    void poserTourelle(MouseEvent event) {
        //Node source = (Node)event.getSource();
        int colIndex = (int) event.getX()-25;
        int colRow = (int) event.getY()-30;
        if ((env.getMap().get(Utile.toWidth(Utile.toTexture(colRow+30)) + Utile.toTexture(colIndex+25)) )% 2 == 1) {
            String choix = select();

            Tourelle bonnot = new Bonnot(colIndex, colRow, this.env);
            Tourelle bossard = new Bossard(colIndex, colRow, this.env);
            Tourelle comparot = new Comparot(colIndex, colRow, this.env);
            Tourelle ricordo = new Ricordo(colIndex, colRow, this.env);
            Tourelle lamolle = new Lamolle(colIndex, colRow, this.env);
            Tourelle homps = new Homps(colIndex, colRow, this.env);
            Tourelle rety = new Rety(colIndex, colRow, this.env);
            Tourelle simonot = new Simonot(colIndex, colRow, this.env);

            if (choix.equals("Bonnot") && this.env.getNiveau().getArgent() >= bonnot.getPrix()) {
                add(bonnot);
                this.env.getNiveau().incrementerArgent(-bonnot.getPrix());
            }
            else if (choix.equals("Bossard") && this.env.getNiveau().getArgent() >= bossard.getPrix()){
                add(bossard);
                this.env.getNiveau().incrementerArgent(-bossard.getPrix());
            }
            else if (choix.equals("Comparot") && this.env.getNiveau().getArgent() >= comparot.getPrix()){
                add(comparot);
                this.env.getNiveau().incrementerArgent(-comparot.getPrix());
            }
            else if(choix.equals("Ricordo") && this.env.getNiveau().getArgent() >= ricordo.getPrix()){
                add(ricordo);
                this.env.getNiveau().incrementerArgent(-ricordo.getPrix());
            }
            else if(choix.equals("Lamolle") && this.env.getNiveau().getArgent() >= lamolle.getPrix()){
                add(lamolle);
                this.env.getNiveau().incrementerArgent(-lamolle.getPrix());
            }
            else if(choix.equals("Homps") && this.env.getNiveau().getArgent() >= homps.getPrix()){
                add(homps);
                this.env.getNiveau().incrementerArgent(-homps.getPrix());
            }
            else if(choix.equals("Rety") && this.env.getNiveau().getArgent() >= rety.getPrix()){
                add(rety);
                this.env.getNiveau().incrementerArgent(-rety.getPrix());
            }
            else if(choix.equals("Simonot") && this.env.getNiveau().getArgent() >= simonot.getPrix()){
                add(simonot);
                this.env.getNiveau().incrementerArgent(-simonot.getPrix());
            }
        }
    }

     public void add(Acteur tourelle){
         this.env.getActeurs().add(tourelle);
         creerSprite(tourelle);
     }

    public void creerSprite(Acteur acteur){
        if(acteur instanceof Tourelle) {
            ImageView c = new ImageView(skins.get(acteur.getName()));
            c.setId(acteur.getId());
            c.translateXProperty().bind(acteur.getXProperty());
            c.translateYProperty().bind(acteur.getYProperty());
            paneActeur.getChildren().add(c);
        }
        else if(acteur instanceof Normal){
            Circle c = new Circle(5);
            c.setId(acteur.getId());
            c.setFill(Color.BLUE);
            c.translateXProperty().bind(acteur.getXProperty());
            c.translateYProperty().bind(acteur.getYProperty());
            paneActeur.getChildren().add(c);
        }
        else if(acteur instanceof Theo){
            Circle c = new Circle(5);
            c.setId(acteur.getId());
            c.setFill(Color.RED);
            c.translateXProperty().bind(acteur.getXProperty());
            c.translateYProperty().bind(acteur.getYProperty());
            paneActeur.getChildren().add(c);
        }
        else if(acteur instanceof Mateo){
            Circle c = new Circle(10);
            c.setId(acteur.getId());
            c.setFill(Color.GREEN);
            c.translateXProperty().bind(acteur.getXProperty());
            c.translateYProperty().bind(acteur.getYProperty());
            paneActeur.getChildren().add(c);
        }
        else if (acteur instanceof Mur){
            ImageView c = new ImageView(skins.get(acteur.getName()));
            c.setId(acteur.getId());
            c.translateXProperty().bind(acteur.getXProperty());
            c.translateYProperty().bind(acteur.getYProperty());
            paneActeur.getChildren().add(c);
        }
    }

    public void creerCercle(Zone zone) {
        Circle c = new Circle(zone.getTaille());
        c.setId(zone.getId());
        c.setFill(Color.valueOf(zone.getCouleur()));
        c.setTranslateX(zone.getX());
        c.setTranslateY(zone.getY());
        System.out.println(zone.trouveTonAmeSoeur());
        c.opacityProperty().bind(zone.trouveTonAmeSoeur().vieProperty());
        paneActeur.getChildren().add(c);
    }


    public void creerSpriteMissile(Missile missile){

        ImageView project = new ImageView("resources/missiles/" +missile.getDebActeur().getTypeMissile()+".png");
        project.setId(missile.getId());
        project.setTranslateX(missile.getX());
        project.setTranslateY(missile.getY());
        project.translateXProperty().bind(missile.getXProperty());
        project.translateYProperty().bind(missile.getYProperty());
        paneActeur.getChildren().add(project);
    }

    public void removeSprite(String id){
        paneActeur.getChildren().remove(paneActeur.lookup("#"+id));
    }

    @FXML
    void clickStart(MouseEvent event) {
        if (this.env.getNiveau().getVagues().getVagues().size() > 0) {
            this.env.startVague();
            gameLoop.play();
        }
    }

    @FXML
    void faireTours() {
        gameLoop.play();
        gameOver();
        gameWin();
    }

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
                Duration.seconds(0.07),
                (event -> {
                    if((this.env.getNiveau().getVagues().getVagues().size() == 0 && this.env.getAttaquantsInActeurs().size() == 0) || this.env.getNiveau().getVie() <= 0){       // on stop la boucle s'il n'y a plus de vagues dans le niveau et s'il n'y a plus d'ennemi sur le terrain
                        while (env.getProject().size() != 0){
                            env.getProject().remove(0);
                        }
                        gameLoop.stop();
                        this.finLabel.setOpacity(1);
                    }
                    else if (temps%5==0){
                        this.env.unTour();
                    }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    void changerScene(MouseEvent event) {
        if (this.finLabel.getOpacity() == 1) {
            if (this.env.getNiveau().getVie() <= 0) {
                try {
                    BorderPane root = FXMLLoader.load(getClass().getResource("../vue/gameOver.fxml"));
                    Scene scene = new Scene(root, 1920, 1080);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (this.env.getNiveau().getVie() > 0) {
                try {
                    BorderPane root = FXMLLoader.load(getClass().getResource("../vue/gameWin.fxml"));
                    Scene scene = new Scene(root, 1920, 1080);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void estDessus(MouseEvent event) {
        this.finLabel.setScaleX(1.1);
        this.finLabel.setScaleY(1.1);
    }

    @FXML
    void estPasDessus(MouseEvent event) {
        this.finLabel.setScaleX(1);
        this.finLabel.setScaleY(1);
    }


    @FXML
    void clickChoix(MouseEvent event) {
        int nombre = (int) (event.getY()/80);
        System.out.println(nombre);

        try {
            this.imageList.get(nombre).setOnMouseClicked(e -> {
                reset(imageList.get(nombre));
                if (checkList.get(nombre).visibleProperty().get()) {
                    this.imageList.get(nombre).setScaleX(1);
                    this.imageList.get(nombre).setScaleY(1);
                    this.checkList.get(nombre).visibleProperty().setValue(false);
                } else {
                    this.imageList.get(nombre).setScaleX(0.8);
                    this.imageList.get(nombre).setScaleY(0.8);
                    this.checkList.get(nombre).visibleProperty().setValue(true);
                }
            });
        } catch (Exception e){
            System.out.println("La selection n'a pas marchee; nombre = " + nombre);
        }
    }

    public void reset(ImageView actuel){
        for (int i = 0; i < this.imageList.size() ; i++) {
            if (this.imageList.get(i)!= actuel) {
                this.imageList.get(i).setScaleX(1);
                this.imageList.get(i).setScaleY(1);
                this.checkList.get(i).visibleProperty().setValue(false);
            }
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
        return choix;
    }

    public void gameOver() {

        if(this.env.getNiveau().joueurVivant() == false){ //On fait appel ici à la méthode joueurVivant() pour savoir si le joueur est encore vivant, si ça renvoie non, alors le joueur a perdu et on affiche la scène de défaite
            try {
                BorderPane root = FXMLLoader.load(getClass().getResource("../vue/gameOver.fxml"));
                Scene scene = new Scene(root,1920,1080);
                Stage window = (Stage)(map.getScene().getWindow());
                window.setScene(scene);
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void gameWin() {

        if(this.env.getNiveau().joueurAvoirGagne() == true){ //On fait appel ici à la méthode joueurAvoirGagne() pour savoir si le joueur a gagné. Si il gagne, alors on affiche la scène de victoire
            try {
                BorderPane root = FXMLLoader.load(getClass().getResource("../vue/gameWin.fxml"));
                Scene scene = new Scene(root,1920,1080);
                Stage window = (Stage)(map.getScene().getWindow());
                window.setScene(scene);
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
