/**
 * <p>
 * Classname:  exp.robot.RobotMouseMoving
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

package exp.robot;


import java.awt.Dimension;
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
 *
 * @since __DATE__
 */
public class RobotMouseMoving {

  
  public static void main(final String[] args) throws Exception{
   final int nSleep = 5555;
 
    try {
      final Robot robot = new Robot();
      final Dimension dimScreen = Toolkit. getDefaultToolkit(). getScreenSize();
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
            pointOnScreen.x < dimScreen.width - 1 ? pointOnScreen.x + 1 : dimScreen.width - 2, 
            pointOnScreen.y < dimScreen.height - 1 ? pointOnScreen.y + 1 : dimScreen.height - 2);
        robot.mouseMove(pointOnScreen.x, pointOnScreen.y);       
        System.out.println(pointOnScreen);
        Thread.sleep(nSleep);
        
      }
    } catch (HeadlessException headlessException) {
    } catch (InterruptedException interruptedException) {
    }
  }
}
