/**
 * <p>
 * Classname: javafxtests.TextFormatterDemo
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import javafx.scene.layout.HBox;

import javafx.stage.Stage;

import javafx.util.StringConverter;

public class TextFormatterDemo extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      Label lblAge = new Label("Age");
      TextField txtAge = new TextField("");
      StringConverter<Integer> formatter;
      formatter = new StringConverter<Integer>()
                  {
                     @Override
                     public Integer fromString(String string)
                     {
                        System.out.println("fromString(): string = " + string);
                        return Integer.parseInt(string);
                     }

                     @Override
                     public String toString(Integer object)
                     {
                        System.out.println("toString(): object = " + object);
                        if (object == null)
                           return "0";
                        System.out.println("object.tostring = " +
                                           object.toString());
                        return object.toString();
                     }
                  };
      txtAge.setTextFormatter(new TextFormatter<Integer>(formatter));
      HBox hboxForm = new HBox(10);
      hboxForm.setPadding(new Insets(10, 10, 10, 10));
      hboxForm.getChildren().addAll(lblAge, txtAge);
      Scene scene = new Scene(hboxForm);
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.setTitle("TextFormatterDemo");
      primaryStage.show();
   }


  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}