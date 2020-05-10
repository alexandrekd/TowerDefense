package app.controleur;

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
import app.modele.Acteur;
import app.modele.Attaquants;
import app.modele.Environnement;
import app.modele.Tourelle;
import org.omg.CORBA.Environment;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

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
        plateau.getChildren().add(new ImageView("resources/textures/1.png"));
        plateau.getChildren().add(new ImageView("resources/textures/test.png"));

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

    @FXML
    void clickStart(MouseEvent event) {
        Attaquants attaquants = new Attaquants(this.env, 20, 5);
        System.out.println(attaquants.getX() + " ; " + attaquants.getY());
        Circle c = new Circle(5);
        c.setId(attaquants.getId());
        c.setFill(Color.BLUE);
        c.setTranslateX(attaquants.getX());
        c.setTranslateY(attaquants.getY());
        plateau.getChildren().add(c);
    }

}
