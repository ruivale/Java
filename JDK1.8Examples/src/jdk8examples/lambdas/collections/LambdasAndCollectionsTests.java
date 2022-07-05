/**
 * <p>
 * Classname: jdk8examples.lambdas.collections.LambdasAndCollectionsTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
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
package jdk8examples.lambdas.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.function.Predicate;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Apr 4, 2013, 1:10:00 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class LambdasAndCollectionsTests {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER = Logger.getLogger(LambdasAndCollectionsTests.class.getName());

  /**
   * The LambdasAndCollectionsTests default constructor.
   */
  public LambdasAndCollectionsTests() {
    List<Integer> numbers = Arrays.asList(1, 2, 8, 3, 4, 5, 6);

    System.out.println("old fashioned way ----------------------------------");
    // old fashioned way ...
    for (int number : numbers) {
      System.out.println(number);
    }

    System.out.println("numbers.forEach(new Consumer<Integer>() ----------------------------------");

    numbers.forEach(new Consumer<Integer>() {
      @Override
      public void accept(Integer value) {
        System.out.println(value);
      }
    });

    System.out.println("numbers.forEach((Integer value)-> System.out.println(value)) ----------------------------------");

    numbers.forEach((Integer value)-> System.out.println(value));
    System.out.println("numbers.forEach(value -> System.out.println(value)) ----------------------------------");
    numbers.forEach(value -> System.out.println(value));
    System.out.println("numbers.forEach(System.out::println) ----------------------------------");
    numbers.forEach(System.out::println);

    System.out.println("-----------------------------------------------------------------------------");

    int value = sumAll(numbers, n -> true);
    System.out.println("sumAll(numbers, n -> true): "+value);
    value = sumAll(numbers, n -> n % 2 == 0);
    System.out.println("\nsumAll(numbers, n -> n % 2 == 0): "+value);
    value = sumAll(numbers, n -> n > 3);
    System.out.println("\nsumAll(numbers, n -> n > 3): "+value);


    System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

  }

  /**
   *
   * @param numbers
   * @return
   */
  public int sumAll(List<Integer> numbers) {
    int total = 0;

    for (int number : numbers) {
      total += number;
    }
    return total;
  }

  /**
   *
   * @param numbers
   * @return
   */
  public int sumAllEven(List<Integer> numbers) {
    int total = 0;

    for (int number : numbers) {
      if (number % 2 == 0) {
        total += number;
      }
    }
    return total;
  }

  /**
   *
   * @param numbers
   * @param p
   * @return
   */
  public int sumAll(List<Integer> numbers, Predicate<Integer> p) {
    int total = 0;

    for (int number : numbers) {
      if (p.test(number)) {
        total += number;
      }
    }
    return total;
  }




  public static void main(final String[] args) {
    final LambdasAndCollectionsTests clazz = new LambdasAndCollectionsTests();

//    Arrays.asList(
//        new File("c:/").listFiles((File f) -> f.getName().endsWith(".txt"))).forEach(
//            file-> {System.out.println(file);});

  }
}
