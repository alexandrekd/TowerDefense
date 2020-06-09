package app.controleur;


import app.modele.Attaquant;
import app.modele.Eleve.Haris;
import app.modele.Eleve.Mateo;
import app.modele.Eleve.Normal;
import app.modele.Eleve.Theo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControleurGameOver implements Initializable {

    @FXML
    private Label resultat;

    @FXML
    private Label ennemisTues;

    @FXML
    private Label lbNormal;

    @FXML
    private Label lbTheo;

    @FXML
    private Label lbHaris;

    @FXML
    private Label lbMateo;

    @FXML
    private Label lbVie;

    private static int totalEnnemis;
    private static boolean gagne;
    private static ArrayList<Attaquant> vaincu = new ArrayList<>();
    private int nbNormal;
    private int nbTheo;
    private int nbHaris;
    private int nbMateo;
    private static int vieRestante;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (gagne){
            this.resultat.setTextFill(Color.valueOf("#31ae00"));
            this.resultat.setText("Gagnée");
        }
        else {
            this.resultat.setTextFill(Color.valueOf("#cc2121"));
            this.resultat.setText("Perdue");
        }

        this.ennemisTues.setText("Ennemis tués :\t\t" + vaincu.size() + "/" + totalEnnemis);
        nbEnnemi();
        this.lbNormal.setText(String.valueOf(nbNormal));
        this.lbTheo.setText(String.valueOf(nbTheo));
//        this.lbHaris.setText(String.valueOf(nbHaris));
        this.lbMateo.setText(String.valueOf(nbMateo));

        this.lbVie.setText("Vie :\t\t" + vieRestante);
    }

    private void nbEnnemi(){
        nbNormal = 0;
        nbTheo = 0;
        nbHaris = 0;
        nbMateo = 0;
        for (int i = 0; i < vaincu.size(); i++) {
            if (vaincu.get(i) instanceof Normal)
                nbNormal++;

            if (vaincu.get(i) instanceof Theo)
                nbTheo++;

            if (vaincu.get(i) instanceof Haris)
                nbHaris++;

            if (vaincu.get(i) instanceof Mateo)
                nbMateo++;
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
    public static void setVie(int v){
        if (v < 0)
            v = 0;
        vieRestante = v;

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
