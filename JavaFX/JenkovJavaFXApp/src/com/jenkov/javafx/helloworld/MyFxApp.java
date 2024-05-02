/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jenkov.javafx.helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 *
 * @author 2334
 */
public class MyFxApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("My First JavaFX App");

    Label label = new Label("Hello World, JavaFX !");
    String styles = "-fx-background-color: #0000ff;" + "-fx-border-color: #ff0000;";
    label.setStyle(styles);

    Scene scene = new Scene(label, 400, 200);


    primaryStage.setScene(scene);

    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Application.launch(args);
  }

}
