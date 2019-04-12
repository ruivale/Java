/**
 * <p>
 * Classname: exp.images.CreateResizedImgFromOriginal
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package exp.images;

import exp.images.colorredution.ImageFrame;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Feb 8, 2018, 10:42:19 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class CreateResizedImgFromOriginal extends JFrame {

  int left = -1;
  int top;
  Image image;

  /**
   *
   */
  public CreateResizedImgFromOriginal(final String strFileAbsPath){
    setLayout(null);
    setBounds(100, 100, 300, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try {
      setImage(new File(strFileAbsPath));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Set the image from a file.
   */
  public void setImage(File file) throws IOException {
    // load the image
    Image image = getToolkit().getImage(file.getAbsolutePath());

    // wait for the image to entirely load
    MediaTracker tracker = new MediaTracker(this);
    tracker.addImage(image, 0);
    try {
      tracker.waitForID(0, 5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (tracker.statusID(0, true) != MediaTracker.COMPLETE) {
      throw new IOException("Could not load: " + file + " " + tracker.statusID(0, true));
    }

    setTitle(file.getName());
    setImage(image);
  }

  /**
   * Set the image from an AWT image object.
   */
  private void setImage(Image image) {
    this.image = image;
    setVisible(true);
  }
  /**
   * Set the image from a 1D RGB pixel array.
   */
  private void setImage(int w, int h, int pix[]) {
    setImage(createImage(new MemoryImageSource(w, h, pix, 0, w)));
  }
  /**
   * Overridden for double buffering.
   */
  public void update(Graphics g) {
    paint(g);
  }

  /**
   * Paint the image.
   */
  public void paint(Graphics g) {
    // the first time through, figure out where to draw the image
    if (left == -1) {
      Insets insets = getInsets();
      left = insets.left;
      top = insets.top;

      setSize(image.getWidth(null) + left + insets.right,
              image.getHeight(null) + top + insets.bottom);
    }
    g.drawImage(image, left, top, this);
  }




  /**
   *
   * @param strIconPath
   * @param strResizedIconPath
   * @param width
   * @param height
   */
  public static void createResizedImgFromOriginal(
      final String strIconPath,
      final String strResizedIconPath,
      final int width,
      final int height) {

    try {
      ImageIcon imgIconResized = new ImageIcon(strResizedIconPath);

      // if the new (w,h) R != || there's no file for that path... created 1!
      if (!new File(strResizedIconPath).exists() ||
          (imgIconResized.getIconWidth() != width && imgIconResized.getIconHeight() != height)) {

        imgIconResized = new ImageIcon(
          new ImageIcon(strIconPath).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

        // Create empty BufferedImage, sized to img
        final BufferedImage buffImage = new BufferedImage(
          imgIconResized.getImage().getWidth(null),
          imgIconResized.getImage().getHeight(null),
          BufferedImage.TYPE_INT_RGB);

        // Draw Image into BufferedImage
        final Graphics gBI = buffImage.getGraphics();
        gBI.drawImage(imgIconResized.getImage(), 0, 0, null);

        final File fileResized = new File(strResizedIconPath);

        ImageIO.write(buffImage, "gif", fileResized);

      } else {
        System.err.println("Will not recreate/resized icon("+strIconPath+") cause it's already there.");
      }

//
//
//
//      final javax.swing.JButton jButon = new javax.swing.JButton(
//        "text",
//        new ImageIcon(strResizedIconPath));
//      final javax.swing.JFrame f = new javax.swing.JFrame("monvw");
//      jButon.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//          final javax.swing.JFrame f = new javax.swing.JFrame("monvw");
//          f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//          f.getContentPane().setLayout(new java.awt.BorderLayout());
//          f.getContentPane().add(
//            new javax.swing.JLabel("text", new ImageIcon(strResizedIconPath), SwingConstants.LEFT),
//            java.awt.BorderLayout.CENTER);
//          f.pack();
//          f.setVisible(true);
//
//        }
//      });
//
//
//
//      final File file = new File(strResizedIconPath);
//
//      Image image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
//
//      // wait for the image to entirely load
//      MediaTracker tracker = new MediaTracker(jButon);
//      tracker.addImage(image, 0);
//      try {
//        System.out.println("Will wait for ID(0, 75000)...");
//        tracker.waitForID(0, 7500);
//        System.out.println("... waitED for ID(0, 75000).");
//
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//
//      if (tracker.statusID(0, true) != MediaTracker.COMPLETE) {
//        throw new IOException("Could not load: " + file + " " + tracker.statusID(0, true));
//      }
//
//
//      f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//      f.getContentPane().setLayout(new java.awt.BorderLayout());
//      f.getContentPane().add(jButon, java.awt.BorderLayout.CENTER);
//      f.pack();
//      f.setVisible(true);



      System.out.println("createResizedImgFromOriginal end...");

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }




  public static void main(final String[] args) {
    try {

      CreateResizedImgFromOriginal.createResizedImgFromOriginal(
        "d:/temp/monvw.gif",
        "d:/temp/monvw_resized.gif",
        36,
        30);

      CreateResizedImgFromOriginal c = new CreateResizedImgFromOriginal("d:/temp/monvw_resized.gif");
      //c.setImage(new File("d:/temp/monvw_resized.gif"));

    } catch (Exception e) {
      e.printStackTrace();
    }


  }
}
