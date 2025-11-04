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

package scenebuilderhellosw;

import com.oracle.javafx.scenebuilder.kit.editor.EditorController;
import com.oracle.javafx.scenebuilder.kit.editor.panel.content.ContentPanelController;
import com.oracle.javafx.scenebuilder.kit.editor.panel.hierarchy.HierarchyPanelController;
import com.oracle.javafx.scenebuilder.kit.editor.panel.inspector.InspectorPanelController;
import com.oracle.javafx.scenebuilder.kit.fxom.FXOMDocument;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 */
public class SceneBuilderHelloSW extends JFrame implements ActionListener, WindowListener {

    private final JFXPanel hierarchyHost = new JFXPanel();
    private final JFXPanel contentHost = new JFXPanel();
    private final JFXPanel inspectorHost = new JFXPanel();
    
    private final EditorController editorController 
            = new EditorController();
    private final HierarchyPanelController hierarchyPanelController
            = new HierarchyPanelController(editorController);
    private final ContentPanelController contentPanelController
            = new ContentPanelController(editorController);
    private final InspectorPanelController inspectorPanelController
            = new InspectorPanelController(editorController);
    static SceneBuilderHelloSW frame;
    
    public SceneBuilderHelloSW() throws InterruptedException {
        assert SwingUtilities.isEventDispatchThread();
        
        final JSplitPane majorSplitPane = new JSplitPane();
        final JSplitPane minorSplitPane = new JSplitPane();
        majorSplitPane.setLeftComponent(hierarchyHost);
        majorSplitPane.setRightComponent(minorSplitPane);
        minorSplitPane.setLeftComponent(contentHost);
        minorSplitPane.setRightComponent(inspectorHost);
        
        assert majorSplitPane.getResizeWeight() == 0.0;
        minorSplitPane.setResizeWeight(1.0);
        majorSplitPane.getLeftComponent().setMinimumSize(new Dimension(200, 0));
        minorSplitPane.getRightComponent().setMinimumSize(new Dimension(200, 0));
        
        setLayout(new BorderLayout());
        add(majorSplitPane, BorderLayout.CENTER);
        
        final JButton openButton = new JButton("Open...");
        openButton.setActionCommand("open");
        openButton.addActionListener(this);
        add(openButton, BorderLayout.SOUTH);
        
        addWindowListener(this);
        
        runLaterAndWait(new Runnable() {
            @Override public void run() {
                assert Platform.isFxApplicationThread();
                hierarchyHost.setScene(new Scene(hierarchyPanelController.getPanelRoot()));
                contentHost.setScene(new Scene(contentPanelController.getPanelRoot()));
                inspectorHost.setScene(new Scene(inspectorPanelController.getPanelRoot()));
            }
        });
        
        setSize(900, 600);
    }
    
    
    /*
     * ActionListener
     */
    
    public void actionPerformed(ActionEvent e) {
        assert e.getActionCommand().equals("open");
        
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("FXML File", "fxml"));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            final File fxmlFile = fc.getSelectedFile();
            try {
                final URL fxmlURL = fxmlFile.toURI().toURL();
                final String fxmlText = FXOMDocument.readContentFromURL(fxmlURL);
                runLaterAndWait(new Runnable() {
                    @Override public void run() {
                        try {
                            editorController.setFxmlTextAndLocation(fxmlText, fxmlURL);
                        } catch(IOException x) {
                            System.out.println("FXML data have been rejected");
                        }
                    }
                });
            } catch(IOException|InterruptedException x) {
                System.out.println("Failed to read FXML data from " + fxmlFile);
                x.printStackTrace();
            }
        }
    }
    
    /*
     * main
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    frame = new SceneBuilderHelloSW();
                    frame.setVisible(true);
                } catch (InterruptedException x) {
                    x.printStackTrace();
                }
            }
        });
    }
  
    private static void runLaterAndWait(Runnable runnable) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Runnable r = new Runnable() {
            @Override public void run() {
                runnable.run();
                latch.countDown();
            }
        };
        Platform.runLater(r);
        latch.await();
    }

    public void windowClosing(WindowEvent e) {
        if (frame.isDisplayable()) {
            frame.dispose();
        }
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
