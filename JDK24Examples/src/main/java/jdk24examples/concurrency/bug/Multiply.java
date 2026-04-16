/**
 * <p>
 * Classname: jdk24examples.concurrency.bug.Multiply
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
package jdk24examples.concurrency.bug;


import java.io.IOException;


/**
 * Can you have a concurrency bug in totally thread-safe code? Take a moment to think about it. 
 * This simple thread-safe class gives you one kind of answer.
 * If "m" is a "Multiply" instance, I can run m.x(3);m.y(4);print(m.calc()) and see a value other 
 * than 12 printed! This is a concurrency bug. This class is technically thread-safe in that you 
 * always see the result of the latest operations on it, but logically not thread-safe because 
 * different threads can overwrite the value "x" and "y" resulting in an unexpected value from "calc". 
 * Even making the "calc" method synchronized, or having an atomic "xy(int, int)" wouldn't change 
 * that, the issue is that all three operations need to be atomically together for correctness. 
 * So depending on how you interpret "thread-safety", this could be considered thread-safe code 
 * that has a concurrency bug.
 * 
 * @author Fasterj.com
 */
class Multiply {

  volatile int x, y;

  void x(int i) {
    x = i;
  }

  void y(int i) {
    y = i;
  }

  int calc() {
    return x * y;
  }
  
  
  
  public static void main(String[] args) {

    new Thread(() -> {
      final Multiply m1 = new Multiply();
      m1.x(3);
      m1.y(4);
      System.out.println("m1(3,4):"+m1.calc());      
      
      for (int i = 0; i < 6; i++) {
        final int j = i;

        Thread.ofVirtual().start(() -> {
          m1.x(j);
          System.out.println("m1("+j+",4):"+m1.calc());      
        });

        System.out.println("m1(3,4):"+m1.calc());      
      }
      
    }).start();
  
    
    try {
      System.in.read();
    } catch (IOException iOException) {
    }
  }
}
