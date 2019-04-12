/**
 * <p>
 * Classname: jdk1_7examples.strig.concat.StringConcatTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
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

package jdk1_7examples.strig.concat;

public class StringConcatTests {

  private static int i;

  public static void main(final String[] args){
    final String s = i + " - " + args[0];
    final String sb = new StringBuffer().append(i).append(" - ").append(args[0]).toString();
    final String sbb = new StringBuilder().append(i).append(" - ").append(args[0]).toString();
  }
}
