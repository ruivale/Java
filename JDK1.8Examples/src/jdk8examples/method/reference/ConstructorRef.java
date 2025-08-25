/**
 * <p>
 * Classname: jdk8examples.method.reference.ConstructorRef
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas. You shall
 * not disclose such Confidential Information and shall use it only in accordance with the terms of
 * the license agreement you entered into EFACEC SE.
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
package jdk8examples.method.reference;

/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 21, 2015, 11:03:31 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ConstructorRef {

  public static void main(String[] args) {
    EmpProvider provider = Emp::new;
    Emp emp = provider.getEmp("Anas", 24);
    System.out.printf("Name: %s%n", emp.name);
    System.out.printf("Age: %d%n", emp.age);
  }
}

class Emp {

  String name;
  Integer age;

  Emp(String name, Integer age) { //parameterised constructor  
    this.name = name;
    this.age = age;
  }
}

@FunctionalInterface
interface EmpProvider {

  Emp getEmp(String name, int age);
}
