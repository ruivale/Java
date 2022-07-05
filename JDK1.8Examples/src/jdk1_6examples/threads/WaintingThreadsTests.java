/**
 * <p>
 * Classname:  jdk1_6examples.threads.WaintinThreadsTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
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

package jdk1_6examples.threads;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class WaintingThreadsTests {


  public static void main(final String[] args) throws Exception{
    final Object obj = new Object();

    //final Thread t = new Thread(new XPTO(obj));
    final XPTOThread t = new XPTOThread(obj);

    synchronized(obj){
      t.start();
      System.out.println("Going to wait ...");
      obj.wait(2000);
      System.out.println("After the wait ...");
    }   
  }
}


class XPTO implements Runnable {

  final Object obj;

  public XPTO(final Object obj) {
    this.obj = obj;
  }

  public void run() {

    try {
      for (int j = 0; j < 50; j++) {
        System.out.println(".");
        Thread.sleep(250);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println("NotifyAll...");
      synchronized (obj) {
        obj.notifyAll();
      }
    }

  }
}


class XPTOThread extends Thread {

  final Object obj;

  public XPTOThread(final Object obj) {
    this.obj = obj;
  }

  public void run() {

    try {
      for (int j = 0; j < 50; j++) {
        System.out.println(".");
        Thread.sleep(250);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println("NotifyAll...");
      synchronized (obj) {
        obj.notifyAll();
      }
    }

  }
}
