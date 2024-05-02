/**
 * <p>
 * Classname: fxdocs.PaneDemo
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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;



/**
 * Defines the Pane Demo application for JavaFX.
 * @author Christoph Nahr
 * @version 1.0.1
 */
public class PaneDemo extends Application {

    static final double BORDER_RADIUS = 4;

    static Border createBorder() {
        return new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(BORDER_RADIUS), BorderStroke.THICK));
    }

    static Shape createShape() {
        final Ellipse shape = new Ellipse(50, 50);
        shape.setCenterX(80);
        shape.setCenterY(80);
        shape.setFill(Color.LIGHTCORAL);
        shape.setStroke(Color.LIGHTCORAL);
        return shape;
    }

    static Region createDefault() {
        final Pane pane = new Pane(createShape());
        pane.setBorder(createBorder());
        pane.setPrefSize(100, 100);
        return pane;
    }

    /**
     * Clips the children of the specified {@link Region} to its current size.
     * This requires attaching a change listener to the region?s layout bounds,
     * as JavaFX does not currently provide any built-in way to clip children.
     *
     * @param region the {@link Region} whose children to clip
     * @param arc the {@link Rectangle#arcWidth} and {@link Rectangle#arcHeight}
     *            of the clipping {@link Rectangle}
     * @throws NullPointerException if {@code region} is {@code null}
     */
    public static void clipChildren(Region region, double arc) {

        final Rectangle outputClip = new Rectangle();
        outputClip.setArcWidth(arc);
        outputClip.setArcHeight(arc);
        region.setClip(outputClip);

        region.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }

    static Region createClipped() {
        final Pane pane = new Pane(createShape());
        pane.setBorder(createBorder());
        pane.setPrefSize(100, 100);

        // clipped children still overwrite Border!
        clipChildren(pane, 3 * BORDER_RADIUS);

        return pane;
    }

    static Region createNested() {
        // create drawing Pane without Border or size
        final Pane pane = new Pane(createShape());
        clipChildren(pane, BORDER_RADIUS);

        // create sized enclosing Region with Border
        final Region container = new StackPane(pane);
        container.setBorder(createBorder());
        container.setPrefSize(100, 100);
        return container;
    }

    /**
     * Starts the {@link PaneDemo} application.
     * @param primaryStage the primary {@link Stage} for the application
     */
    @Override
    public void start(Stage primaryStage) {

        final Button btnDefault = new Button("_Default");
        btnDefault.setPrefWidth(60);
        final Button btnClipped = new Button("_Clipped");
        btnClipped.setPrefWidth(60);
        final Button btnNested = new Button("_Nested");
        btnNested.setPrefWidth(60);

        final VBox buttons = new VBox(btnDefault, btnClipped, btnNested);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(16));
        buttons.setSpacing(8);

        final StackPane output = new StackPane(createDefault());
        output.setPadding(new Insets(8, 40, 40, 8));

        btnDefault.setOnAction(t -> output.getChildren().set(0, createDefault()));
        btnClipped.setOnAction(t -> output.getChildren().set(0, createClipped()));
        btnNested.setOnAction(t -> output.getChildren().set(0, createNested()));

        final Scene scene = new Scene(new HBox(buttons, output));
        HBox.setHgrow(output, Priority.ALWAYS);

        primaryStage.setTitle("Pane Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the {@link PaneDemo} application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
