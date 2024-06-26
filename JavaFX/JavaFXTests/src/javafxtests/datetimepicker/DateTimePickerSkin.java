/**
 * <p>
 * Classname: javafxtests.datetimepicker.DateTimePickerSkin
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
 * Rua Eng.� Frederico Ulrich ? Ap. 3078
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

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;


public class DateTimePickerSkin extends DatePickerSkin {

  private DateTimePicker datePicker;
  private DatePickerContent ret;

  public DateTimePickerSkin(DateTimePicker datePicker) {
    super(datePicker);
    this.datePicker = datePicker;
  }

  @Override
  public Node getPopupContent() {
    if (ret == null) {
      ret = (DatePickerContent) super.getPopupContent();

      Slider hours = new Slider(0, 23, (datePicker.getTimeValue() != null ? datePicker.getTimeValue().
        getMinute() : 0));
      Label hoursValue = new Label("Hours: " + (datePicker.getTimeValue() != null ? datePicker.
        getTimeValue().getHour() : "") + " ");

      Slider minutes = new Slider(0, 59, (datePicker.getTimeValue() != null ? datePicker.getTimeValue().
        getMinute() : 0));
      Label minutesValue = new Label("Minutes: " + (datePicker.getTimeValue() != null ? datePicker.
        getTimeValue().getMinute() : "") + " ");

      Slider seconds = new Slider(0, 59, (datePicker.getTimeValue() != null ? datePicker.getTimeValue().
        getSecond() : 0));
      Label secondsValue = new Label("Seconds: " + (datePicker.getTimeValue() != null ? datePicker.
        getTimeValue().getSecond() : "") + " ");

      ret.getChildren().addAll(new HBox(hoursValue, hours), new HBox(minutesValue, minutes), new HBox(
        secondsValue, seconds));

      hours.valueProperty().addListener((observable, oldValue, newValue) -> {
        datePicker.setTimeValue(datePicker.getTimeValue().withHour(newValue.intValue()));
        hoursValue.setText("Hours: " + String.format("%02d", datePicker.getTimeValue().getHour()) + " ");
      });

      minutes.valueProperty().addListener((observable, oldValue, newValue) -> {
        datePicker.setTimeValue(datePicker.getTimeValue().withMinute(newValue.intValue()));
        minutesValue.setText("Minutes: " + String.format("%02d", datePicker.getTimeValue().getMinute())
                             + " ");
      });

      seconds.valueProperty().addListener((observable, oldValue, newValue) -> {
        datePicker.setTimeValue(datePicker.getTimeValue().withSecond(newValue.intValue()));
        secondsValue.setText("Seconds: " + String.format("%02d", datePicker.getTimeValue().getSecond())
                             + " ");
      });

    }
    return ret;
  }

}
