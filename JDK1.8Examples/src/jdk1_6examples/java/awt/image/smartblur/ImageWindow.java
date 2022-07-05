/**
 * <p>
 * Classname:  jdk1_6examples.java.awt.image.smartblur.ImagePanel
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.java.awt.image.smartblur;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class ImageWindow {

// This inner class is our canvas.
// We draw the image on it.
  class ImagePanel extends JComponent {

    BufferedImage theImage = null;

    ImagePanel(BufferedImage image) {
      super();
      theImage = image;
    }

    public BufferedImage getImage() {
      return theImage;
    }

    public void setImage(BufferedImage image) {
      theImage = image;
      this.updatePanel();
    }

    public void updatePanel() {

      invalidate();
      getParent().doLayout();
      repaint();
    }

    public void paintComponent(Graphics g) {

      int w = theImage.getWidth();
      int h = theImage.getHeight();

      g.drawImage(theImage, 0, 0, w, h, this);
    }
  }  // end ImagePanel inner class

// Constructor
  public ImageWindow(String[] args) {

    // open image
    BufferedImage image = openImageFile(args[0]);

    // create a panel for it
    ImagePanel theImagePanel = new ImagePanel(image);

    // display the panel in a JFrame
    createWindowForPanel(theImagePanel, args[0]);

    // filter the image
    filterImage(theImagePanel);
  }

  public void filterImage(ImagePanel panel) {

    SmartBlurFilter filter = new SmartBlurFilter();

    BufferedImage newImage = filter.filter(panel.getImage());

    panel.setImage(newImage);
  }

  public void createWindowForPanel(ImagePanel theImagePanel, String name) {

    BufferedImage image = theImagePanel.getImage();
    JFrame mainFrame = new JFrame();
    mainFrame.setTitle(name);
    mainFrame.setBounds(50, 80, image.getWidth() + 10, image.getHeight() + 10);
    mainFrame.setDefaultCloseOperation(3);
    mainFrame.getContentPane().add(theImagePanel);
    mainFrame.setVisible(true);
  }

  BufferedImage openImageFile(String fname) {

    BufferedImage img = null;

    try {
      File f = new File(fname);
      if (f.exists()) {
        img = ImageIO.read(f);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return img;
  }

  public static void main(String[] args) {
    final String[] ss = {"D:/Projects/JDK1.6Examples/images/dome_on_highrec.gif"};
    new ImageWindow(ss);
  }
}
