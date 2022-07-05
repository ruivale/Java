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
import java.awt.color.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class ImageTest
    extends JPanel {
  private int m_width = 64;
  private int m_height = 48;

  // image creation
  private BufferedImage m_image;
  private VolatileImage m_vImage;

  public ImageTest() {
    short[] buffer = new short[640 * 480];
    for (int i = 0; i < 24; ++i) {
      for (int j = 0; j < 640; ++j) {
        buffer[i * 640 + j] = (short) 65535;
      }
    }
    byte[] red = new byte[65536];
    byte[] green = new byte[65536];
    byte[] blue = new byte[65536];
    DataBufferShort dataBuffer;
    ComponentSampleModel sampleModel;
    for (int i = 0; i < 65536; ++i) {
      red[i] = (byte) (i >> 8);
      green[i] = (byte) ( (i >> 8 & 0xf0) | (i >> 4 & 0x0f));
      blue[i] = (byte) ( (i >> 8 & 0xf0) | (i & 0x0f));
    }
    IndexColorModel colorModel = new IndexColorModel(16, 65536, red, green,
        blue);
    //ComponentColorModel colorModel = new ComponentColorModel (ColorSpace.getInstance (ColorSpace.CS_GRAY), new int[] {16}, false, false, Transparency.OPAQUE, DataBuffer.TYPE_USHORT);
    dataBuffer = new DataBufferShort(buffer, buffer.length);

    sampleModel = new ComponentSampleModel(DataBuffer.TYPE_USHORT, 64, 48, 1,
                                           640, new int[] {0});
    WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer,
        new Point(0, 0));
    m_image = new BufferedImage(colorModel, raster, false, null);
  }

  // rendering to the image
  void renderOffscreen() {
    do {
      if (m_vImage == null ||
          m_vImage.validate(getGraphicsConfiguration()) ==
          VolatileImage.IMAGE_INCOMPATIBLE) {
        // old vImg doesn't work with new GraphicsConfig; re-create it
        m_vImage = createVolatileImage(m_width * 2, m_height * 2);
      }
      Graphics2D g = m_vImage.createGraphics();
      //
      // miscellaneous rendering commands...
      //
      g.drawImage(m_image, 0, 0, m_width * 2, m_height * 2, null);
      //g.drawImage (m_image, 0, 0, m_width, m_height, null);
      g.dispose();
    } while (m_vImage.contentsLost());
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // copying from the image (here, gScreen is the Graphics
    // object for the onscreen window)
    do {
      if (m_vImage == null) {
        renderOffscreen();
      } else {
        int returnCode = m_vImage.validate(getGraphicsConfiguration());
        if (returnCode == VolatileImage.IMAGE_RESTORED) {
          // Contents need to be restored
          renderOffscreen(); // restore contents
        } else if (returnCode == VolatileImage.IMAGE_INCOMPATIBLE) {
          // old vImg doesn't work with new GraphicsConfig; re-create it
          m_vImage = createVolatileImage(m_width, m_height);
          renderOffscreen();
        }
      }
      g.drawImage(m_vImage, 0, 0, this);
    } while (m_vImage.contentsLost());
  }

  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.getContentPane().add(new ImageTest());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(640, 480);
    f.show();
  }
}
