/**
 * <p>
 * Classname: exp.lang.exit.SecurityManagerOverridingTheExit
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
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

package exp.lang.exit;


import java.security.Permission;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Sep 20, 2013, 4:41:00 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class SecurityManagerOverridingTheExit {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(SecurityManagerOverridingTheExit.class.getName());


 /**
  * The SecurityManagerOverridingTheExit default constructor.
  */
  public SecurityManagerOverridingTheExit(){

    // install security manager to avoid System.exit() call from lib
    SecurityManager previousSecurityManager = System.getSecurityManager();

    final SecurityManager securityManager = new SecurityManager() {
      @Override
      public void checkPermission(final Permission permission) {
        if (permission.getName() != null && permission.getName().startsWith("exitVM")) {
          System.out.println("\nU wish ... ;-)\n");
          throw new SecurityException();
        }
      }
    };
    System.setSecurityManager(securityManager);

    try {
      // Call here your lib code
      try {Thread.sleep(2000);} catch (InterruptedException interruptedException) {}

      System.exit(0);

    } catch (SecurityException e) {
      // Say hi to your favorite creator of closed source software that includes System.exit() in his code.
      System.out.println("The sys didn't allow me to exit ... DAMM system. ahahahahahah");

    } finally {
      //System.setSecurityManager(previousSecurityManager);

      //System.exit(0);
    }
  }

  public static void main(final String[] args){

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final SecurityManagerOverridingTheExit clazz = new SecurityManagerOverridingTheExit();
      }
    });

  }
}
