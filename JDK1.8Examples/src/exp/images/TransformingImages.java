package exp.images;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TransformingImages {
  public TransformingImages() {
    transformImg();
  }

  private void transformImg() {
    Image image ;

    BufferedImage reg = new BufferedImage(100, 75, BufferedImage.TYPE_INT_ARGB);
    int w = reg.getWidth();
    int h = reg.getHeight();

    BufferedImage dst = new BufferedImage(w, h,
                                          BufferedImage.TYPE_INT_RGB);

    int red = 0;
    int blue = 0;
    int green = 0;

    for (int y = 0; y < reg.getHeight(); ++y) {
      for (int x = 0; x < reg.getWidth(); ++x) {

        int srcPixel = reg.getRGB(x, y);
        Color c = new Color(srcPixel);

        red = (int) Math.round( (255 * c.getRed()) / 255);
        blue = (int) Math.round( (0 * c.getBlue()) / 255);
        green = (int) Math.round( (255 * c.getGreen()) / 255);
        dst.setRGB(x, y, new Color(red, green, blue).getRGB());

      }
    }

  }

  public static void main(String[] args) {
    TransformingImages transformingImages1 = new TransformingImages();
  }

}

