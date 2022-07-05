package exp.cursor;

import java.awt.*;

import javax.swing.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class HideCursor extends JFrame {
  public HideCursor() {
    int[] pixels = new int[16 * 16];
    Image image = Toolkit.getDefaultToolkit().createImage(new java.awt.image.MemoryImageSource(
          16, 16, pixels, 0, 16));
    Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image,
        new Point(0, 0), "invisiblecursor");

    this.setCursor(transparentCursor);
  }

  public static void main(String[] args) {
    HideCursor h = new HideCursor();
    h.setBounds(100, 100, 300, 250);
    h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    h.setVisible(true);
  }
}
