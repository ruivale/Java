/**
 * <p>
 * Classname: javastreams.ImplementingIfElseInStreams
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
package javastreams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class ImplementingIfElseInStreams {
  private static List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

  /**
   * 
   */
  private static void ifElse() {
    ints.stream()
      .forEach(i -> {
        if (i.intValue() % 2 == 0) {
          System.out.println(i + " is EVEN");
        } else {
          System.out.println(i + " is ODD");
        }
      });
    System.out.println("----------------------------------");
  }
  
  /**
   * 
   */
  private static void withfilter(){
    Stream<Integer> evenIntegers = ints.stream()
      .filter(i -> i.intValue() % 2 == 0);
    Stream<Integer> oddIntegers = ints.stream()
      .filter(i -> i.intValue() % 2 != 0);

    evenIntegers.forEach(i -> System.out.println(i + " is EVEN"));
    oddIntegers.forEach(i -> System.out.println(i + " is ODD"));   
    
    System.out.println("----------------------------------");
  }

  
  public static void main(final String[] args) {
    ImplementingIfElseInStreams.ifElse();
    ImplementingIfElseInStreams.withfilter();
  }
}
