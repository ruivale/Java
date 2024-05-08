/**
 * <p>
 * Classname: exp.singleton.EagerAndLazySingleton
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
package exp.singleton;

/**
 * <p>
 * Description:
 * The singleton object is created only when the getInstance() method is called. It is a Java memory-safe 
 * singleton class. It is a thread-safe singleton and is lazily loaded. It is the most widely used and recommended.
 * 
 * Despite performance and safety improvement, the only objective to create just one object for a class is 
 * challenged by memory reference, reflection, and serialization in Java.
 * 
 * Memory reference: In a multithreaded environment, reordering of read and writes for threads can occur on a 
 * referenced variable, and a dirty object read can happen anytime if the variable is not declared volatile.
 * Reflection: With reflection, the private constructor can be made public and a new instance can be created. 
 * Serialization: A serialized instance object can be used to create another instance of the same class. 
 * All of these affect both static and dynamic singletons. In order to overcome such challenges, it requires 
 * us to declare the instance holder as volatile and override equals(), hashCode() and readResolve() of default 
 * parent class of all classes in Java, Object.class.
 * 
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class EagerAndLazySingleton {

  /*
   * 
   */
  private EagerAndLazySingleton() {
  }

  
  /*
   * 
   */
  private static class SingletonHelper {
    private static final EagerAndLazySingleton INSTANCE = new EagerAndLazySingleton();
  }

  
  
  
  public static EagerAndLazySingleton getInstance() {
    return SingletonHelper.INSTANCE;
  }
}
