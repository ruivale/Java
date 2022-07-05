/**
 * <p>
 * Classname:  jdk1_6examples.tests.PrintingNumbers
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
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
package jdk1_6examples.tests;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * Write a program that prints the numbers from 1 to 100. But for multiples of three print "Fizz" 
 * instead of the number and for the multiples of five print "Buzz". For numbers which are multiples 
 * of both three and five print "FizzBuzz". 
 * </p>
 *
 * Created on 20/Mai/2011, 19:08:20
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class PrintingNumbers {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(PrintingNumbers.class.getName());

  /**
   * The PrintingNumbers default constuctor.
   */
  public PrintingNumbers() {
    
    for (int i = 0; i < 100; i++) {
      if(i % 3 == 0 && i % 5 == 0){
        System.out.println("FizzBuzz");        
      }else if(i % 3 == 0){
        System.out.println("Fizz");        
      }else if(i % 5 == 0){
        System.out.println("Buzz");        
      }else{
        System.out.println(i);
      }
    }
    
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("PrintingNumbers").append("").toString();
  }
  
  public static void main(final String[] args) {
    final PrintingNumbers clazz = new PrintingNumbers();
  }
}
