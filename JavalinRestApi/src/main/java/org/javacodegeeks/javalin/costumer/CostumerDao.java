/**
 * <p>
 * Classname: org.javacodegeeks.javalin.costumer.CustomerDao
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
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
package org.javacodegeeks.javalin.costumer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class CostumerDao {

  private final List<Costumer> customers = Arrays.asList(
    new Costumer(0, "George Smith", 334872136),
    new Costumer(1, "Tania Rogers", 231872636),
    new Costumer(2, "Carol Smith", 431875136)
  );

  private static CostumerDao customerDao = null;

  
  /**
   * 
   */
  private CostumerDao() {
  }

  
  /**
   * 
   * @return 
   */
  static CostumerDao instance() {
    if (customerDao == null) {
      customerDao = new CostumerDao();
    }
    return customerDao;
  }

  /**
   *
   * @param id
   * @return
   */
  Optional<Costumer> getCustomerById(int id) {
    return this.customers.stream().filter(costumer -> costumer.id == id).findFirst();
  }

  /**
   *
   * @return
   */
  Iterable getAllCustomerNames() {
    return this.customers.stream().map(customer -> customer.name).collect(Collectors.toList());
  }
}
