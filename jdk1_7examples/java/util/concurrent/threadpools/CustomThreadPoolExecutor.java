/**
 * <p>
 * Classname: jdk1_7examples.java.util.concurrent.threadpools.CustomThreadPoolExecutor
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich – Ap. 3078
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
package jdk1_7examples.java.util.concurrent.threadpools;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

  public CustomThreadPoolExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  @Override
  protected void beforeExecute(Thread t,
                               Runnable r) {
    super.beforeExecute(t, r);
    System.out.println("Perform beforeExecute() logic");
  }

  @Override
  protected void afterExecute(Runnable r,
                              Throwable t) {
    super.afterExecute(r, t);
    if (t != null) {
      System.out.println("Perform exception handler logic");
    }
    System.out.println("Perform afterExecute() logic");
  }
}