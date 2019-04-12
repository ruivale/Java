/**
 * <p>
 * Classname: exp.collections.list.User
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
package exp.collections.list;


public class User {

  private String firstName;
  private String lastName;
  private int age;

  public User(String firstName,
              String lastName,
              int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public String toString() {
    StringBuilder sbuf = new StringBuilder();
    sbuf.append('(').append(this.firstName).append(',').append(this.lastName).append(',').
      append(this.age).append(')');
    return sbuf.toString();
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }
  
  public int getAge() {
    return this.age;
  }
}
