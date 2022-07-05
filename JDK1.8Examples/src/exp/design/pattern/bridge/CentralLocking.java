/**
 * <p>
 * Classname: exp.design.pattern.bridge.CentralLocking
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
package exp.design.pattern.bridge;

public class CentralLocking implements Product {

  private final String productName;

  public CentralLocking(String productName) {
    this.productName = productName;
  }

  @Override
  public String productName() {
    return productName;
  }

  @Override
  public void produce() {
    System.out.println("Producing Central Locking System");
  }
}
