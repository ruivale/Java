/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtests;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.layout.HBox;

import javafx.stage.Stage;


/**
 *
 * @author 2334
 */
public class JavaFXTests extends Application {

  @Override
  public void start(Stage primaryStage) {
    Button btn1 = new Button("Demo 1");
    btn1.setOnAction(ae -> {
      // 1. Confirmation dialog with header message

      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmation");
      alert.setHeaderText("Exit application");
      alert.setContentText("Do you want to exit the " + "application?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
        Platform.exit();
      }
    });
    Button btn2 = new Button("Demo 2");
    btn2.setOnAction(ae -> {
      // 2. Confirmation dialog without header message

      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmation");
      alert.setContentText("Do you want to exit the " + "application?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
        Platform.exit();
      }
    });
    Button btn3 = new Button("Demo 3");
    btn3.setOnAction(ae -> {
      // 3. Error dialog with header message

      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Printer error");
      alert.setContentText("Paper not loaded");
      alert.showAndWait();
    });
    Button btn4 = new Button("Demo 4");
    btn4.setOnAction(ae -> {
      // 4. Error dialog without header message

      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Paper not loaded");
      alert.showAndWait();
    });
    Button btn5 = new Button("Demo 5");
    btn5.setOnAction(ae -> {
      // 5. Information dialog with header message

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Information");
      alert.setHeaderText("Time status");
      alert.setContentText("The time is currently 10PM.");
      alert.showAndWait();
    });
    Button btn6 = new Button("Demo 6");
    btn6.setOnAction(ae -> {
      // 6. Information dialog without header message

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Information");
      alert.setContentText("The time is currently 10PM.");
      alert.showAndWait();
    });
    Button btn7 = new Button("Demo 7");
    btn7.setOnAction(ae -> {
      // 7. Warning dialog with header message

      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Battery status");
      alert.setContentText("The battery charge is low.");
      alert.showAndWait();
    });
    Button btn8 = new Button("Demo 8");
    btn8.setOnAction(ae -> {
      // 8. Warning dialog without header message

      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setContentText("The battery charge is low.");
      alert.showAndWait();
    });
    HBox hboxForm = new HBox(10);
    hboxForm.setPadding(new Insets(10, 10, 10, 10));
    hboxForm.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7,
        btn8);
    Scene scene = new Scene(hboxForm);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.setTitle("Alert Demo");
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
