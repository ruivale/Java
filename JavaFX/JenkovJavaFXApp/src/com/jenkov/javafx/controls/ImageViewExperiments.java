package com.jenkov.javafx.controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class ImageViewExperiments extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("ImageView Experiment 1");

    FileInputStream input =
      new FileInputStream(".\\src\\imgs\\EFACEC.jpg");
    Image image = new Image(input);
    ImageView imageView = new ImageView(image);

    HBox hbox = new HBox(imageView);

    Scene scene = new Scene(hbox, 1000, 650);
    primaryStage.setScene(scene);
    primaryStage.show();
  }




  public static void main(String[] args) {
    Application.launch(args);
  }

}
