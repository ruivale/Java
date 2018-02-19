/**
 * <p>
 * Classname: jdk8examples.collections.Person
 * </p>
 *
 * <p>Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
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

package jdk8examples.collections;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 31, 2018, 3:03:21 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class Person {

  /**
   * @return the id
   */
  public int getPersonId() {
    return id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the age
   */
  public int getAge() {
    return age;
  }
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(Person.class.getName());

  private int id;
  private String name;
  private int age;

 /**
  * The Person default constructor.
  */
  public Person(int id, String name, int age){
    this.id = id;
    this.name = name;
    this.age = age;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("Person").append("").toString();
  }

}
