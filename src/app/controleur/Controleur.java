package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import modele.Attaquants;
import modele.Environnement;
import modele.Tourelle;
import org.omg.CORBA.Environment;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private TilePane plateau;

    @FXML
    private RadioButton btTourelleSimple;

    @FXML
    private Button btStart;

    @FXML
    private ToggleGroup Tour;

    private Environnement env;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.env = new Environnement(550, 400);
        plateau.getChildren().add(new ImageView("resources/textures/test.png"));
    }

    @FXML
    void poserTourelle(MouseEvent event) {
        Node source = (Node)event.getSource();
        /*int colIndex = (GridPane.getColumnIndex(source) == null) ?  0 : (GridPane.getColumnIndex(source));
        int colRow = (GridPane.getRowIndex(source) == null) ? 0 : (GridPane.getRowIndex(source));*/
       int colIndex = (int) event.getX();
       int colRow = (int) event.getY();

       Tourelle tourelle = new Tourelle(5, colIndex, colRow, 3, 20, this.env);
       this.env.ajouterTourelle(tourelle);
       creerSprite(tourelle);
    }

    void creerSprite(Tourelle tourelle){
        Circle c = new Circle(10);
        c.setId(tourelle.getId());
        c.setFill(Color.RED);
        c.setTranslateX(tourelle.getX());
        c.setTranslateY(tourelle.getY());
        /*c.translateXProperty().bind(tourelle.getXProperty());
        c.translateYProperty().bind(tourelle.getYProperty());*/
        plateau.getChildren().add(c);
    }

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
