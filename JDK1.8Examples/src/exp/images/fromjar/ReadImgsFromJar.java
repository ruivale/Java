package exp.images.fromjar;


import javax.swing.ImageIcon;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.image.BufferedImage;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ReadImgsFromJar {
  public ReadImgsFromJar() {
  }

  /**
   *
   * @param imgName String
   * @return ImageIcon
   */
  private ImageIcon getImage(String imgName) {

    ImageIcon img = null;

    // when image not in jar
    // URL url = getClass().getResource(imgName);
    // img = new ImageIcon(url);


    // when image in jar
    try {
      //InputStream in = getClass().getResource(imgName);
      BufferedImage bi = ImageIO.read(getClass().getResource(imgName));

      // Read from an input stream
      img = new ImageIcon(bi);

    } catch (Exception e1) {
      e1.printStackTrace();
    }

    return img;
  }


  public static void main(final String[] args) {
    new ReadImgsFromJar().getImage("d:\\temp\\FirefoxAddOns.jpg");
  }


}
