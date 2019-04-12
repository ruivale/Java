/**
 * <p>
 * Classname: jdk8examples.streams.loops.StreamsAndForLoops
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package jdk8examples.streams.loops;

import java.util.function.BiConsumer;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Apr 12, 2016, 12:05:58 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class StreamsAndForLoops {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(StreamsAndForLoops.class.getName());

  /**
   * The StreamsAndForLoops default constructor.
   */
  public StreamsAndForLoops() {
    float[][] values = new float[][]{
      {4.5f, 0.35f},
    };
    loop(values, (i, k) -> {
      float value = values[i][k];
      System.out.println("Value["+i+"]["+k+"]="+value);
    });
  }

  /**
   *
   * @param values
   * @param consumer
   */
  private void loop(float[][] values, BiConsumer<Integer, Integer> consumer) {
    for (int i = 0; i < values.length; i++) {
      for (int k = 0; k < values[i].length; k++) {
        consumer.accept(i, k);
      }
    }
  }




  public static void main(final String[] args) {
    final StreamsAndForLoops clazz = new StreamsAndForLoops();
  }
}
