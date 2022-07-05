package exp.images;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;


class BlackWhiteFilter
    extends RGBImageFilter {

  public int filterRGB(int x,
                       int y,
                       int rgb) {
    int alpha = (rgb >>
        24) &
        0xff;
    int red = (int) (((rgb >>
        16) &
        0xff) *
        0.3f);
    int green = (int) (((rgb >>
        8) &
        0xff) *
        0.59f);
    int blue = (int) ((rgb &
        0xff) *
        0.11f);
    int bws = ((red +
        green +
        blue) >
        127) ? 255 : 0;
    return ((alpha &
        0xFF) <<
        24) |
        ((bws &
        0xFF) <<
        16) |
        ((bws &
        0xFF) <<
        8) |
        ((bws &
        0xFF) <<
        0);
  }
}

public class FromColorToBW
    extends JFrame {

  private Image bwImage = null;

  public FromColorToBW() {
    Image colorImage =
        Toolkit.getDefaultToolkit().createImage("d:/Rui/Private/_Cool/a1.jpg");
    ImageFilter filter = new BlackWhiteFilter();
    ImageProducer producer =
        new FilteredImageSource(colorImage.getSource(), filter);
    bwImage = Toolkit.getDefaultToolkit().createImage(producer);
    setSize(200, 200);
    setVisible(true);
  }

  public void paint(Graphics g) {
    super.paint(g);
    g.drawImage(bwImage, 50, 50, this);
  }

  public static void main(String[] args) {
    new FromColorToBW();
  }
}
