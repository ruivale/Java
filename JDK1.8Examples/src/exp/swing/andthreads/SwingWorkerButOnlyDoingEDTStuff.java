/**
 * <p>
 * Classname:  exp.swing.andthreads.SwingWorkerButOnlyDoingEDTStuff
 * </p>
 *
 * <p>Copyright: Copyright (c) 2018 EFACEC SE
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

package exp.swing.andthreads;


import java.util.List;
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
public class SwingWorkerButOnlyDoingEDTStuff {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(SwingWorkerButOnlyDoingEDTStuff.class.getName());


 /**
  * The SwingWorkerButOnlyDoingEDTStuff default constuctor.
  */
  public SwingWorkerButOnlyDoingEDTStuff(){
    
    final javax.swing.SwingWorker swingWorker = new javax.swing.SwingWorker<Boolean, Object> (){

      @Override
      public Boolean doInBackground() {
        try {Thread.sleep(666);} catch (InterruptedException e) {}        
        
        System.out.println("\tdoInBackground(EDT?"+SwingUtilities.isEventDispatchThread()+")...");        
        return true;
      }

      @Override
      protected void done() {
        System.out.println("\tdone(EDT?"+SwingUtilities.isEventDispatchThread()+")...");
        try {
          for(int i=0; i<10; i++){
            try {Thread.sleep(500);} catch (InterruptedException e) {}
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        System.out.println("\t...done(EDT?"+SwingUtilities.isEventDispatchThread()+")");
      }
           
    };
    swingWorker.execute();
    
    System.out.println("swingWorker.execute()...");
    
    while(!swingWorker.isDone()){
      System.out.println("swingWorker NOT DONE YET...");
      try {Thread.sleep(233);} catch (InterruptedException e) {}
    }

    System.out.println("swingWorker DONE...");
  }

  public static void main(final String[] args) throws Exception{
    final SwingWorkerButOnlyDoingEDTStuff clazz = new SwingWorkerButOnlyDoingEDTStuff();
   
    System.out.println("Press any key to exit...");
    System.in.read();
    
  }
}
