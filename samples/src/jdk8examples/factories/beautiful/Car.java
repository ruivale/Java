/**
 * <p>
 * Classname: jdk8examples.factories.beautiful.Car
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

/*** THIS DOESN'T WORK!!! DON'T KNOW UAI!
public abstract class Car {

  private final Color color;


  @FunctionalInterface
  public interface Factory {

    Car make(Color color);

  }
}
/**/
/***/
public abstract class Car {

  private final Color color;


  public interface Factory {

    Car make(Color color);

  }

  protected Car(Color color) {

    this.color = color;

  }

  public abstract String getModel();

  public abstract int getPrice();

}
/**/