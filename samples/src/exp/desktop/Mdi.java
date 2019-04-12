/*
 *
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 *
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


package  exp.desktop;

import  java.awt.*;
import  java.awt.event.*;
import  java.awt.image.*;
import  javax.swing.*;
import  javax.swing.event.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */
public class Mdi extends JFrame {

    /**
     *
     */
    public Mdi () {
        super("title");
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(inset, inset);
        setSize(screenSize.width - inset*2, screenSize.height - inset*2);
        addWindowListener(new WindowAdapter() {

            /**
             *
             * @param e
             */
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        });
        JInternalFrame jif = new JInternalFrame();
        jif.setBounds(20, 20, 100, 200);
        jif.setVisible(true);
        JPanel overAllPanel = new JPanel();
        overAllPanel.setLayout(new BorderLayout());
        setContentPane(overAllPanel);
        JDesktopPane desktop = new JDesktopPane();
        overAllPanel.add(desktop, BorderLayout.CENTER);
        desktop.setBackground(Color.white);
        desktop.add(jif);
        // here JScrollPane with desktopPane
        JScrollPane scrollPane = new JScrollPane();
        JViewport viewport = new JViewport();
        viewport.setView(desktop);
        scrollPane.setViewport(viewport);
        desktop.setPreferredSize(new Dimension(1600, 1200));                    //very  important
        overAllPanel.add(scrollPane, BorderLayout.CENTER);
        this.setBounds(50, 50, 300, 200);
        this.setVisible(true);
    }

    /**
     *
     * @param args[]
     */
    public static void main (String args[]) {
        new Mdi();
    }

}



