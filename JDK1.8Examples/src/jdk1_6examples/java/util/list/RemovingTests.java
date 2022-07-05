/**
 * <p>
 * Classname:  jdk1_6examples.java.util.list.RemovingTests
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
package jdk1_6examples.java.util.list;

import java.util.ArrayList;
import java.util.List;

public class RemovingTests {

  public static void main(String[] args) {
    List l = new ArrayList();
    for (int i = 0; i < 10; i++) {
      l.add(i);
    }
    for (int i = 0; i < 5; i++) {
      l.remove(i);
    }
    System.out.println(l);
  }
}
