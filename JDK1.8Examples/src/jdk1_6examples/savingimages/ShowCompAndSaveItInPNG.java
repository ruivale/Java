/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk1_6examples.savingimages;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Produces a PNG image, a green rhombus with the letters I/O on it, and exports it as a *.png image. This program is
 * not realistic.  It has been greatly simplified for teaching purposes just to show the essential elements. A realistic
 * program would be much more general purpose. See the source code for Masker for a more practical example of png image
 * creation.
 * <p/>
 * composed with IntelliJ IDEA
 *
 * @author Roedy Green, Canadian Mind Products
 * @version 1.0, 2007-05-13.
 */
public final class ShowCompAndSaveItInPNG {
// --------------------------- main() method ---------------------------
  /**
   * Run as a stand-alone application.
   *
   * @param args command line arguments ignored.
   */
  public static void main(String args[]) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    final JFrame jframe = new JFrame("TestPaintCompnent");
    final Container contentPane = jframe.getContentPane();
    jframe.setSize(150, 200);

    // Note that jframe.setBackground is almost useless.
    // You must set the background colour of the contentPane.
    contentPane.setBackground(Color.WHITE);
    contentPane.setForeground(Color.BLUE);
    contentPane.setLayout(new BorderLayout());
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final Rhombus rhombus = new Rhombus();
    rhombus.setBackground(Color.YELLOW);
    contentPane.add(rhombus, BorderLayout.NORTH);

    final JButton save = new JButton("Save");
    save.setBorder(BorderFactory.createRaisedBevelBorder());
    save.setFocusPainted(false);
    save.setToolTipText("Save PNG image as a file.");
    save.setFont(new Font("Dialog", Font.BOLD, 15));
    save.addActionListener(new ActionListener() {

      /**
       * user clicked save.
       *
       * @param event button push event
       */
      public void actionPerformed(ActionEvent event) {
        rhombus.saveImage();
      }// end actionPerformed
    });
    contentPane.add(save, BorderLayout.SOUTH);

    jframe.validate();
    jframe.setVisible(true);
  }
}

final class Rhombus
    extends JPanel {
// ------------------------------ FIELDS ------------------------------
  /**
   * Displays a green rhombus with white I/O lettering on it.
   *
   * @author Roedy Green
   * @version 1.0
   */
  /**
   * height of image
   */
  private static final int height = 128;
  /**
   * width of image
   */
  private static final int width = 128;
  private static final Color lightGreen = new Color(0x30ffaf);

  /* transparent white */
  private static final Color transparent = new Color(0x00ffffff, true);
// -------------------------- PUBLIC INSTANCE  METHODS --------------------------
  /**
   * draws the rhombus
   *
   * @param g graphics region where we paint
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON));
    g2d.setBackground(transparent);
    g2d.clearRect(0, 0, width, height);
    Font font = new Font("Tiresias PCFont Z", Font.BOLD, 40);
    g2d.setFont(font);
    String text = "I/O";
    g2d.setColor(lightGreen);
    g2d.fillPolygon(new int[]{0, 96, 128, 32} /* xs */, new int[]{32, 32, 96, 96} /* ys */, 4);

    FontMetrics fm = this.getFontMetrics(font);
    int textWidth = fm.stringWidth(text);
    FontRenderContext fr = g2d.getFontRenderContext();
    LineMetrics lm = font.getLineMetrics(text, fr);
    int textHeight = (int) (lm.getAscent() *
        .71)/* overstated */;
    g2d.setColor(Color.BLUE);
    g2d.drawString(text,
        width /
        2 -
        textWidth /
        2,
        height /
        2 +
        textHeight /
        2);
  }

  /**
   * save current image as a png.
   */
  public void saveImage() {
    try {
      // repaint onto a BufferedImage
      BufferedImage bufferedImage =
          new BufferedImage(width,
          height,
          BufferedImage.TYPE_4BYTE_ABGR_PRE);
      Graphics2D g2d = bufferedImage.createGraphics();
      // redraw using our paintComponent
      paintComponent(g2d);

      File file = new File("C:/temp", "rhombus.png");
      // write out the BufferedImage as a PNG
      ImageIO.write(bufferedImage, "png" /* format desired */, file);
    } catch(IOException e) {
      System.err.println("image not saved.");
    }
  }

// --------------------------- CONSTRUCTORS ---------------------------
  /**
   * Constructor
   */
  Rhombus() {
    Dimension d = new Dimension(width, height);
    this.setPreferredSize(d);
    this.setMinimumSize(d);
    this.setMaximumSize(d);
  }
}

