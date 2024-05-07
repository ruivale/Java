/**
 * <p>
 * Classname: JDK22Examples.UnnamedVariablesAndPatterns
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
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
package JDK22Examples;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 240430
 */
public class UnnamedVariablesAndPatterns {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(UnnamedVariablesAndPatterns.class.getName());

  /**
   * The UnnamedVariablesAndPatterns default constructor.
   */
  public UnnamedVariablesAndPatterns() {
  }

  private static void oldOld() {
    String name = "Rui Vale";

    if (name != null) {
      System.out.println("Hello, " + name + "!");
    }
  }

  private static void newNew() {
    String name = "Rui Vale";

    if (name != null) {
      System.out.println("\n\nIT SEEMS Netbeans DOES NOT YET CORRECTLY SUPPORT JDK22!\n\n");
//      System.out.println("Hello, " + _ + "!"); // _ represents the unused variable
    }

  }
  
  public static void run() {    
    System.out.println("UnnamedVariablesAndPatterns UnnamedVariablesAndPatterns UnnamedVariablesAndPatterns");
    UnnamedVariablesAndPatterns.oldOld();
    UnnamedVariablesAndPatterns.newNew();
    System.out.println("-----------------------------------------------------------------------------------");
  }

  
  void main() {
    run();
  }

}
