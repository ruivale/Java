/**
 * <p>
 * Classname: exp.booleans.BooleanInversion
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

package exp.booleans;



/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 17, 2013, 1:23:01 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class BooleanInversion {
  public static void test() {
    boolean flag = true;
    long start;
    int iTotal = 1999990000;

    start = -System.currentTimeMillis();
    for (int i=0; i<iTotal; i++) {
      flag ^= true;
    }
    start += System.currentTimeMillis();
    System.out.println("time for flag ^= true: " + start + "ms");

    start = -System.currentTimeMillis();
    for (int i=0; i<iTotal; i++) {
      flag = !flag;
    }
    start += System.currentTimeMillis();
    System.out.println("time for flag = !flag: " + start + "ms");

    start = -System.currentTimeMillis();
    for (int i=0; i<iTotal; i++) {
      flag = flag?false:true;
    }
    start += System.currentTimeMillis();
    System.out.println("time for flag = flag?false:true: " + start + "ms");
  }
  public static void main(String[] args) throws Exception {
    test();
    Thread.sleep(100);
    test();
  }
}
