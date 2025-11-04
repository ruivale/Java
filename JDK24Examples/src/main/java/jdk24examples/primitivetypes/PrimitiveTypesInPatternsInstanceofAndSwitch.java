/**
 * <p>
 * Classname: jdk24examples.primitivetypes.PrimitiveTypesInPatternsInstanceofAndSwitch
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2024 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package jdk24examples.primitivetypes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Summary 
 *    Enhance pattern matching by allowing primitive types in all pattern contexts, and extend
 *    instanceof and switch to work with all primitive types. This is a preview language feature.
 *
 * History 
 *    This feature was originally proposed by JEP 455, and delivered as a preview feature in
 *    JDK 23. We here propose to preview it for a second time, without change.
 *
 * Goals 
 *    Enable uniform data exploration by allowing type patterns for all types, whether primitive
 *    or reference.
 *
 *    Align type patterns with instanceof, and align instanceof with safe casting.
 *
 *    Allow pattern matching to use primitive types in both nested and top-level pattern contexts.
 *
 *    Provide easy-to-use constructs that eliminate the risk of losing information due to unsafe casts.
 *
 *    Following the enhancements to switch in Java 5 (enum switch) and Java 7 (string switch), allow
 *    switch to process values of any primitive type.
 *
 * Non-Goals 
 *    It is not a goal to add new kinds of conversions to the Java language. 
 * 
 * Motivation
 *    Multiple restrictions pertaining to primitive types impose friction when using pattern matching,
 *    instanceof, and switch. Eliminating these restrictions would make the Java language more uniform
 *    and more expressive.
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 250909
 */
public class PrimitiveTypesInPatternsInstanceofAndSwitch {

  
  private static String testSwitch1(final int x) {
    return 
      switch (x) {
        case 0 -> "okay";
        case 1 -> "warning";
        case 2 -> "error";
        default -> "unknown status: " + x;
    };
  }

  private static String testSwitchWithPrimitiveType(final int x) {
    return 
      switch (x) {
        case 0 -> "okay";
        case 1 -> "warning";
        case 2 -> "error";
        case int i when i > 2 && i < 100 -> "Between 2 and 100!";
        case int i when i >= 100 -> "Bigger then 100!";
        case int i when i < 0 -> "Negative value!";
        default -> "Unknown";
      };    
  }
  
  
  private static String testInstanceOf(final Object obj){
    if(obj instanceof Integer integer){
      return "Yes, it's an Integer: "+integer.toString();
    }
    
    return "Not a Integer!";
  }
  
  
  public static void main(final String[] args) {
    System.out.println("");
    
    
    
    System.out.println("----------------------------------------------------------------------");
    for (int j = 0; j < 4; j++) {
      System.out.println("PrimitiveTypesInPatternsInstanceofAndSwitch.testSwitch1(" + j + "): "
        + PrimitiveTypesInPatternsInstanceofAndSwitch.testSwitch1(j));
    }
    System.out.println("----------------------------------------------------------------------");
    
    
    System.out.println("----------------------------------------------------------------------");
    for (int j = -2; j < 5; j++) {
      System.out.println("PrimitiveTypesInPatternsInstanceofAndSwitch.testSwitchWithPrimitiveType(" + j + "): "
        + PrimitiveTypesInPatternsInstanceofAndSwitch.testSwitchWithPrimitiveType(j));
      
      if(j == 4){
        System.out.println("PrimitiveTypesInPatternsInstanceofAndSwitch.testSwitchWithPrimitiveType(102): "
          + PrimitiveTypesInPatternsInstanceofAndSwitch.testSwitchWithPrimitiveType(102));
        break;        
      }
    }
    System.out.println("----------------------------------------------------------------------");    
    

    System.out.println("----------------------------------------------------------------------");
    System.out.println("PrimitiveTypesInPatternsInstanceofAndSwitch.testInstanceOf(new Integer(10)): "+
      PrimitiveTypesInPatternsInstanceofAndSwitch.testInstanceOf(new Integer(10)));
    System.out.println("----------------------------------------------------------------------");

    
    
    System.out.println("");
  }

}
