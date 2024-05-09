/**
 * <p>
 * Classname:  JDK22Examples.PatternMatchingForSwitch
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
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

package java22;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description: 
 * Pattern Matching for switch (JEP 470 – Preview)
 * 
 * Java 22 introduces a preview of pattern matching for the switch statement. 
 * This allows for more concise and readable code when dealing with various conditions in a switch block.
 * 
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 240430
 */
public class PatternMatchingForSwitch {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(PatternMatchingForSwitch.class.getName());

 
  /**
   * 
   */
  private static void oldOld() {
    String fruit = "apple";

    switch (fruit) {
      case "apple":
        System.out.println("It's an apple!");
        break;
      case "banana":
        System.out.println("It's a banana!");
        break;
      default:
        System.out.println("Unknown fruit");
    }
  }
 
  
  /**
   * 
   */
  private static void newNew(){
    String fruit = "apple";
    
    switch (fruit) {
      case "apple" -> System.out.println("It's an apple!");
      case "banana" -> System.out.println("It's a banana!");
      default -> System.out.println("Unknown fruit");
    }    
  }
  

  
  
  public static void main(final String[] args){
    System.out.println("\nPatternMatchingForSwitch PatternMatchingForSwitch PatternMatchingForSwitch");
    PatternMatchingForSwitch.oldOld();
    PatternMatchingForSwitch.newNew();
    System.out.println("-----------------------------------------------------------------------------------");     
  }
}
