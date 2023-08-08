/**
 * <p>
 * Classname:  jdk8examples.method.reference.User
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

package jdk8examples.method.reference;


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
public class User {


  public static void main(final String[] args){
    final UserType clazz = Admin::new;
  }
}


class Admin extends User {
  String name;
  Integer age;

  Admin(String name, Integer age)
  {
    this.name = name;
    this.age = age;
  }
}

class Manager extends User {
  String name;
  Integer age;

  Manager(String name, Integer age)
  {
    this.name = name;
    this.age = age;
  }
}

@FunctionalInterface
interface UserType {
  User getUser(String name, int age);
}
