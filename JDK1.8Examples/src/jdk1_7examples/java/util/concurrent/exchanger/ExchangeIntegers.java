/**
 * <p> Classname: jdk1_7examples.java.util.concurrent.exchanger.ExchangeIntegers </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A. <br> This software is the confidential and
 * proprietary information of EFACEC Eng. Sistemas. You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into EFACEC SE. </p> <p>Company:
 * EFACEC Eng. Sistemas <br> Rua Eng.º Frederico Ulrich – Ap. 3078 <br> 4471-907 Moreira da Maia <br> PORTUGAL <br>
 * Tel: +351 22 940 2000 <br> Fax: +351 22 948 5428 <br> Web: www.efacec.pt <br> Email: te@efacec.pt </p>
 */
package jdk1_7examples.java.util.concurrent.exchanger;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * <p> Description: </p>
 *
 * Created on Jan 11, 2012, 3:41:40 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ExchangeIntegers {

  public static void main(String[] args) {
    final Exchanger<Integer> e = new Exchanger<Integer>();
    new Thread(new Runnable() {

      private final AtomicReference<Integer> last = new AtomicReference<Integer>(1);

      @Override
      public void run() {
        try {
          int i = 0;
          while (i++ < 5) {
            last.set(e.exchange(last.get()));
            System.out.println("Thread A has value: " + last.get());
            Thread.sleep(2000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    new Thread(new Runnable() {

      private final AtomicReference<Integer> last = new AtomicReference<>(2);

      @Override
      public void run() {
        try {
          int i = 0;
          while (i++ < 5) {
            last.set(e.exchange(last.get()));
            System.out.println("Thread B has value: " + last.get());
            Thread.sleep(2000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}