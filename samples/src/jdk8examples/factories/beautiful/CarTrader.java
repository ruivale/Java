/**
 * <p>
 * Classname: jdk8examples.factories.beautiful.CarTrader
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.� Frederico Ulrich ? Ap. 3078
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
package jdk8examples.factories.beautiful;

import java.awt.Color;


public final class CarTrader {

  private Car.Factory factory;

  private int cash;

  public void setSupplier(Car.Factory factory) {

    this.factory = factory;

  }

  public Car buyCar(Color color) {

    final Car car = factory.make(color);

    cash += car.getPrice();

    return car;

  }

}
