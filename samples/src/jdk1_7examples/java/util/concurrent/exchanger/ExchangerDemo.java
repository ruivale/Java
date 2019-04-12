/**
 * <p>
 * Classname: jdk1_7examples.java.util.concurrent.exchanger.ExchangerDemo
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich – Ap. 3078
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

package jdk1_7examples.java.util.concurrent.exchanger;


import java.util.concurrent.Exchanger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 11, 2012, 3:04:32 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ExchangerDemo {
  public static void main(String args[]) {
    Exchanger<String> exgr = new Exchanger<String>();

    new UseString(exgr);
    new MakeString(exgr);
  }
}

class MakeString implements Runnable {
  Exchanger<String> ex;

  String str;

  MakeString(Exchanger<String> c) {
    ex = c;
    str = new String();

    new Thread(this).start();
  }

  public void run() {
    char ch = 'A';
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 5; j++)
        str += (char) ch++;

      try {
        System.out.println("MakeString - "+str);
        str = ex.exchange(str);
      } catch (InterruptedException exc) {
        System.out.println(exc);
      }
    }
  }
}

class UseString implements Runnable {
  Exchanger<String> ex;

  String str;

  UseString(Exchanger<String> c) {
    ex = c;
    new Thread(this).start();
  }

  public void run() {

    for (int i = 0; i < 3; i++) {
      try {
        str = ex.exchange(new String());
        System.out.println("Got: " + str);
      } catch (InterruptedException exc) {
        System.out.println(exc);
      }
    }
  }
}

