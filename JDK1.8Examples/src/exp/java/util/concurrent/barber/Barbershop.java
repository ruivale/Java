/**
 * <p>
 * Classname: exp.java.util.concurrent.barber.Barbershop
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

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
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
public class Barbershop {

  private static final Logger logger = Logger.getLogger(Barbershop.class.getName());
  private final Semaphore seats;

  
  /**
   * 
   * @param seatsCount 
   */
  public Barbershop(int seatsCount) {
    this.seats = new Semaphore(seatsCount, true);
  }

  
  /**
   * 
   * @param customerId
   * @return 
   */
  public boolean acquireSeat(int customerId) {
    logger.info(() -> "Customer #" + customerId+ " is trying to get a seat");
    
    try {
      boolean acquired = seats.tryAcquire(5 * 1000, TimeUnit.MILLISECONDS);
      
      if (!acquired) {
        logger.info(() -> "Customer #" + customerId + " has left the barbershop");
        return false;
      }
      logger.info(() -> "Customer #" + customerId + " got a seat");
      return true;
      
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      logger.severe(() -> "Exception: " + ex);
    }
    
    return false;
  }

  
  /**
   * 
   * @param customerId 
   */
  public void releaseSeat(int customerId) {
    logger.info(() -> "Customer #" + customerId + " has released a seat");
    seats.release();
  }
  
  
  
  
  /**
   * 
   * @param args 
   */  
  public static void main(String[] args) {
    final Barbershop bs = new Barbershop(3);
  
    for (int i = 1; i <= 10; i++) {
      final BarbershopCustomer bc = new BarbershopCustomer(bs, i);
      new Thread(bc).start();
    }
  }
}
