/**
 * <p>
 * Classname: fxdocs.FilterListApp
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

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Jun 20, 2017, 6:13:08 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class FilterListApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    //
    // Test data
    //
    Player[] players = {new Player("BOS", "David Ortiz"),
      new Player("BOS", "Jackie Bradley Jr."),
      new Player("BOS", "Xander Bogarts"),
      new Player("BOS", "Mookie Betts"),
      new Player("HOU", "Jose Altuve"),
      new Player("HOU", "Will Harris"),
      new Player("WSH", "Max Scherzer"),
      new Player("WSH", "Bryce Harper"),
      new Player("WSH", "Daniel Murphy"),
      new Player("WSH", "Wilson Ramos")};

    //
    // Set up the model which is two lists of Players and a filter criteria
    //
    ReadOnlyObjectProperty<ObservableList<Player>> playersProperty = new SimpleObjectProperty<>(
      FXCollections.observableArrayList());

    ReadOnlyObjectProperty<FilteredList<Player>> viewablePlayersProperty
      = new SimpleObjectProperty<FilteredList<Player>>(
        new FilteredList<>(playersProperty.get()
        ));

    ObjectProperty<Predicate<? super Player>> filterProperty = viewablePlayersProperty.get().
      predicateProperty();

    //
    // Build the UI
    //
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(4);

    HBox hbox = new HBox();
    hbox.setSpacing(2);

    ToggleGroup filterTG = new ToggleGroup();

    //
    // The toggleHandler action wills set the filter based on the TB selected
    //
    @SuppressWarnings("unchecked")
    EventHandler<ActionEvent> toggleHandler = (event) -> {
      ToggleButton tb = (ToggleButton) event.getSource();
      Predicate<Player> filter = (Predicate<Player>) tb.getUserData();
      filterProperty.set(filter);
    };

    ToggleButton tbShowAll = new ToggleButton("Show All");
    tbShowAll.setSelected(true);
    tbShowAll.setToggleGroup(filterTG);
    tbShowAll.setOnAction(toggleHandler);
    tbShowAll.setUserData((Predicate<Player>) (Player p) -> true);

    //
    // Create a distinct list of teams from the Player objects, then create
    // ToggleButtons
    //
    List<ToggleButton> tbs = Arrays.asList(players)
      .stream()
      .map((p) -> p.getTeam())
      .distinct()
      .map((team) -> {
        ToggleButton tb = new ToggleButton(team);
        tb.setToggleGroup(filterTG);
        tb.setOnAction(toggleHandler);
        tb.setUserData((Predicate<Player>) (Player p) -> team.equals(p.getTeam()));
        return tb;
      })
      .collect(Collectors.toList());

    hbox.getChildren().add(tbShowAll);
    hbox.getChildren().addAll(tbs);

    //
    // Create a ListView bound to the viewablePlayers property
    //
    ListView<Player> lv = new ListView<>();
    lv.itemsProperty().bind(viewablePlayersProperty);

    vbox.getChildren().addAll(hbox, lv);

    Scene scene = new Scene(vbox);

    primaryStage.setScene(scene);
    primaryStage.setOnShown((evt) -> {
      playersProperty.get().addAll(players);
    });

    primaryStage.show();

  }

  public static void main(String args[]) {
    launch(args);
  }


  static class Player {

    private final String team;
    private final String playerName;

    public Player(String team,
                  String playerName) {
      this.team = team;
      this.playerName = playerName;
    }

    public String getTeam() {
      return team;
    }

    public String getPlayerName() {
      return playerName;
    }

    @Override
    public String toString() {
      return playerName + " (" + team + ")";
    }
  }

}
