/**
 * <p>
 * Classname: exp.Wandering
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package exp;


import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 */
public class Wandering {

  
  public static void main(final String[] args) throws Exception{
   final int nSleep = 5555;
 
    try {
      final Robot robot = new Robot();
      final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      final GraphicsDevice[] gdlist = ge.getScreenDevices();
      final Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
      System.out.println("\nScreenSize: " + dimScreen);
      final int nbrScreens = gdlist.length;
      Point pointOnScreen;
      
      final boolean canRun = true;
      
      while (canRun) {
        pointOnScreen = MouseInfo.getPointerInfo().getLocation();
        pointOnScreen.setLocation(
            pointOnScreen.x > 0 ? pointOnScreen.x - 1 : 1, 
            pointOnScreen.y > 0 ? pointOnScreen.y - 1 : 1);
        robot.mouseMove(pointOnScreen.x, pointOnScreen.y);
        System.out.println(pointOnScreen);        
        Thread.sleep(nSleep);
        
        pointOnScreen = MouseInfo.getPointerInfo().getLocation();
        pointOnScreen.setLocation(
            pointOnScreen.x < dimScreen.width * nbrScreens - 1 ? 
                pointOnScreen.x + 1 : 
                dimScreen.width * nbrScreens - 2, 
            pointOnScreen.y < dimScreen.height * nbrScreens - 1 ? 
                pointOnScreen.y + 1 : 
                dimScreen.height * nbrScreens - 2);
        robot.mouseMove(pointOnScreen.x, pointOnScreen.y);       
        System.out.println(pointOnScreen);
        Thread.sleep(nSleep);
        
      }
    } catch (HeadlessException headlessException) {
    } catch (InterruptedException interruptedException) {
    }
  }
}
