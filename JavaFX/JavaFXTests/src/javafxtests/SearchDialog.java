/**
 * <p>
 * Classname: javafxtests.SearchDialog
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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
package javafxtests;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.geometry.Insets;

import javafx.scene.Node;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import javafx.util.Pair;


public class SearchDialog extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create the custom dialog.

    Dialog<Pair<String, Boolean>> dialog = new Dialog<>();
    dialog.setTitle("Search");
    dialog.setHeaderText("Enter search parameters");

      // Set the search icon.
    //dialog.setGraphic(new ImageView(this.getClass().getResource("search.png").toString()));

      // Set the button types.
    ButtonType searchButtonType = new ButtonType("Search", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(searchButtonType,
        ButtonType.CANCEL);

      // Create the layout for the controls.
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

      // Create and initialize the search-text and case-sensitive-search controls.
    TextField srchText = new TextField();
    srchText.setPromptText("Search text");
    CheckBox css = new CheckBox("Case-sensitive search");

      // Populate the layout with a label along with the search text and
    // case-sensitive search controls.
    grid.add(new Label("Search Text:"), 0, 0);
    grid.add(srchText, 1, 0);
    grid.add(css, 0, 1);

      // Disable/enable search button depending on whether search-text field is
    // empty. Button defaults to being disabled.
    Node searchButton = dialog.getDialogPane().lookupButton(searchButtonType);
    searchButton.setDisable(true);
    srchText.textProperty().addListener((observable, oldValue, newValue) -> {
      searchButton.setDisable(newValue.trim().isEmpty());
    });

      // Install controls layout in the dialog panel.
    dialog.getDialogPane().setContent(grid);

      // Request focus on the search-text field. See
    // https://community.oracle.com/thread/2321126 for information on why
    // Platform.runLater() is used.
    Platform.runLater(() -> srchText.requestFocus());

      // Convert the result to a srchtext-css-status pair when the search button
    // is clicked.
    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == searchButtonType) {
        return new Pair<>(srchText.getText(), css.isSelected());
      }
      return null;
    });

      // Display dialog box and wait for user response.
    Optional<Pair<String, Boolean>> result = dialog.showAndWait();

      // If the user closed the dialog box via the search button, output the
    // chosen search text and case-sensitive search status.
    result.ifPresent(stcss -> {
      System.out.println("Search text = " + stcss.getKey() + ", Case-sensitive search = " + stcss.
          getValue());
    });
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}
