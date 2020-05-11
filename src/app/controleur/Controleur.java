package app.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import app.modele.*;
import org.omg.CORBA.Environment;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    /*@FXML
    private TilePane plateau;*/

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

    ObservableList<Attaquants> l1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        l1 = FXCollections.observableArrayList();
        ListChangeListener<Attaquants > listen = (c-> System.out.println("Changement"));
        l1.addListener(listen);

        this.env = new Environnement(550, 400);
        setmap();
    }

    @FXML
    void poserTourelle(MouseEvent event) {
        Node source = (Node)event.getSource();
        int colIndex = (int) event.getX();
        int colRow = (int) event.getY();

        Tourelle tourelle = new Tourelle(5, colIndex, colRow, 3, 500, this.env);
        this.env.ajouterTourelle(tourelle);
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
        else if(acteur instanceof Attaquants){
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

    public void removeSprite(String id){
        plateau.getChildren().remove(id);
    }

    @FXML
    void clickStart(MouseEvent event) {
        Attaquants attaquant = new Attaquants(this.env, 20, 5);
        this.env.ajouterAttaquants(attaquant);
        this.l1.add(attaquant);
        creerSprite(attaquant);
    }

    @FXML
    void faireTours(MouseEvent event) {
        int nbtour;
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
            int x = (i * 10) % 550;
            int y = ((i * 10) / 550) * 10;
            texture.setY(y);
            texture.setX(x);
            plateau.getChildren().add(texture);
        }
    }
}
