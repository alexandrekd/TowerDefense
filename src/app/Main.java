package app;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
    public Stage window;
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("vue/sampleDebut.fxml"));
            Scene scene = new Scene(root,1920,1080);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}