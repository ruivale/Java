package exp.images.colorredution;

/*
 * @(#)TestQuantize.java    0.90 9/19/00 Adam Doppelt
 */

import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 * Test color quantization of an image.
 *
 * <p><b>Usage: Test [image file] [# colors] [# colors] ...</b><p>
 *
 * For example:
 *
 *   <pre>java quantize.TestQuantize gub.jpg 100 50 20 10</pre>
 *
 * will display gub.jpg with 100, 50, 20, and 10 colors.
 *
 * @version 0.90 19 Sep 2000
 * @author <a href="http://www.gurge.com/amd/">Adam Doppelt</a>
 */
public class TestQuantize {
  /**
   * Snag the pixels from an image.
   */
  static int[][] getPixels(Image image) throws IOException {
    int w = image.getWidth(null);
    int h = image.getHeight(null);
    int pix[] = new int[w * h];
    PixelGrabber grabber = new PixelGrabber(image, 0, 0, w, h, pix, 0, w);

    try {
      if (grabber.grabPixels() != true) {
        throw new IOException("Grabber returned false: " +
                              grabber.status());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    int pixels[][] = new int[w][h];
    for (int x = w; x-- > 0; ) {
      for (int y = h; y-- > 0; ) {
        pixels[x][y] = pix[y * w + x];
      }
    }

    return pixels;
  }

  public static void main(String args[]) throws IOException {

    String a[] = {"D:/temp/logo_sem_efacec.gif", "256", "128", "64", "32", "16", "8"};
    args = a;

    ImageFrame original = new ImageFrame();
    original.setImage(new File(args[0]));
    original.setTitle("original");

    int x = 100;
    int y = 100;
    original.setLocation(x, y);

    for (int i = 1; i < args.length; ++i) {
      x += 20;
      y += 20;
      int pixels[][] = getPixels(original.getImage());
      long tm = System.currentTimeMillis();

      // quant
      int palette[] = Quantize.quantizeImage(pixels, Integer.parseInt(args[i]));
      tm = System.currentTimeMillis() - tm;
      System.out.println("reduced to " + args[i] + " in " + tm + "ms");
      ImageFrame reduced = new ImageFrame();
      reduced.setImage(palette, pixels);

      reduced.setTitle(args[i] + " colors");
      reduced.setLocation(x, y);
    }
  }
}
