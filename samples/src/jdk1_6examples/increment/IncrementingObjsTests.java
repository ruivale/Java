/**
 * <p>
 * Classname:  jdk1_6examples.increment.IncrementingObjsTests
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

package jdk1_6examples.increment;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class IncrementingObjsTests {
  private static final int n = 90000000;

  public static void main(final String[] args){
    incrementPlusPlus();
    //plusPlusIncrement();
  }

  private static void incrementPlusPlus(){
    Integer integer = new Integer(0);
    final long before = System.currentTimeMillis();

    for (int i = 0; i < n; i++) {
      integer++;
    }
    final long after = System.currentTimeMillis();
    System.out.println("integer++ of "+n+" took: "+(after - before)+" millis.");
  }

  private static void plusPlusIncrement(){
    Integer integer = new Integer(0);
    final long before = System.currentTimeMillis();

    for (int i = 0; i < n; i++) {
      ++integer;
    }
    final long after = System.currentTimeMillis();
    System.out.println("++integer of "+n+" took: "+(after - before)+" millis.");
  }
}













