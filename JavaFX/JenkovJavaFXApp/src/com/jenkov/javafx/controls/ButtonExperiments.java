package com.jenkov.javafx.controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class ButtonExperiments extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("HBox Experiment 1");

    FileInputStream input = new FileInputStream(".\\src\\imgs\\EFACEC.jpg");
    Image image = new Image(input);
    ImageView imageView = new ImageView(image);

    Button button = new Button("Home", imageView);

    Scene scene = new Scene(button, 1100, 650);
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
