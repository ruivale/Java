/**
 * <p>
 * Classname: javafxtests.ChoiceDemo
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package javafxtests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;

import javafx.stage.Stage;

public class ChoiceDemo extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      List<String> fruits = new ArrayList<>();
      fruits.add("apples");
      fruits.add("bananas");
      fruits.add("cherries");
      fruits.add("oranges");
      ChoiceDialog<String> choice = new ChoiceDialog<>("bananas", fruits);
      choice.setTitle("Fruits");
      choice.setHeaderText("Fruits");
      choice.setContentText("Choose your fruit:");
      Optional<String> result = choice.showAndWait();
      if (result.isPresent())
      {
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setContentText(result.get() + " are great!");
         alert.showAndWait();
      }
   }


  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}