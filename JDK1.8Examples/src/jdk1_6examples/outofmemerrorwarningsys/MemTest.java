/**
 * <p>
 * Classname:  jdk1_6examples.outofmemerrorwarningsys.MemTest
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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

package jdk1_6examples.outofmemerrorwarningsys;


import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.*;

public class MemTest {
  public static void main(String[] args) {
    MemoryWarningSystem.setPercentageUsageThreshold(0.6);

    MemoryWarningSystem mws = new MemoryWarningSystem();
    mws.addListener(new MemoryWarningSystem.Listener() {
      public void memoryUsageLow(long usedMemory, long maxMemory) {
        System.out.println("Memory usage low!!!");
        double percentageUsed = ((double) usedMemory) / maxMemory;
        System.out.println("percentageUsed = " + percentageUsed);
        MemoryWarningSystem.setPercentageUsageThreshold(0.8);
      }
    });

    Collection<Double> numbers = new LinkedList<Double>();
    while (true) {
      numbers.add(Math.random());
    }
  }
}
