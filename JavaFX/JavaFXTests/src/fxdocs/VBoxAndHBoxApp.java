/**
 * <p>
 * Classname: fxdocs.VBoxAndHBoxApp
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
package fxdocs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Jun 21, 2017, 12:17:57 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class VBoxAndHBoxApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    VBox vbox = new VBox();
    HBox topControls = new HBox();

    VBox.setMargin(topControls, new Insets(10.0d));
    topControls.setAlignment(Pos.BOTTOM_LEFT);

    Button btnRefresh = new Button("Refresh");

    HBox topRightControls = new HBox();
    HBox.setHgrow(topRightControls, Priority.ALWAYS);
    topRightControls.setAlignment(Pos.BOTTOM_RIGHT);
    Hyperlink signOutLink = new Hyperlink("Sign Out");
    topRightControls.getChildren().add(signOutLink);

    topControls.getChildren().addAll(btnRefresh, topRightControls);

    TableView<Customer> tblCustomers = new TableView<>();
    tblCustomers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    VBox.setMargin(tblCustomers, new Insets(0.0d, 10.0d, 10.0d, 10.0d));
    VBox.setVgrow(tblCustomers, Priority.ALWAYS);

    TableColumn<Customer, String> lastNameCol = new TableColumn<>("Last Name");
    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

    TableColumn<Customer, String> firstNameCol = new TableColumn<>("First Name");
    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    tblCustomers.getColumns().addAll(lastNameCol, firstNameCol);

    Separator sep = new Separator();

    HBox bottomControls = new HBox();
    bottomControls.setAlignment(Pos.BOTTOM_RIGHT);
    VBox.setMargin(bottomControls, new Insets(10.0d));

    Button btnClose = new Button("Close");

    bottomControls.getChildren().add(btnClose);

    vbox.getChildren().addAll(
      topControls,
      tblCustomers,
      sep,
      bottomControls
    );

    Scene scene = new Scene(vbox);

    primaryStage.setScene(scene);
    primaryStage.setWidth(800);
    primaryStage.setHeight(600);
    primaryStage.setTitle("VBox and HBox App");
    primaryStage.setOnShown((evt) -> loadTable(tblCustomers));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void loadTable(TableView<Customer> tblCustomers) {
    tblCustomers.getItems().add(new Customer("George", "Washington"));
    tblCustomers.getItems().add(new Customer("Abe", "Lincoln"));
    tblCustomers.getItems().add(new Customer("Thomas", "Jefferson"));
  }

}
