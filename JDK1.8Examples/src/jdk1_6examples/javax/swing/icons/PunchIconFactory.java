/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.icons.PunchIconFactory
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
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
package jdk1_6examples.javax.swing.icons;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class PunchIconFactory {

  /** This class LOGGER */
  private static final Logger LOGGER =
                              Logger.getLogger(PunchIconFactory.class.getName());

  public static Icon createPunchedIcon(Image image,
                                       int unblurredShadowSize_pixels) {
    // create an image in which to draw the given image with the inner
    // shadow.
    BufferedImage newImage = new BufferedImage(image.getWidth(null),
                                               image.getHeight(null),
                                               BufferedImage.TYPE_4BYTE_ABGR);

    // 1) paint a gradient background in the resultant image.
    Graphics2D graphics = newImage.createGraphics();
    GradientPaint paint = new GradientPaint(
        0, 0, Color.BLACK, 0, image.getHeight(null), new Color(0x3e3e3e));
    graphics.setPaint(paint);
    graphics.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());

    // 2) paint the given image into resultant image, only keeping pixels that
    //    existed in the given image.
    graphics.setComposite(AlphaComposite.DstIn);
    graphics.drawImage(image, 0, 0, null);

    // 3) create an inner shadow for the given image.
    BufferedImage shadowImage =
                  createInnerShadow(image, unblurredShadowSize_pixels);
    graphics.setComposite(AlphaComposite.SrcAtop);
    graphics.drawImage(shadowImage, 0, 0, null);

    graphics.dispose();

    return new ImageIcon(newImage);
  }

  private static BufferedImage createInnerShadow(
      Image image,
      int unblurredShadowSize_pixels) {
    // create an image padded by the shadow size. this allows the given
    // image to abut the edge and still receive an inner shadow. if we don't
    // do this, an image that abuts the edge will have a shadow that is
    // blurred into full alpha transparency, when it should in fact be opaque.
    int twiceShadowSize = unblurredShadowSize_pixels * 2;
    BufferedImage punchedImage = new BufferedImage(
        image.getWidth(null) + twiceShadowSize,
        image.getHeight(null) + twiceShadowSize,
        BufferedImage.TYPE_INT_ARGB);

    // 1) start by filling the entire rectangle with black.
    Graphics2D graphics = punchedImage.createGraphics();
    graphics.setColor(new Color(0, 0, 0, 140));
    graphics.fillRect(0, 0, punchedImage.getWidth(), punchedImage.getHeight());
    // 2) next erase the given image from the previously drawn rectangle. this
    //    punches out the image from the rectangle, which will let the "light"
    //    flow through when we create the drop shadow. note that we're moving
    //    down and to left shadowSize pixels to compensate for the pad, then
    //    another shadowSize pixels to offset the image.
    graphics.setComposite(AlphaComposite.DstOut);
    graphics.drawImage(image, twiceShadowSize, twiceShadowSize, null);
    graphics.dispose();

    // create a drop shadow for the punched out image.
    BufferedImage innerShaodowImage = createLinearBlurOp(
        unblurredShadowSize_pixels).filter(punchedImage, null);

    // return an image of the original size. we're subtracting off the pad
    // that we added in the beginning which was only used to allow images
    // that abut the edge.
    return innerShaodowImage.getSubimage(unblurredShadowSize_pixels,
                                         unblurredShadowSize_pixels,
                                         punchedImage.getWidth() -
                                         twiceShadowSize,
                                         punchedImage.getHeight() -
                                         twiceShadowSize);
  }

  private static ConvolveOp createLinearBlurOp(int size) {
    float[] data = new float[size * size];
    float value = 1.0f / (float) (size * size);
    for (int i = 0; i < data.length; i++) {
      data[i] = value;
    }
    return new ConvolveOp(new Kernel(size, size, data));
  }


  public static void main(String[] args) {
    final Image image =
        new ImageIcon("D:/Projects/JDK1.6Examples/images/InossIcon.gif").getImage();
    final Icon icon = PunchIconFactory.createPunchedIcon(
        image,
        25);

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.add(new JButton(icon), BorderLayout.CENTER);
        f.setBounds(100,100,600,350);
        f.setVisible(true);


      }
    });

  }
}
