/**
 * <p>
 * Classname: exp.util.concurrency.ObjSyncTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

package exp.util.concurrency;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 14, 2016, 6:01:34 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ObjSyncTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ObjSyncTests.class.getName());


 /**
  * The ObjSyncTests default constructor.
  */
  public ObjSyncTests(){

    final Thread t1 = new Thread(() -> {
      final Object obj = ObjSyncFactory.getLockableObject();

      System.out.println(Thread.currentThread().getName()+" - will try to sync 1...");

      synchronized(obj){
        System.out.println(Thread.currentThread().getName()+" - synchronized(obj)...");
        try {Thread.sleep(2500);} catch (InterruptedException interruptedException) {}
        System.out.println(Thread.currentThread().getName()+" - ...synchronized(obj)");
      }

      System.out.println(Thread.currentThread().getName()+" - will sleep for 4500 millis...");
      try {Thread.sleep(4500);} catch (InterruptedException interruptedException) {}
      System.out.println(Thread.currentThread().getName()+" - will try to sync 2...");

      synchronized(obj){
        System.out.println(Thread.currentThread().getName()+" - synchronized(obj)...");
        try {Thread.sleep(1250);} catch (InterruptedException interruptedException) {}
        System.out.println(Thread.currentThread().getName()+" - ...synchronized(obj)");
      }
      System.out.println(Thread.currentThread().getName()+" - exiting.");

    }, "Thread1");
    t1.start();


    final Thread t2 = new Thread(() -> {
      final Object obj = ObjSyncFactory.getLockableObject();

      System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - will try to sync 1...");

      synchronized(obj){
        System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - synchronized(obj)...");
        try {Thread.sleep(3500);} catch (InterruptedException interruptedException) {}
        System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - ...synchronized(obj)");
      }

      System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - will sleep for 1200 millis...");
      try {Thread.sleep(1200);} catch (InterruptedException interruptedException) {}
      System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - will try to sync 2...");

      synchronized(obj){
        System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - synchronized(obj)...");
        try {Thread.sleep(4250);} catch (InterruptedException interruptedException) {}
        System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - ...synchronized(obj)");
      }
      System.out.println("\t\t\t\t"+Thread.currentThread().getName()+" - exiting.");

    }, "Thread2");
    t2.start();

  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("ObjSyncTests").append("").toString();
  }

  public static void main(final String[] args){
    final ObjSyncTests clazz = new ObjSyncTests();
  }
}
