/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.swingutilities.ReturnValueFromInvokeLater
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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
package jdk1_6examples.javax.swing.swingutilities;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ReturnValueFromInvokeLater {

  /** This class LOGGER */
  private static final Logger LOGGER =
      Logger.getLogger(ReturnValueFromInvokeLater.class.getName());

  /**
   * The ReturnValueFromInvokeLater default constuctor.
   */
  public ReturnValueFromInvokeLater() {
  }

  /**
   *
   * @param <T>
   * @param callable
   * @return
   */
  public static <T> FutureTask<T> invokeLater(Callable<T> callable) throws InvocationTargetException{

    if(SwingUtilities.isEventDispatchThread()){
      throw new InvocationTargetException(new Throwable("Cannot call this method inside EDT!"));
    }

    FutureTask<T> task = new FutureTask<T>(callable);

    SwingUtilities.invokeLater(task);

    return task;
  }

  /**
   *
   * @param <T>
   * @param callable
   * @return
   * @throws InterruptedException
   * @throws InvocationTargetException
   */
  public static <T> T invokeAndWait(Callable<T> callable) throws InterruptedException,
                                                                 InvocationTargetException {
    try {
      //blocks until future returns
      return invokeLater(callable).get();
      
    } catch (ExecutionException e) {
      Throwable t = e.getCause();

      if (t instanceof RuntimeException) {
        throw (RuntimeException) t;
      } else if (t instanceof InvocationTargetException) {
        throw (InvocationTargetException) t;
      } else {
        throw new InvocationTargetException(t);
      }
    }
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("ReturnValueFromInvokeLater").append("").toString();
  }

  public static void main(final String[] args) {
    final ReturnValueFromInvokeLater clazz = new ReturnValueFromInvokeLater();
  }
}
