/**
 * <p>
 * Classname: exp.reflection.IntegerChangingTests
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

package exp.reflection;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Oct 31, 2012, 5:55:12 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class IntegerChangingTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(IntegerChangingTests.class.getName());


 /**
  * The IntegerChangingTests default constructor.
  */
  public IntegerChangingTests(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("IntegerChangingTests").append("").toString();
  }

  public static void main(final String[] args){
    System.out.println("Some integer values inside the [-128,127] range:");
    try {
      Field value = Integer.class.getDeclaredField("value");
      value.setAccessible(true);
      value.set(42, 43);

      Map<Integer, String> meaningOfLife = new HashMap<Integer, String>(1);
      meaningOfLife.put(42, "\tThe Meaning of Life");


      System.out.println(meaningOfLife.get(42));
      System.out.println(meaningOfLife.get(43));

      System.out.printf("\tSix times Seven = %d%n", 6 * 7);

    } catch (Exception exc) {
      exc.printStackTrace();
    }

    System.out.println("\nNow, with some integer values outside the [-128,127] range:");

    try {
      Field value = Integer.class.getDeclaredField("value");
      value.setAccessible(true);
      value.set(420, 430);

      Map<Integer, String> meaningOfLife = new HashMap<Integer, String>(1);
      meaningOfLife.put(420, "\tThe Meaning of Life");


      System.out.println(meaningOfLife.get(420));
      System.out.println(meaningOfLife.get(430));

      System.out.printf("\tSixty times Seven = %d%n", 60 * 7);

    } catch (Exception exc) {
      exc.printStackTrace();
    }

    System.out.println();
  }
}
