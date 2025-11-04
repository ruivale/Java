/*
 * Copyright (c) 2012, 2014, Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package scenebuilderhello;

import com.oracle.javafx.scenebuilder.kit.editor.EditorController;
import com.oracle.javafx.scenebuilder.kit.editor.panel.content.ContentPanelController;
import com.oracle.javafx.scenebuilder.kit.editor.panel.hierarchy.AbstractHierarchyPanelController;
import com.oracle.javafx.scenebuilder.kit.editor.panel.hierarchy.treeview.HierarchyTreeViewController;
import com.oracle.javafx.scenebuilder.kit.editor.panel.inspector.InspectorPanelController;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 */
public class SceneBuilderHello extends Application {
    
    private final EditorController editorController 
            = new EditorController();
    private final AbstractHierarchyPanelController hiearchyPanelController 
            = new HierarchyTreeViewController(editorController);
    private final ContentPanelController contentPanelController
            = new ContentPanelController(editorController);
    private final InspectorPanelController inspectorPanelController
            = new InspectorPanelController(editorController);
    
    @Override
    public void start(final Stage primaryStage) {
        Button btn = new Button("Open FXML...");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleOpenAction();
            }
        });
        
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        SplitPane splitPane = new SplitPane();
        
        root.getChildren().add(splitPane);
        root.getChildren().add(btn);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        VBox.setVgrow(btn, Priority.NEVER);
        
        splitPane.getItems().add(hiearchyPanelController.getPanelRoot());
        splitPane.getItems().add(contentPanelController.getPanelRoot());
        splitPane.getItems().add(inspectorPanelController.getPanelRoot());
        splitPane.getDividers().get(0).setPosition(0.25);
        splitPane.getDividers().get(1).setPosition(0.75);
        
        Scene scene = new Scene(root, 900, 600);
        
        primaryStage.setTitle("Hello from Scene Builder !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    private void handleOpenAction() {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter("FXML Document", "*.fxml");
        fileChooser.getExtensionFilters().add(filter);
        File fxmlFile = fileChooser.showOpenDialog(null);
        if (fxmlFile != null) {
            try {
                String fxmlText = readContentFromFile(fxmlFile);
                URL fxmlLocation = fxmlFile.toURI().toURL();
                editorController.setFxmlTextAndLocation(fxmlText, fxmlLocation);
            } catch(IOException x) {
                System.out.println("Failed to load FXML from " + fxmlFile);
            }
        }
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }

    
    private static String readContentFromFile(File file) throws IOException {
        final byte[] buffer;
        
        buffer = new byte[(int)file.length()];
        try (DataInputStream is = new DataInputStream(new FileInputStream(file))) {
            is.readFully(buffer);
        }
        
        return new String(buffer, Charset.forName("UTF-8"));
    }
}
