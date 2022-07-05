/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.swingworker.UsingSwingWorker
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
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

package jdk1_6examples.javax.swing.swingworker;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class UsingSwingWorker {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(UsingSwingWorker.class.getName());


  public UsingSwingWorker(){

      SwingWorker worker = new SwingWorker<Integer, Object>() {

        @Override
        protected Integer doInBackground() {
          System.out.println("doInBackground - isEDT?"+SwingUtilities.isEventDispatchThread());
        try {
          Thread.sleep(5000);
        } catch (InterruptedException ex) {
          Logger.getLogger(UsingSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
          return new Integer(234234);
        }

//                @Override
//                protected void process(List<FlipPair> pairs) {
//                    FlipPair pair = pairs.get(pairs.size() - 1);
//                    System.out.println(String.format("%d", pair.heads));
//
//                }
        @Override
        protected void done() {
          System.out.println("done - isEDT?"+SwingUtilities.isEventDispatchThread());
        }
      };
      worker.execute();

  }

  public static void main(final String[] args){
    final UsingSwingWorker clazz = new UsingSwingWorker();
  }
}
