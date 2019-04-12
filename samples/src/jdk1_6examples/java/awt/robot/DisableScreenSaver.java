/**
 * <p>
 * Classname:  jdk1_6examples.java.awt.robot.DisableScreenSaver
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

package jdk1_6examples.java.awt.robot;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class DisableScreenSaver {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(DisableScreenSaver.class.getName());
  private static volatile boolean mayContinue = true;
  private static Robot robot;// = new Robot();


  /**
   *
   * @throws Exception
   */
  public static void start () throws Exception {

    if(robot == null){
      robot = new Robot();
    }

    while (mayContinue) {
//      Thread.sleep(60 * 1000); // one minute
      if(!mayContinue) break;

      robot.keyPress(KeyEvent.VK_CAPS_LOCK);
      robot.keyPress(KeyEvent.VK_NUM_LOCK);
      robot.keyPress(KeyEvent.VK_SCROLL_LOCK);
      Thread.sleep(50);
      robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
      robot.keyRelease(KeyEvent.VK_NUM_LOCK);
      robot.keyRelease(KeyEvent.VK_SCROLL_LOCK);
      Thread.sleep(250);
      robot.keyPress(KeyEvent.VK_CAPS_LOCK);
      robot.keyPress(KeyEvent.VK_NUM_LOCK);
      robot.keyPress(KeyEvent.VK_SCROLL_LOCK);
      Thread.sleep(50);
      robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
      robot.keyRelease(KeyEvent.VK_NUM_LOCK);
      robot.keyRelease(KeyEvent.VK_SCROLL_LOCK);
      Thread.sleep(250);

//      robot.keyPress(KeyEvent.VK_SHIFT);
//      Thread.sleep(50);
//      robot.keyRelease(KeyEvent.VK_SHIFT);
    }
  }

  /**
   *
   */
  public static void stop(){
    mayContinue = false;
  }




  public static void main(final String[] args) throws Exception {
    DisableScreenSaver.start();
  }
}
