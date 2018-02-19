/**
 * <p>
 * Classname: jdk8examples.traits.Person
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

package jdk8examples.traits;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 29, 2016, 5:18:50 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class Person implements HasName, HasAge, HasWeight {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(Person.class.getName());

  private final String name;
  private final int age;
  private final int weight;

 /**
  * The Person default constructor.
  */
  public Person(String name, int age, int weight){
    this.name = name;
    this.age = age;
    this.weight = weight;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public int getWeight() {
    return this.weight;
  }
 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("Person: ").append(this.getName()).toString();
  }
}
