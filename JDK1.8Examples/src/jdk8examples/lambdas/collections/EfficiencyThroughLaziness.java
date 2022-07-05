/**
 * <p>
 * Classname: jdk8examples.lambdas.collections.EfficiencyThroughLaziness
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


import java.util.List;
import java.util.Arrays;


/**
 * <p>
 * Description:
 * Streams can help a lot to fix both this problems. You can create a Stream from any Collection by
 * invoking the new stream() method on it. However Streams differ from Collections in several ways:
 *   - No storage: Streams don't have storage for values; they carry values from a data structure
 *     through a pipeline of operations.
 *   - Functional in nature: an operation on a stream produces a result, but does not modify its
 *     underlying data source. A Collection can be used as a source for a stream.
 *   - Laziness-seeking: many stream operations, such as filtering, mapping, sorting, or duplicate
 *     removal can be implemented lazily, meaning we only need to examine as many elements from the
 *     stream as we need to find the desired answer.
 *   - Bounds optional: there are many problems that are sensible to express as infinite streams,
 *     letting clients consume values until they are satisfied. Collections don't let you do this,
 *     but streams do.
 * </p>
 *
 * Created on Apr 9, 2013, 1:31:29 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class EfficiencyThroughLaziness {

  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

 /**
  * The EfficiencyThroughLaziness default constructor.
  */
  public EfficiencyThroughLaziness(){

    System.out.println(
        numbers.stream().
          filter(EfficiencyThroughLaziness::isEven).
            map(EfficiencyThroughLaziness::doubleIt).
              filter(EfficiencyThroughLaziness::isGreaterThan5).
                findFirst());
  }

  /**
   *
   * @param number
   * @return
   */
  public static boolean isEven(int number) {
    return number % 2 == 0;
  }

  /**
   *
   * @param number
   * @return
   */
  public static int doubleIt(int number) {
    return number * 2;
  }

  /**
   *
   * @param number
   * @return
   */
  public static boolean isGreaterThan5(int number) {
    return number > 5;
  }

  /**
   *
   * @param args
   */
  public static void main(final String[] args){
    final EfficiencyThroughLaziness clazz = new EfficiencyThroughLaziness();
  }
}
