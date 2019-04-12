/**
 * <p>
 * Classname: exp.threads.sync.ExchangerSample
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package exp.threads.sync;

import java.util.concurrent.Exchanger;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Aug 26, 2016, 3:16:25 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ExchangerSample {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ExchangerSample.class.getName());

  private final Exchanger<ExchangerClass> exchanger = new Exchanger<>();

  /**
   * The ExchangerSample default constructor.
   */
  public ExchangerSample() {

    final ExchangerClass exchangerClass888 = new ExchangerClass(888, "Exchanger");
    final ExchangerClass exchangerClass999 = new ExchangerClass(999, "Exchanger");

    new Thread(new ExchangerRunnable(exchanger, exchangerClass888)).start();
    new Thread(new ExchangerRunnable(exchanger, exchangerClass999)).start();
  }

  public static void main(final String[] args) {
    final ExchangerSample clazz = new ExchangerSample();
  }


  class ExchangerRunnable implements Runnable {

    Exchanger exchanger = null;
    Object object = null;

    public ExchangerRunnable(Exchanger exchanger,
                             Object object) {
      this.exchanger = exchanger;
      this.object = object;
    }

    public void run() {
      try {
        Object previous = this.object;

        this.object = this.exchanger.exchange(this.object);

        System.out.println(
            Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object
        );
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


  class ExchangerClass {

    int iId;
    String strDesc;

    public ExchangerClass(int iId,
                          String strDesc) {
      this.iId = iId;
      this.strDesc = strDesc;
    }

    public String toString() {
      return "ExchangerClass(" + this.iId + "): " + this.strDesc;
    }
  }

}
