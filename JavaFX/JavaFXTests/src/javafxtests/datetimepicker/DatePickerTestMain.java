/**
 * <p>
 * Classname: javafxtests.datetimepicker.DatePickerTestMain
 * </p>
 *
 * <p>Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
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

package javafxtests.datetimepicker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class DatePickerTestMain extends Application {

  @Override
  public void start(Stage primaryStage) {
    VBox vBox = new VBox();
    Scene s = new Scene(new ScrollPane(vBox), 600, 400);
    DatePickerTest d = new DatePickerTest();

    vBox.getChildren().add(d);

    primaryStage.setScene(s);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
