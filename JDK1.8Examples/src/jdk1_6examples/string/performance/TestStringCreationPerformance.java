/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.string.performance;

/**
 * <p>Classname:  </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: EFACEC SE</p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class TestStringCreationPerformance {
  private static int iterations = 1000000;
  private static String s;
  
  public static void main(String[] args) {
    
    long before = System.currentTimeMillis();    
    for(int i = 0; i < iterations; i++) {      
      s = String.valueOf(i);
    }    
    long after = System.currentTimeMillis();
    System.out.println("String.valueOf() took: "+(after-before)+"."); 
    
    before = System.currentTimeMillis();
    for(int i = 0; i < iterations; i++) {      
      s = ""+i;
    }
    after = System.currentTimeMillis();
    System.out.println("\"\"+ took: "+(after-before)+"."); 
    
    before = System.currentTimeMillis();
    for(int i = 0; i < iterations; i++) {      
      s = new StringBuffer().append(i).toString();
    }
    after = System.currentTimeMillis();
    System.out.println("new StringBuffer took: "+(after-before)+"."); 
    
    before = System.currentTimeMillis();
    for(int i = 0; i < iterations; i++) {      
      s = new StringBuilder().append(i).toString();
    }
    after = System.currentTimeMillis();
    System.out.println("new StringBuilder took: "+(after-before)+"."); 
    
    
  }
}
