/**
 * <p>
 * Classname:  jdk1_6examples.generics.GenericsTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.generics;

public class GenericsTests {

  interface IPerson {
    int getAge();
  }

  interface IPersonDAO<IPerson> {
  }

  public static void main(String[] args) {
    IPersonDAO<Person> personDAO = null;
    IPersonDAO<Engineer> engineerDao = null;
    //personDAO = engineerDao;
    //engineerDao = personDAO;
  }

  class Person implements IPerson {
    int age;

    public int getAge() {
      System.out.println("I 'am Person");
      return age;
    }
  }

  class Engineer extends Person {
    @Override
    public int getAge() {
      System.out.println("I 'am Engineer");
      return age;
    }
  }

  class JavaEngineer extends Engineer {
    @Override
    public int getAge() {
      System.out.println("I 'am JavaEngineer");
      return age;
    }
  }
}
