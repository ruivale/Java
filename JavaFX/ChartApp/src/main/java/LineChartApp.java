/*
 * Copyright 2017 Bekwam, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author carl
 */
public class LineChartApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.getData().add( new XYChart.Data<>(0.0,0.0));
        series.getData().add( new XYChart.Data<>(0.7,0.5));
        series.getData().add( new XYChart.Data<>(1.0,0.632));
        series.getData().add( new XYChart.Data<>(2.0,0.865));
        series.getData().add( new XYChart.Data<>(3.0,0.95));
        series.getData().add( new XYChart.Data<>( 4.0, 0.982 ));
        series.getData().add( new XYChart.Data<>( 5.0, 0.993 ));

        LineChart lc = new LineChart(
                new NumberAxis("Time Constant", 0.0, 5.0, 1),
                new NumberAxis("Voltage (Vs)", 0.0, 1.0, 0.1)
                );

        lc.getData().add( series );

        lc.setTitle("RC Charging");
        lc.setStyle("-fx-background-color: lightgray");
        lc.setCreateSymbols(false);
        lc.setLegendVisible(false);

        VBox vbox = new VBox(lc);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("LineChartApp");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
