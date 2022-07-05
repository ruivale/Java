/**
 * <p>
 * Classname: javastreams.FindAnyFindFirst
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
import java.util.Optional;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class FindAnyFindFirst {  
  
  private static List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K");

  /**
   * As the name suggests, the findAny() method allows us to find any element from a Stream. We use
   * it when we're looking for an element without paying an attention to the encounter order.
   *
   * In a non-parallel operation, it will most likely return the first element in the Stream, but
   * there is no guarantee for this.
   *
   */
  private static void createStream_whenFindAnyResultIsPresent_thenCorrect() {
    System.out.println("createStream_whenFindAnyResultIsPresent_thenCorrect");
//    List<String> list = Arrays.asList("A", "B", "C", "D");

    Optional<String> result = list.stream().findAny();

    System.out.println("\tresult.isPresent()? " + result.isPresent());

    if (result.isPresent()) {
      System.out.println("\t\tResult = " + result.get());
    }
    System.out.println("------------------");
  }

  /**
   * For maximum performance when processing the parallel operation, the result cannot be reliably
   * determined
   */
  private static void createParallelStream_whenFindAnyResultIsPresent_thenCorrect() {
    System.out.println("createParallelStream_whenFindAnyResultIsPresent_thenCorrect");
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Optional<Integer> result = list
      .stream().parallel()
      .filter(num -> num < 7).findAny();

    System.out.println("\tresult.isPresent()? " + result.isPresent());

    if (result.isPresent()) {
      System.out.println("\t\tResult = " + result.get());
    }
    System.out.println("------------------");
  }

  /**
   * The findFirst() method finds the first element in a Stream. So, we use this method when we
   * specifically want the first element from a sequence.
   *
   * When there is no encounter order, it returns any element from the Stream. According to the
   * java.util.streams package documentation, ?Streams may or may not have a defined encounter
   * order. It depends on the source and the intermediate operations.?
   *
   * The behavior of the findFirst method does not change in the parallel scenario. If the encounter
   * order exists, it will always behave deterministically.
   */
  private static void createStream_whenFindFirstResultIsPresent_thenCorrect() {
    System.out.println("createStream_whenFindFirstResultIsPresent_thenCorrect");

//    List<String> list = Arrays.asList("A", "B", "C", "D");

    Optional<String> result = list.stream().findFirst();

    System.out.println("\tresult.isPresent()? " + result.isPresent());

    if (result.isPresent()) {
      System.out.println("\t\tResult = " + result.get() + " (must be 'A')");
    }
    System.out.println("------------------");
  }

  public static void main(final String[] args) {
    
    System.out.println(Arrays.toString(list.toArray()));
    System.out.println("");
    
    FindAnyFindFirst.createStream_whenFindAnyResultIsPresent_thenCorrect();
    FindAnyFindFirst.createParallelStream_whenFindAnyResultIsPresent_thenCorrect();
    FindAnyFindFirst.createStream_whenFindFirstResultIsPresent_thenCorrect();
  }
}
