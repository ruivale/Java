/**
 * <p>
 * Classname:  jdk1_6examples.java.lang.runtime.ShutdownHook.CatchingShutdownHook
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

package jdk1_6examples.java.lang.runtime.ShutdownHook;


import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class CatchingShutdownHook {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(CatchingShutdownHook.class.getName());


 /**
  * The CatchingShutdownHook default constuctor.
  */
  public CatchingShutdownHook(){

    final JDialog f = new JDialog(new JFrame(), true);
    f.setBounds(100,100,250,250);

    /****/
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        System.out.println("Catched the shutdown hook ... ");

        //System.exit(1);

        try {
          for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            System.out.println(".");
          }
        } catch (InterruptedException interruptedException) {
        }

        
        System.out.println("Realy exit ...");
      }
    });
    /**/

    new Thread(new Runnable() {

      @Override
      public void run() {
        System.out.println("just waiting ...");
      }
    }).start();

    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

    System.out.println("before System.exit(1);");

    System.exit(1);

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("CatchingShutdownHook").append("").toString();
  }

  public static void main(final String[] args){
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        final CatchingShutdownHook clazz = new CatchingShutdownHook();

      }
    });
  }
}
