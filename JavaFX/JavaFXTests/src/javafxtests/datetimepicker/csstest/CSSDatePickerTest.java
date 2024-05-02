/**
 * <p>
 * Classname: javafxtests.datetimepicker.csstest.CSSDatePickerTest
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package javafxtests.datetimepicker.csstest;

import java.util.Locale;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CSSDatePickerTest extends Application {

  @Override
  public void start(Stage stage) {
    System.err.println(System.getProperty("javafx.runtime.version"));

    //Application.setUserAgentStylesheet(getClass().getResource("shortstyle.css").toExternalForm());
    Application.setUserAgentStylesheet(getClass().getResource("style.css").toExternalForm());


    Parent content = createContent();
    Scene scene = new Scene(content, 500, 500);
    stage.setScene(scene);
    stage.show();
  }

  private Parent createContent() {
    VBox vbox = new VBox(10);
    vbox.getChildren().add(createDatePicker(false, false));
    vbox.getChildren().add(createDatePicker(true, false));
    vbox.getChildren().add(createDatePicker(false, true));
    return vbox;
  }

  private DatePicker createDatePicker(boolean showWeekNumbers,
                                      boolean disabled) {
    DatePicker dp = new DatePicker();
    dp.setDisable(disabled);
    dp.setShowWeekNumbers(showWeekNumbers);
    return dp;
  }

  public static void main(String[] args) {
    Locale.setDefault(Locale.PRC);
    Application.launch(args);
  }

}
