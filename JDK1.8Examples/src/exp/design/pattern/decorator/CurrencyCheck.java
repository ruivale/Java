/**
 * <p>
 * Classname: exp.design.pattern.decorator.CurrencyCheck
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2019 EFACEC SE
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
package exp.design.pattern.decorator;

//Now its time to check currency


public class CurrencyCheck {

  public static void main(String[] args) {

    // without adding decorators
    Currency curr = new Dollar();
    System.out.println(curr.getDescription() + " dollar. " + curr.cost(2.0));

    //adding decorators
    Currency curr2 = new USDDecorator(new Dollar());
    System.out.println(curr2.getDescription() + " dollar. " + curr2.cost(4.0));

    Currency curr3 = new SGDDecorator(new Dollar());
    System.out.println(curr3.getDescription() + " dollar. " + curr3.cost(4.0));
  }
}
