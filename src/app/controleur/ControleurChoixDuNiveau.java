package app.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControleurChoixDuNiveau {
    @FXML
    private ImageView niveau1;

    @FXML
    void click1(MouseEvent event) {
        try {
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
