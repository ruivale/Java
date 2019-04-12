/*
 *
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


package  exp.desktop;

import  java.awt.*;
import  java.awt.image.*;
import  javax.swing.*;


/**
 * Form class: it creates a screen with a statusbar
 *
 * @version 2.2 06/26/01
 * @author Stefano Campioli
 */
public class ScrollingDesktop extends JFrame {
    private JPanel screenPanel, screenStatus, statusPanel;
    private JDesktopPane screenDesktop;
    private JScrollPane screenScroller;
    private BufferedImage statusImage;
    private Graphics2D statusCanvas;
    private JLabel statusDisplay;
    private Color bgColor = Color.black;
    private Color statusbgColor = new Color(117, 120, 112);

    /**
     *
     */
    public ScrollingDesktop () {
        createScreen();
        showScreen();
    }
    ;
    /**
     *
     * @param args
     */
    public static void main (String[] args) {
        ScrollingDesktop screenWindow = new ScrollingDesktop();
    }

    /**
     *
     */
    public void createScreen () {
        screenDesktop = new JDesktopPane();
        screenDesktop.setBackground(bgColor);
        JInternalFrame jif = new JInternalFrame();
        jif.setSize(700, 600);
        jif.setVisible(true);
        screenDesktop.add(jif);
        screenScroller = new JScrollPane();
        screenScroller.getViewport().add(screenDesktop);
        statusDisplay = new JLabel("LABEL LABEL");
        statusDisplay.setPreferredSize(new Dimension(400, 30));
        statusPanel = new JPanel();
        statusPanel.setBackground(bgColor);
        statusPanel.add(statusDisplay);
        screenStatus = new JPanel();
        screenStatus.add(statusPanel);
        screenPanel = new JPanel(new BorderLayout());
        screenPanel.add("Center", screenScroller);
        screenPanel.add("South", screenStatus);
        getContentPane().add(screenPanel);
    }
    ;
    /**
     *
     */
    public void showScreen () {
        pack();
        show();
    }
    ;}




