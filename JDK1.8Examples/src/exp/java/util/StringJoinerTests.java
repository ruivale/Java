/**
 * <p>
 * Classname: exp.java.util.StringJoinerTests
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
package exp.java.util;

import java.util.StringJoiner;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Sep 7, 2016, 3:47:00 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class StringJoinerTests {

  /**
   *
   */
  private static void SimpleConcat(long k) {
    String sjr = "[";

    final long before = System.currentTimeMillis();
    for (int i = 0; i < k; i++) {
      sjr += String.valueOf(i) + ",";
    }
    sjr += "]";
    final long after = System.currentTimeMillis();
    System.out.println("SimpleConcat took " + (after - before) + " millis. k?"+k);
  }
  /**
   *
   */
  private static void StringBuffer(long k) {
    final StringBuffer sjr = new StringBuffer("[");

    final long before = System.currentTimeMillis();
    for (int i = 0; i < k; i++) {
      sjr.append(String.valueOf(i)).append(",");
    }
    sjr.append("]");
    final long after = System.currentTimeMillis();
    System.out.println("StringBuilder took " + (after - before) + " millis. k?"+k);
  }
  /**
   *
   */
  private static void StringBuilder(long k) {
    final StringBuilder sjr = new StringBuilder("[");

    final long before = System.currentTimeMillis();
    for (int i = 0; i < k; i++) {
      sjr.append(String.valueOf(i)).append(",");
    }
    sjr.append("]");
    final long after = System.currentTimeMillis();
    System.out.println("StringBuilder took " + (after - before) + " millis. k?"+k);
  }
  /**
   *
   */
  private static void StringJoiner(long k) {
    final StringJoiner sjr = new StringJoiner(",", "[", "]");

    final long before = System.currentTimeMillis();
    for (int i = 0; i < k; i++) {
      sjr.add(String.valueOf(i));
    }
    final long after = System.currentTimeMillis();
    System.out.println("StringJoiner took " + (after - before) + " millis. k?"+k);
  }




  public static void main(final String[] args) {

    final long k1 = 10000;
    final long k2 = 10000000;

    StringJoiner(k1);
    StringBuffer(k1);
    StringBuilder(k1);

    StringJoiner(k2);
    StringBuffer(k2);
    StringBuilder(k2);

    //SimpleConcat();
  }
}
