/**
 * <p>
 * Classname: javafxtests.SpinnerDemo
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

import javafx.application.Application;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

public class SpinnerDemo extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      Spinner<Integer> ispinner = new Spinner<>(1, 10, 2);
      Spinner<Double> dspinner = new Spinner<>(1.5, 3.5, 1.5, 0.5);

      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(10));

      grid.add(new Label("Integer Spinner"), 0, 0);
      grid.add(ispinner, 1, 0);
      grid.add(new Label("Double Spinner"), 0, 1);
      grid.add(dspinner, 1, 1);

      Scene scene = new Scene(grid, 350, 100);
      primaryStage.setTitle("SpinnerDemo");
      primaryStage.setScene(scene);
      primaryStage.show();
   }




  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}