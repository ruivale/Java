/**
 * <p>
 * Classname:  jdk8examples.optional.OptionalBooleanSimpleSample
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

package jdk8examples.optional;


import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class OptionalBooleanSimpleSample {

    
  /**
   * Obtained from the equips specific config like: "...https$ON|..." or : "...https$OFF|..." 
   * 
   * @return true, false or null in case there's no https TAG in the specific config
   */
  public static Optional<Boolean> isHttpsOn(String strValue){
    final String strHttpsSpeConfigValue = strValue;
    Boolean boolHttps =
        strHttpsSpeConfigValue != null && !strHttpsSpeConfigValue.isEmpty()?
          "ON".equalsIgnoreCase(strHttpsSpeConfigValue):
          null;
    
    return Optional.ofNullable(boolHttps);    
  }

  
  
  public static void main(final String[] args){
    String[] strValues = {
      "ON",
      "OFF",
      "LIXO",
      "",
      null
    };

    for (String strValue : strValues) {
      Optional<Boolean> opt = isHttpsOn(strValue);    
      System.out.println(
        "Optional("+strValue+") isHttpOn? " + 
          opt +" Optional<?>.isPresent(): " + 
          opt.isPresent() + " Value: " +
          (opt.isPresent()? opt.get(): "NOT PRESENT... SO..."));
      
    }
    
  }
}
