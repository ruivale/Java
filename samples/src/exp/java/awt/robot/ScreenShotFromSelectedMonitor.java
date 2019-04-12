/**
 * <p> Classname: exp.java.awt.robot.ScreenShotFromSelectedMonitor </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A. <br> This software is the
 * confidential and proprietary information of EFACEC Eng. Sistemas. You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms of the license agreement
 * you entered into EFACEC SE. </p> <p>Company: EFACEC Eng. Sistemas <br> Rua Eng.º Frederico Ulrich ?
 * Ap. 3078 <br> 4471-907 Moreira da Maia <br> PORTUGAL <br> Tel: +351 22 940 2000 <br> Fax: +351 22 948
 * 5428 <br> Web: www.efacec.pt <br> Email: te@efacec.pt </p>
 */
package exp.java.awt.robot;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


/**
 * <p> Description: </p>
 *
 * Created on Mar 9, 2012, 4:12:51 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ScreenShotFromSelectedMonitor {

  public static void main(String args[]) {
    Graphics2D graphics2DImage = null;

    try {
      final Robot robot = new Robot();
      final GraphicsDevice graphicsDeviceCurrent = MouseInfo.getPointerInfo().getDevice();
      final BufferedImage imageExport =
          robot.createScreenCapture(graphicsDeviceCurrent.getDefaultConfiguration().getBounds());

      graphics2DImage = (Graphics2D) imageExport.getGraphics();

      final File fileScreenshot =
          new File("./CurrentMonitorScreenshot-" + System.currentTimeMillis() + ".png");

      ImageIO.write(imageExport, "png", fileScreenshot);

      System.out.println("Screenshot captured to '" + fileScreenshot.getCanonicalPath()+ "'!");

    } catch (Exception exp) {
      exp.printStackTrace();
    } finally {
      graphics2DImage.dispose();
    }
  }
}
