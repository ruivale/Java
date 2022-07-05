/**
 * <p>
 * Classname: jdk8examples.optional.OptionalAndFunctions
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package jdk8examples.optional;

import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Apr 18, 2016, 1:13:37 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class OptionalAndFunctions {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(OptionalAndFunctions.class.getName());

  /**
   *
   * @param propertyName
   * @return
   */
  public static Optional<String> getProperty(final String propertyName) {
    return
        propertyName != null && !propertyName.isEmpty()?
            Optional.ofNullable(System.getProperty(propertyName)):
            Optional.empty();
  }

  /**
   *
   * @param <T>
   * @param propertyName
   * @param func
   * @return
   */
  public static <T> Optional<T> getProperty(
      String propertyName,
      /*Throwing*/Function<String, ? extends T/*, ? extends Exception*/> func) {
    return getProperty(propertyName).map(val -> {
      try {
        return func.apply(val);

      } catch (Exception e) {
        LOGGER.severe(() -> "Invalid property transform, will default " + e.getMessage());
        return null;
      }
    });
  }




  public static void main(final String[] args) {
    try {

      System.setProperty("cache.limit", "sd");
      System.setProperty("cache.enabled", "tr_ue");


      int size = OptionalAndFunctions.getProperty("cache.limit", Integer::parseInt).orElse(500);
      System.out.println("Size: "+size);

      size = OptionalAndFunctions.getProperty(null, Integer::parseInt).orElse(500);
      System.out.println("Size: "+size);

      boolean enabled = OptionalAndFunctions.getProperty("cache.enabled", Boolean::valueOf).orElse(true);
      //boolean enabled = OptionalAndFunctions.getProperty("cache.enabled", Boolean::getBoolean).orElse(true);
      System.out.println("Enabled? "+enabled);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
