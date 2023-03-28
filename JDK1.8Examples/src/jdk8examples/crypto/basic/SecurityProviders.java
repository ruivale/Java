/**
 * <p>
 * Classname: jdk8examples.crypto.basic.SecurityProviders
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
package jdk8examples.crypto.basic;

import java.security.Provider;
import java.util.logging.Logger;
import java.security.Security;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>
 * Description:
 * If you would like to obtain the list of installed providers simply call java.security.Security.getProviders().
 * 
 * Some popular provider examples include: SunPKCS11, SunMSCAPI (Windows), BouncyCastle, RSA JSAFE, 
 * SafeNet. If the provider you would like to use is not among the list printed, you can also register 
 * it following the steps below:
 *    - Place provider classes on CLASSPATH.
 *    - Register the provider either:
 *        . Statically by modifying the conf/security/java.security configuration file, e.g. 
 *          security.provider.5=SunJCEII. Be aware that in JDK 8 the java.security file is in 
 *          java.home/lib/security/java.security.
 *        . Dynamically by invoking Security.addProvider(java.security.Provider) and 
 *          Security.insertProviderAt(java.security.Provider,int).
 *    - The preference order for a provider is declared via simple number ordering.
 * 
 * Source: https://dev.java/learn/security/intro/
 * 
 * 
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class SecurityProviders {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER
    = Logger.getLogger(SecurityProviders.class.getName());

  /**
   * The SecurityProviders default constructor.
   */
  public SecurityProviders() {
    Set<String> algos = new TreeSet<>();
    for (Provider provider : Security.getProviders()) {
      Set<Provider.Service> service = provider.getServices();
      service.stream().map(Provider.Service::getAlgorithm).forEach(algos::add);
    }
    algos.forEach(System.out::println);
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("SecurityProviders").append("").toString();
  }

  public static void main(final String[] args) {
    final SecurityProviders clazz = new SecurityProviders();
  }
}
