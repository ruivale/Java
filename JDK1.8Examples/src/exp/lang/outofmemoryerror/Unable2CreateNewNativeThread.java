/**
 * <p>
 * Classname: exp.lang.outofmemoryerror.Unable2CreateNewNativeThread
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
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
package exp.lang.outofmemoryerror;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 17, 2014, 3:41:53 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class Unable2CreateNewNativeThread {

  public static void main(final String[] args) {
    long nThreads = 0;

    try {
      while (true) {
        new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(10000000);
            } catch (InterruptedException e) {
            }
          }
        }).start();
        nThreads++;
      }
    } finally {
      System.out.println("NbrThreads: "+nThreads);
    }
  }
}
