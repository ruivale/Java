/**
 * <p>
 * Classname:  jdk1_6examples.SumTest
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
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

package jdk1_6examples;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 1/Abr/2011, 10:55:33
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class SumTest {

  private static int sum(final int iMax){
    int i = 0;
    int sum = 0;

    while(i < iMax){
      sum += i;
      i++;
    }

    return sum;
  }

  public static void main(final String[] args){
    int max = 10;
    System.out.println("sum from 1 to "+max+" is "+sum(max)+".");
  }
}
