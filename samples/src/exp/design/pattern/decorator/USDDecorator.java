/**
 * <p>
 * Classname: exp.design.pattern.decorator.USDDecorator
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
 * Rua Eng.� Frederico Ulrich - Apartado 3081
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

// Concrete Decorator
public class USDDecorator extends Decorator {

//  Currency currency;

  public USDDecorator(Currency currency) {
    this.currency = currency;
  }

  public String getDescription() {
    return currency.getDescription() + " ,its US Dollar";
  }
//
//  public double cost(double v) {    
//    return this.currency.cost(v);
//  }

}
