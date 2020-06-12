package app.controleur;


import app.modele.Attaquant;
import app.modele.Eleve.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControleurGameOver implements Initializable {

    @FXML
    private Label resultat; // Gagnee / Perdue

    @FXML
    private Label ennemisTues;

    @FXML
    private Label lbNormal;
    @FXML
    private HBox hbNormal;

    @FXML
    private Label lbTheo;
    @FXML
    private HBox hbTheo;

    @FXML
    private Label lbHaris;
    @FXML
    private HBox hbHaris;

    @FXML
    private Label lbMateo;
    @FXML
    private HBox hbMateo;

    @FXML
    private Label lbThomas;
    @FXML
    private HBox hbThomas;

    @FXML
    private Label lbAlexandre;
    @FXML
    private HBox hbAlexandre;

    @FXML
    private Label lbVie;

    @FXML
    private Label lbScore;

    private static int totalEnnemis;
    private static boolean gagne;
    private static int vieRestante;
    private static int vieMax;
    private static ArrayList<Attaquant> vaincu = new ArrayList<>();
    private int nbNormal;
    private int nbTheo;
    private int nbHaris;
    private int nbMateo;
    private int nbThomas;
    private int nbAlexandre;

    public MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final File defaite = new File("src/resources/musique/game-over.mp3");
        final File victoire = new File("src/resources/musique/victory.mp3");

        if (gagne){
            final Media media = new Media(victoire.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            this.resultat.setTextFill(Color.valueOf("#31ae00"));
            this.resultat.setText("Gagné");
        }
        else {
            final Media media = new Media(defaite.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            this.resultat.setTextFill(Color.valueOf("#cc2121"));
            this.resultat.setText("Perdu");
        }

        this.ennemisTues.setText("Ennemis tués :\t\t" + vaincu.size() + "/" + totalEnnemis);
        nbEnnemi();

        // Ne s'affiche pas si aucun n'a ete tue
        if (nbNormal == 0) {
            this.hbNormal.setVisible(false);
            this.hbNormal.managedProperty().setValue(false);
        }
        this.lbNormal.setText(String.valueOf(nbNormal));
        if (nbTheo == 0) {
            this.hbTheo.setVisible(false);
            this.hbTheo.managedProperty().setValue(false);
        }
        this.lbTheo.setText(String.valueOf(nbTheo));
        if (nbHaris == 0) {
            this.hbHaris.setVisible(false);
            this.hbHaris.managedProperty().setValue(false);
        }
        this.lbHaris.setText(String.valueOf(nbHaris));
        if (nbMateo == 0) {
            this.hbMateo.setVisible(false);
            this.hbMateo.managedProperty().setValue(false);
        }
        this.lbMateo.setText(String.valueOf(nbMateo));
        if (nbThomas == 0) {
            this.hbThomas.setVisible(false);
            this.hbThomas.managedProperty().setValue(false);
        }
        this.lbThomas.setText(String.valueOf(nbThomas));
        if (nbAlexandre == 0) {
            this.hbAlexandre.setVisible(false);
            this.hbAlexandre.managedProperty().setValue(false);
        }
        this.lbAlexandre.setText(String.valueOf(nbAlexandre));

        this.lbVie.setText("Vie : \t \t \t " + vieRestante);

        this.lbScore.setText("Score :\t" + calculerScore());
    }

    // Calcul le nombre d'ennemis tues
    private void nbEnnemi(){
        nbNormal = 0;
        nbTheo = 0;
        nbHaris = 0;
        nbMateo = 0;
        for (int i = 0; i < vaincu.size(); i++) {
            if (vaincu.get(i) instanceof Telio)
                nbNormal++;

            if (vaincu.get(i) instanceof Theo)
                nbTheo++;

            if (vaincu.get(i) instanceof Haris)
                nbHaris++;

            if (vaincu.get(i) instanceof Mateo)
                nbMateo++;

            if (vaincu.get(i) instanceof Thomas)
                nbThomas++;

            if (vaincu.get(i) instanceof Alexandre)
                nbAlexandre++;
        }
    }

    public static void setVaincu(ArrayList<Attaquant> liste){
        vaincu.clear();
        for (int i = 0; i < liste.size(); i++)
            vaincu.add(liste.get(i));
    }
    public static void setGagne(boolean g){
            gagne = g;
    }
    public static void setTotalEnnemis(int total){
        totalEnnemis = total;
    }
    public static void setVieRestante(int v){
        if (v < 0)
            v = 0;
        vieRestante = v;
    }

    public static void setVieMax(int v){
        vieMax = v;
    }

    private int calculerScore(){
        int score = vieRestante*100/vieMax;
        return score;
    }

    @FXML
    void rejouer(MouseEvent event) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("../vue/sampleChoixDuNiveau.fxml"));
            Scene scene = new Scene(root,1920,1080);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
