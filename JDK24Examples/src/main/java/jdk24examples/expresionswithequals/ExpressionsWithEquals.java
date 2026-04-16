/**
 * <p>
 * Classname: jdk24examples.expresionswithequals.ExpressionsWithEquals
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2026 EFACEC SE
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
package jdk24examples.expresionswithequals;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class ExpressionsWithEquals {

  private static void testI() {
    System.out.println("\ntestI");
    int i = 2;
    int j = (i = 3) * i;
    System.out.println(j);
  }

  private static void testII() {
    System.out.println("\ntestII");
    int a = 9;
    a += (a = 3); // first example
    System.out.println(a);
    int b = 9;
    b = b + (b = 3); // second example
    System.out.println(b);
  }

  public static void testIII() {
    System.out.println("\ntestIII");
    int j = 1;
    try {
      int i = forgetIt() / (j = 2);
    } catch (Exception e) {
      System.out.println("Now j = " + j);
    }
  }

  static int forgetIt() throws Exception {
    throw new Exception("I'm outta here!");
  }

  public static void main(final String[] args) {
    ExpressionsWithEquals.testI();
    ExpressionsWithEquals.testII();
    ExpressionsWithEquals.testIII();
  }
}
