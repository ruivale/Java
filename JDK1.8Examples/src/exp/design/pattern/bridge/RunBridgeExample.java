/**
 * <p>
 * Classname:  exp.design.pattern.facade.RunBridgeExample
 * </p>
 *
 * <p>Copyright: Copyright (c) 2019 EFACEC SE
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

package exp.design.pattern.bridge;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class RunBridgeExample {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(RunBridgeExample.class.getName());


 /**
  * The RunBridgeExample default constructor.
  */
  public RunBridgeExample(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("RunBridgeExample").append("").toString();
  }

  public static void main(final String[] args){
    final RunBridgeExample clazz = new RunBridgeExample();
  }
}
