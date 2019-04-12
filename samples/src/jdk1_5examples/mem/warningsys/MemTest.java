/*
 * MemTest.java
 *
 * Created on 21 de Janeiro de 2005, 15:25
 * http://www.javaspecialists.co.za/archive/Issue092.html
 */

package jdk1_5examples.mem.warningsys;


import java.util.*;


public class MemTest {
  public static void main(String[] args) {
    MemoryWarningSystem.setPercentageUsageThreshold(0.6);

    MemoryWarningSystem mws = new MemoryWarningSystem();
    mws.addListener(new MemoryWarningSystem.Listener() {
      public void memoryUsageLow(long usedMemory, long maxMemory) {
        System.out.println("Memory usage low!!!");
        double percentageUsed = ( (double) usedMemory) / maxMemory;
        System.out.println("percentageUsed = " + percentageUsed);
        MemoryWarningSystem.setPercentageUsageThreshold(0.8);
      }
    });

    Collection<Double> numbers = new LinkedList<Double> ();
    while (true) {
      numbers.add(Math.random());
    }
  }
}
