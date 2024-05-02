package pl.plum.mdbuttontest;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
            getClass().getResource("/fxml/main.fxml"));
            //new URL("file://D:/Projects/JavaFX/MDButton/md-button-fx-sample-master/src/main/resources/fxml/main.fxml"));
        primaryStage.setTitle("Material Design FX");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
