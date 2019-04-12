/**
 * <p>
 * Classname:  jdk1_6examples.java.awt.splash.SplashScreenDemo
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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
package jdk1_6examples.java.awt.splash;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * We need to set command line parameter -splash as follows

java -splash:images/Splash_inoss.gif SplashScreenDemo




When we make jar bundle that time we need to add Splash Screen entry into manifest.mf file in jar

Manifest-Version: 1.0
Main-Class: SplashScreenDemo
SplashScreen-Image: images/splash.jpg




Tip: This Feature only works latest Java 6 Version

final SplashScreen splash = SplashScreen.getSplashScreen();
    if (splash == null) {
     System.out.println("Splash not supported");
    } else {
     g = splash.createGraphics();
     renderSplashFrame(g, 1, "Iniitiating");
     splash.update();
    }


Its retrieve the Splash Screen image mentions in command line parameter. and make the SplashScreen object.
If that object is null then Ur JRE cant support splash screen.
if its supports we construct the Graphics Object then we can process like normal paint method.
and we need to call update method, if anything is modified in graphics.
 *
 * 
 * @author 2334
 */
public class SplashScreenDemo {

  private static JFrame wkFrame;

  public SplashScreenDemo() {
  }

  /**
   * @param args
   */
  public static void main(final String[] args) {



    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        Graphics2D g = null;

        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
          System.out.println("Splash not supported");
        } else {
          g = splash.createGraphics();
          renderSplashFrame(g, 1, "Iniitiating");
          splash.update();
        }

        // Create the top-level container and add contents to it.
        wkFrame = new JFrame("Simple Splash Screen Test");

        // SwingUtilities.updateComponentTreeUI(wkFrame);

        if (g != null) {
          renderSplashFrame(g, 2, "Loading Core Components");
          splash.update();
        }

        // Load and set an image as icon for the frame
        Image image = Toolkit.getDefaultToolkit().createImage("images/InossIcon.gif");
        wkFrame.setIconImage(image);

        if (g != null) {
          renderSplashFrame(g, 3, "Loading Core Components");
          splash.update();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        wkFrame.setSize(screenSize);

        if (g != null) {
          renderSplashFrame(g, 4, "Set Dimension");
          splash.update();
        }

        wkFrame.setLocation(0, 0);

        if (g != null) {
          renderSplashFrame(g, 5, "Set Component Position");
          splash.update();
        }

        // do some work

        if (g != null) {
          renderSplashFrame(g, 6, "Loading MenuBar");
          splash.update();
        }

        // do some work

        if (g != null) {
          renderSplashFrame(g, 7, "Loading ToolBar");
          splash.update();
        }

        // do some work

        if (g != null) {
          renderSplashFrame(g, 8, "Loading Main part");
          splash.update();
        }

        // do some work

        if (g != null) {
          renderSplashFrame(g, 9, "Loading Status Bar");
          splash.update();
        }

        // Finish setting up the frame, and show it.
        wkFrame.addWindowListener(new WindowAdapter() {

          @Override
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });

        wkFrame.setExtendedState(wkFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        if (g != null) {
          renderSplashFrame(g, 10, "Finish");
          splash.update();
        }

        wkFrame.setVisible(true);

      }
    });

  }

  private static void renderSplashFrame(Graphics2D g,
                                        int frame,
                                        String content) {

    g.setComposite(AlphaComposite.Src);
    g.setColor(Color.GREEN);
    g.fillRect(258, 349, 20 * frame, 10);
    g.setComposite(AlphaComposite.Clear);
    g.fillRect(258, 330, 200, 20);
    g.setPaintMode();
    g.setColor(Color.BLACK);

    g.drawString(content, 258, 340);

  }
}
