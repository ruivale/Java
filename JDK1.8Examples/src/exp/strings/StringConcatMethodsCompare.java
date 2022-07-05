/**
 * <p>
 * Classname: exp.strings.StringConcatMethodsCompare
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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
package exp.strings;

import java.io.IOException;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 1, 2015, 1:20:50 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class StringConcatMethodsCompare {

  public static void main(String args[]) throws IOException {
    System.out.println("starting ...");

    final int ITERATION = 10000;
    long startTime;
    long duration;
    String s = "";

    startTime = System.nanoTime();
    for (int i = 0; i < ITERATION*2; i++) {
      s = s + Integer.toString(i);
    }
    duration = (System.nanoTime() - startTime) / 1000;




    // StringBuilder example to concate two String in Java
    StringBuilder builder = new StringBuilder(); //default size for worst case
    startTime = System.nanoTime();
    for (int i = 0; i < ITERATION; i++) {
      builder.append(i);
    }
    duration = (System.nanoTime() - startTime) / 1000;
    System.out.println("Time taken to concatenate 100000 Strings using StringBuilder append in micro) : "+ duration);




    // StringBuffer example to concate String in Java
    StringBuffer buffer = new StringBuffer(); // default size 16
    startTime = System.nanoTime();
    for (int i = 0; i < ITERATION; i++) {
      buffer.append(i);
    }
    duration = (System.nanoTime() - startTime) / 1000;
    System.out.println("Time taken to concatenate 100000 Strings using StringBuffer (in micro) : "+ duration);



    // String Concatenation using + operator
    startTime = System.nanoTime();
    for (int i = 0; i < ITERATION; i++) {
      s = s + Integer.toString(i);
    }
    duration = (System.nanoTime() - startTime) / 1000;
    System.out.println("Time taken to concatenate 100000 Strings using + operator (in micro) : "+ duration);



    // Using String concat() method
    startTime = System.nanoTime();
    for (int i = 0; i < ITERATION; i++) {
      s.concat(Integer.toString(i));

    }
    duration = (System.nanoTime() - startTime) / 1000;
    System.out.println("Time taken to concatenate 100000 Strings using concat method (in micro) : "+ duration);


  }
}
