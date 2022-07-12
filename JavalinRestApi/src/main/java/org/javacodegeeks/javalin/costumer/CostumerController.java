/**
 * <p>
 * Classname: org.javacodegeeks.javalin.costumer.CostumerController
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

import io.javalin.Handler;

import java.util.Objects;


/**
 * 
 * @author 2334
 */
public class CostumerController {

  /**
   * 
   */
  public static Handler fetchAllCustomeNames = ctx -> {
    CostumerDao dao = CostumerDao.instance();
    Iterable allCustomers = dao.getAllCustomerNames();
    ctx.json(allCustomers);
  };

  /**
   * 
   */
  public static Handler fetchById = ctx -> {
    int id = Integer.parseInt(Objects.requireNonNull(ctx.param("id")));
    CostumerDao dao = CostumerDao.instance();
    Costumer customer = dao.getCustomerById(id).get();

    if (customer == null) {
      ctx.html("Not Found");
    } else {
      ctx.json(customer);
    }
  };
}
