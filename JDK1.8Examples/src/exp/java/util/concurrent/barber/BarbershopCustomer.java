/**
 * <p>
 * Classname: exp.java.util.concurrent.barber.BarbershopCustomer
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2024 EFACEC SE
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
package exp.java.util.concurrent.barber;

import java.util.Random;
import java.util.logging.Logger;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class BarbershopCustomer implements Runnable{

  private static final Logger logger = Logger.getLogger(BarbershopCustomer.class.getName());
  private static final Random rnd = new Random();
  private final Barbershop barbershop;
  private final int customerId;

  
  /**
   * 
   * @param barbershop
   * @param customerId 
   */
  public BarbershopCustomer(final Barbershop barbershop, final int customerId) {
    this.barbershop = barbershop;
    this.customerId = customerId;
  }

  
  /**
   * 
   */
  @Override
  public void run() {
    final boolean acquired = barbershop.acquireSeat(customerId);
    
    if (acquired) {
      try {
        Thread.sleep(rnd.nextInt(10 * 1000));
        
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        logger.severe(() -> "Exception: " + ex);
      } finally {
        barbershop.releaseSeat(customerId);
      }
    } else {
      Thread.currentThread().interrupt();
    }
  }
}
