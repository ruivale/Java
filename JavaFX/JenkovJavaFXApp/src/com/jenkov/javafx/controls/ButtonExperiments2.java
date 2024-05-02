package com.jenkov.javafx.controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ButtonExperiments2 extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Button Experiment 1");

    Button button1 = new Button("Button 1");
    Button button2 = new Button("Button 2");
    Button button3 = new Button("Button 3");
    Button button4 = new Button("Button 4");

    button1.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
    button2.setStyle("-fx-background-color: #00ff00");
    button3.setStyle("-fx-font-size: 2em; ");
    button4.setStyle("-fx-text-fill: #0000ff");

    HBox hbox = new HBox(button1, button2, button3, button4);

    Scene scene = new Scene(hbox, 400, 100);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
