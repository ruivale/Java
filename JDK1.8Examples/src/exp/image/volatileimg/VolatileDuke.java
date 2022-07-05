package exp.image.volatileimg;

/*
 * @(#)VolatileDuke.java 1.3 01/03/05
 *
 * Copyright 2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.image.VolatileImage;
import java.net.URL;

/**
 * VolatileDuke
 *
 * This test app displays 2 versions of the same applet, running
 * together. One app uses a volatile back-buffer; the other uses
 * a non-volatile backbuffer.
 2 BookTitle \u2022 Month 2000
 *
 * The basic applet loads the duke.gif image, copies it into a sprite
 * image, creates a back bufffer and then renders frames. Each frame
 * rendering consists of some rendering
 * to the back buffer (coloring the background and writing some text
 * into it), copying the sprite to a different location in the back
 * buffer, and then copying the back buffer onto the screen.
 *
 * This app can also be run in "performance" mode by using the -perf
 * flag. This mode runs the 2 apps in
 * turn at some number of iterations each. Each test is timed and the
 * results are printed on the command line. Running VolatileDuke in this
 * mode is the only way to get a true performance comparison of
 * the various different image configurations; due to differences in
 * thread implementations on various platforms, running both applets
 * together (as in the default case of VolatileDuke) will not necessarily
 * give the results you expect.
 *
 */
public class VolatileDuke
    extends JApplet implements Runnable {
  Image dukeImage;
  private static int windowW = 500, windowH = 500;
  private static int spriteW, spriteH;
  private Thread thread;
  private int xLoc = 0, yLoc = 0;
  private int xStep = 7, yStep = 3;
  Image sprite;
  Image backBuffer;
  boolean bbVolatile = false;
  static String volatileString = "Volatile";
  static String nonVolatileString = "Non-Vol";
  String bbString;
  int width, height;

  public VolatileDuke(boolean bbVolatile) {
    super();
    this.bbVolatile = bbVolatile;
    if (bbVolatile) {
      bbString = volatileString;
    } else {
      bbString = nonVolatileString;
    }
  }

  /**
   * Load the duke.gif image, create the sprite and back buffer
   * images, and render the content into the sprite.
   */
  public synchronized void initOffscreen() {
    if (dukeImage == null) {
      //final String strImgPath = "D:/TakeHome/Private/Space/mars/PIA05570.jpg";
      final String strImgPath = "D:/temp/logo_sem_efacec.gif";

      dukeImage = new ImageIcon(strImgPath).getImage();

      spriteW = dukeImage.getWidth(null);
      spriteH = dukeImage.getHeight(null);
      sprite = createImage(spriteW, spriteH);
      restoreSpriteContent();
    }
    if (backBuffer == null ||
        width != getWidth() ||
        height != getHeight()) {
      width = getWidth();
      height = getHeight();
      if (bbVolatile) {
        backBuffer = createVolatileImage(width, height);
      } else {
        backBuffer = createImage(width, height);
      }
    }
  }

  /**
   * Prepare the sprite location variables for the next frame
   */
//4 BookTitle \u2022 Month 2000
  public void step() {
    xLoc += xStep;
    yLoc += yStep;
    if (xLoc < 0) {
      xLoc = xStep;
      xStep = -xStep;
    } else if ( (xLoc + spriteW) > getWidth()) {
      xLoc = getWidth() - spriteW - xStep;
      xStep = -xStep;
    }
    if (yLoc < 0) {
      yLoc = yStep;
      yStep = -yStep;
    } else if ( (yLoc + spriteH) > getHeight()) {
      yLoc = getHeight() - spriteH - yStep;
      yStep = -yStep;
    }
  }

  /**
   * For any of our images that are volatile, if the contents of
   * the image have been lost since the last reset, reset the image
   * and restore the contents.
   */
  public void resetRestoreVolatileImages() {
    GraphicsConfiguration gc = this.getGraphicsConfiguration();
    if (bbVolatile) {
      int valCode = ( (VolatileImage) backBuffer).validate(gc);
      if (valCode == VolatileImage.IMAGE_INCOMPATIBLE) {
        backBuffer = createVolatileImage(width, height);
      }
    }
  }

  /**
   * Renders the sprite that we will use. We fill the sprite
   * with a background color and copy in the image that we loaded,
   Appendix A VolatileDuke.java 5
   */
  public void restoreSpriteContent() {
    Graphics2D g2 = (Graphics2D) sprite.getGraphics();
    g2.setColor(Color.green);
    g2.fillRect(0, 0, spriteW, spriteH);
    g2.drawImage(dukeImage, null, null);
    g2.setColor(Color.black);
    g2.dispose();
  }

  /**
   * Rendering loop of the applet.
   * We loop on rendering into the back buffer and copying
   * that buffer onto the screen. The inner loop should usually iterate
   * only once; it will repeat if there is a problem with any of the
   * volatile surfaces.
   */
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    initOffscreen();
    try {
      do {
        resetRestoreVolatileImages();
        Graphics2D gBB = (Graphics2D) backBuffer.getGraphics();
        gBB.setColor(Color.green);
        gBB.fillRect(1, 1, getWidth() - 1, getHeight() - 1);
        gBB.setColor(Color.black);
        gBB.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        gBB.drawString(bbString, 5, getHeight() - 1);
        gBB.drawImage(sprite, xLoc, yLoc, this);
        gBB.dispose();
        g.drawImage(backBuffer, 0, 0, this);
      } while (bbVolatile &&
               ( (VolatileImage) backBuffer).contentsLost());
    } catch (Exception e) {
      System.out.println("Exception during paint: " + e);
    }
//6 BookTitle \u2022 Month 2000
  }

  public void start() {
    thread = new Thread(this);
    thread.setPriority(Thread.MIN_PRIORITY);
    thread.start();
  }

  public synchronized void stop() {
    thread = null;
  }

  public void run() {
    Thread me = Thread.currentThread();
    while (thread == me) {
      step();
      Graphics g = getGraphics();
      update(g);
      g.dispose();
    }
    thread = null;
  }

  public long runPerf(int iterations) {
    Graphics g = getGraphics();
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < iterations; ++i) {
      step();
      update(g);
    }
//Appendix A VolatileDuke.java 7
    Toolkit.getDefaultToolkit().sync();
    return System.currentTimeMillis() - startTime;
  }

  public static void main(String argv[]) {
    Frame f = new Frame("Java 2D(TM) Demo - VolatileDuke");
    if (argv.length > 0) {
      if (argv[0].equals("-perf")) {
        f.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });
        System.out.println("VolatileDuke Performance Test\n");
        System.out.println(" Back-Buffer Milliseconds");
        System.out.println(" ----------- ------------");
        VolatileDuke demo = new VolatileDuke(false);
        for (int i = 0; i < 2; i++) {
          if (i != 0) {
            demo = new VolatileDuke(true);
          }
          f.add(demo);
          f.pack();
          f.setSize(new Dimension(windowW, windowH));
          f.show();
          long perfTime = demo.runPerf(500);
          if (i == 0) {
            System.out.print(" non-volatile");
          } else {
            System.out.print(" volatile ");
          }
          System.out.println(" " + perfTime);
          f.remove(demo);
        }
        System.exit(0);
      } else {
        System.out.println("Usage: java VolatileDuke " +
                           "[-perf]");
        return;
      }
    } else {
      f.setLayout(new GridLayout(1, 2));
      final VolatileDuke demo1 = new VolatileDuke(false);
      f.add(demo1);
      final VolatileDuke demo2 = new VolatileDuke(true);
      f.add(demo2);
      f.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }

        public void windowDeiconified(WindowEvent e) {
          demo1.start();
          demo2.start();
        }

        public void windowIconified(WindowEvent e) {
          demo1.stop();
          demo2.stop();
        }
      });
      f.pack();
      f.setSize(new Dimension(windowW, windowH));
      f.show();
      demo1.start();
      demo2.start();
    }
  }
}
