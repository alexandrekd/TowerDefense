package app.controleur;


import app.modele.Attaquant;
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

    private static boolean gagne;
    private static ArrayList<Attaquant> vaincu = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (gagne){
            this.resultat.setTextFill(Color.valueOf("#31ae00"));
            this.resultat.setText("GAGNÉE");
        }
        else {
            this.resultat.setTextFill(Color.valueOf("#cc2121"));
            this.resultat.setText("PERDUE");
        }
    }

    public static void setVaincu(ArrayList<Attaquant> liste){
        for (int i = 0; i < liste.size(); i++)
            vaincu.add(liste.get(i));
    }

    public static void setGagne(boolean g){
            gagne = g;
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
