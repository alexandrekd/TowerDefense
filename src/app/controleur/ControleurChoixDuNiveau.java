package app.controleur;

import app.modele.Environnement;
import app.modele.Niveau;
import app.modele.Vagues;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class ControleurChoixDuNiveau {
    @FXML
    private ImageView niveau1;

    @FXML
    private ImageView niveau2;

    @FXML
    void click1(MouseEvent event) {
        try {
            Environnement.setMap(new ArrayList<Integer>(Arrays.asList(101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                    101, 103, 101, 102, 102, 102, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 101, 101, 101,
                    101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                    101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 101, 102, 101, 101, 101,
                    101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                    101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 103, 103, 103, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                    900, 102, 102, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 101, 102, 101, 101, 101,
                    101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 102, 102, 104,
                    101, 101, 102, 102, 102, 102, 101, 103, 103, 103, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                    101, 101, 102, 101, 101, 101, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 103, 103, 103, 101, 102, 101, 103, 101, 101, 101, 101, 101,
                    101, 101, 102, 101, 103, 103, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                    101, 101, 102, 101, 101, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                    101, 101, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 101, 101,
                    101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 103, 103, 103, 103, 101, 101, 101, 101, 101, 101, 101, 101, 101, 103, 101, 101, 101, 101, 101,
                    101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                    101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101
            )));
            Niveau.setIdVagues(1);

            BorderPane root = FXMLLoader.load(getClass().getResource("../vue/sample.fxml"));
            Scene scene = new Scene(root,1920,1080);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click2(MouseEvent event) {
        try {
            Environnement.setMap(new ArrayList<Integer>(Arrays.asList(101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                    101, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 104,
                    101, 102, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 101, 101, 101, 101,
                    101, 102, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 101, 101, 101, 101,
                    101, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101,
                    101, 101, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 102, 101, 101, 101, 101,
                    101, 101, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 102, 101, 101, 101, 101,
                    101, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101,
                    101, 102, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 101, 101, 101, 101,
                    101, 102, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 101, 101, 101, 101,
                    101, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101,
                    101, 101, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 102, 101, 101, 101, 101,
                    101, 101, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 103, 102, 101, 101, 101, 101,
                    900, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101,
                    101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                    101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101
            )));
            Niveau.setIdVagues(2);

            BorderPane root = FXMLLoader.load(getClass().getResource("../vue/sample.fxml"));
            Scene scene = new Scene(root,1920,1080);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
