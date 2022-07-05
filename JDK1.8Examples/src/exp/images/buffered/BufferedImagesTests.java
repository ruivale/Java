package exp.images.buffered;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;


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
public class BufferedImagesTests {
  public BufferedImagesTests() {
    try {
      final Image img = new ImageIcon("D:/temp/logo_sem_efacec.gif").getImage();
      final BufferedImage buffImage =
          new BufferedImage(
              img.getWidth(null),
              img.getHeight(null),
              BufferedImage.TYPE_INT_RGB);

      // Draw Image into BufferedImage
      final Graphics gBI = buffImage.getGraphics();
      gBI.drawImage(img, 0, 0, null);



      JFrame f_ = new JFrame();
      f_.getContentPane().setLayout(new BorderLayout());

      f_.getContentPane().add(new JLabel(new ImageIcon(buffImage)));

      f_.pack();
      f_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f_.setVisible(true);


    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    new BufferedImagesTests();
  }
}
