/**
 * <p>
 * Classname: javafxtests.scene.control.SplitPaneExample
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2019 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package javafxtests.scene.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplitPaneExample extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) {

    SplitPane splitPane = new SplitPane();

    VBox leftControl = new VBox(new Label("Left Control"));
    VBox midControl = new VBox(new Label("Mid Control"));
    VBox rightControl = new VBox(new Label("Right Control"));

    splitPane.getItems().addAll(leftControl, midControl, rightControl);

    Scene scene = new Scene(splitPane);

    primaryStage.setScene(scene);
    primaryStage.setTitle("JavaFX App");

    primaryStage.show();
  }
}
