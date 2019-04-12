/**
 * <p>
 * Classname: exp.ints.IntegerVsint
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
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
package exp.ints;

import java.util.Date;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 29, 2013, 10:46:17 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class IntegerVsint {

  public void runInteger() {
    long startTime = new Date().getTime();
    Integer ctr = 0;
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      ctr += i;
    }
    long endtime = new Date().getTime();
    System.out
        .println("Time for Integer: " + (endtime - startTime) + " ms");

    System.out.println();
  }

  public void runint() {
    long startTime = new Date().getTime();

    int i = 0;
    int ctr = 0;
    for (i = 0; i < Integer.MAX_VALUE; i++) {
      ctr += i;
    }

    long endtime = new Date().getTime();

    System.out.println("Time for int: " + (endtime - startTime) + " ms");

    System.out.println();
  }

  public static void main(String[] args) {
    new IntegerVsint().runInteger();
    new IntegerVsint().runint();
    new IntegerVsint().runInteger();
  }
}
