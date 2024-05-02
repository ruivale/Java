/**
 * <p>
 * Classname: javafxtests.datetimepicker.DatePickerExample
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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * From>
 * https://stackoverflow.com/questions/26341410/how-do-i-modify-the-month-and-year-controls-on-javafx-8s-datepicker
 *
 *
 * The best way to change appearance like this is to use an external CSS. You can refer to the source
 * code for the default stylesheet to see how the defaults are defined: the interesting parts for the
 * DatePicker are near the bottom (lines 2932 onwards at the time of writing).
 * http://hg.openjdk.java.net/openjfx/8/master/rt/file/f89b7dc932af/modules/controls/src/main/resources/com/sun/javafx/scene/control/skin/modena/modena.css
 *
 *
 * date-picker-readable.css:
 * .date-picker {
 *  -fx-font-size: 18pt;
 *  -fx-font-weight: bold ;
 * }
 * .date-picker .day-name-cell {
 *  -fx-padding: 10px ;
 * }
 *
 *
 * @author 2334
 */
public class DatePickerExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        DatePicker datePicker = new DatePicker();
        VBox root = new VBox(datePicker);

        Scene scene = new Scene(root, 250, 150);

        scene.getStylesheets().add(this.getClass().getResource("date-picker-readable.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}