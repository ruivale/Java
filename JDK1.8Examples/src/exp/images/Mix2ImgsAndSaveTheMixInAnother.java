// %1216308268008:%
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exp.images;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.*;


/**
 *
 * I THINK THIS CLASS DONT WORK ... ;-)
 * 
 *
 * @author c2334
 */
public class Mix2ImgsAndSaveTheMixInAnother {

  /**
   *
   *
   * @param args 
   */
  public static void main(String[] args) {
    args = new String[]{
      "D:/temp/dome.gif",
      "D:/temp/dome_locked.gif",
      "D:/temp/dome_locked_mixed.gif"
    };
    File f1 = new File(args[0]);
    BufferedImage biOne;
    BufferedImage biTwo;
    BufferedImage bOutput = null;

    try {
      biOne = ImageIO.read(f1);

      BufferedImage firstPart =
          biOne.getSubimage(
          0,
          0,
          biOne.getWidth(),
          biOne.getHeight() /
          2);

      bOutput =
          new BufferedImage(
          biOne.getWidth(),
          biOne.getHeight(),
          BufferedImage.TYPE_INT_RGB);

      File f2 = new File(args[1]);

      biTwo = ImageIO.read(f2);

      BufferedImage seccondPart =
          biTwo.getSubimage(
          0,
          biTwo.getHeight() /
          2,
          biOne.getWidth(),
          biTwo.getHeight() /
          2);

      //Here you take the graphics object out of the buffered image
      // and then you are able to do pretty much with it.
      Graphics2D g = bOutput.createGraphics();

      g.drawImage(
          biOne,
          new AffineTransform(),
          null);
      g.drawImage(
          biTwo,
          new AffineTransform(
          1f,
          0f,
          0f,
          1f,
          0f,
          (float) biOne.getHeight() /
          2),
          null);
    } catch(final IOException e) {
      System.out.println(e.getMessage());
    }

    //Here is the output
    try {
      File f = new File(args[2]);

      ImageIO.write(
          bOutput,
          "jpg",
          f);
    } catch(final IOException e) {
      System.out.println(e.getMessage());
    }
  }
}