/**
 * <p>
 * Classname: fxdocs.NoNullComboApp
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

package fxdocs;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 21, 2017, 4:35:54 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class NoNullComboApp extends Application {

    private final ComboBox<String> country = new ComboBox<>();
    private final ComboBox<String> city = new ComboBox<>();

    private List<String> countries = new ArrayList<>();

    private Map<String, List<String>> citiesMap = new LinkedHashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label countryLabel = new Label("Country:");
        country.setPrefWidth(200.0d);
        Label cityLabel = new Label("City:");
        city.setPrefWidth(200.0d);
        Button saveButton = new Button("Save");

        VBox vbox = new VBox(
                countryLabel,
                country,
                cityLabel,
                city,
                saveButton
        );
        vbox.setAlignment(Pos.CENTER_LEFT );
        vbox.setSpacing( 10.0d );

        TilePane outerBox = new TilePane(vbox);
        outerBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(outerBox);

        initData();

        country.getItems().add("");
        country.getItems().addAll( countries );
        country.setValue( "" );  // empty selection is object and not null

        city.getItems().add("");
        city.setValue( "" );

        country.setOnAction( (evt) -> {

            String cty = country.getValue();

            city.getItems().removeIf( (c) -> !c.isEmpty() );

            if( citiesMap.containsKey(cty) ) {  // not an empty key
                city.getItems().addAll( citiesMap.get(cty) );
            }
        });

        saveButton.setOnAction( (evt) -> {
           System.out.println("saving country='" + country.getValue() +
                                      "', city='" + city.getValue() + "'");
        });

        primaryStage.setTitle("NoNullComboApp");
        primaryStage.setScene( scene );
        primaryStage.setWidth( 320 );
        primaryStage.setHeight( 480 );
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initData() {

        String COUNTRY_FR = "France";
        String COUNTRY_DE = "Germany";
        String COUNTRY_CH = "Switzerland";

        countries.add(COUNTRY_FR); countries.add(COUNTRY_DE); countries.add(COUNTRY_CH);

        List<String> frenchCities = new ArrayList<>();
        frenchCities.add("Paris");
        frenchCities.add("Strasbourg");

        List<String> germanCities = new ArrayList<>();
        germanCities.add("Berlin");
        germanCities.add("Cologne");
        germanCities.add("Munich");

        List<String> swissCities = new ArrayList<>();
        swissCities.add("Zurich");

        citiesMap.put(COUNTRY_FR, frenchCities );
        citiesMap.put(COUNTRY_DE, germanCities );
        citiesMap.put(COUNTRY_CH, swissCities );
    }
}