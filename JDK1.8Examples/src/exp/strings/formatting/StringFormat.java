/**
 * <p>
 * Classname:  exp.strings.formatting.StringFormat
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

package exp.strings.formatting;


import java.text.MessageFormat;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class StringFormat {
  private static final int max = 10000;
    
  /**
   * 
   */
  private static void stringFormat(){
    final long before = System.currentTimeMillis();
    
    for (int i = 0; i < max; i++) {
      String.format("Value is %s.", Integer.toString(i));      
    }
    
    long after = System.currentTimeMillis();
    System.out.println("String.format(\"Value is %s.\", "+max+") took "+(after - before)+" millis.");        
  }
    
  /**
   * 
   */
  private static void messageFormat(){
    final long before = System.currentTimeMillis();
    
    for (int i = 0; i < max; i++) {
      MessageFormat.format("Value is {0}.", Integer.toString(i));
    }
    
    final long after = System.currentTimeMillis();
    System.out.println( "MessageFormat.format(\"Value is {0}.\", "+max+") took "+(after - before)+" millis.");    
  }
  
  
  
  
  public static void main(final String[] args){
    StringFormat.stringFormat();
    StringFormat.messageFormat();
    StringFormat.stringFormat();
    StringFormat.messageFormat();

    StringFormat.messageFormat();
    StringFormat.stringFormat();
    StringFormat.messageFormat();
    StringFormat.stringFormat();

    StringFormat.stringFormat();
    StringFormat.stringFormat();
    StringFormat.stringFormat();

    StringFormat.messageFormat();
    StringFormat.messageFormat();
    StringFormat.messageFormat();
    
    StringFormat.messageFormat();
    StringFormat.messageFormat();
    StringFormat.messageFormat();
    
    StringFormat.stringFormat();
    StringFormat.stringFormat();
    StringFormat.stringFormat();

  }
}
