/**
 * <p>
 * Classname: exp.reflection.CharsReflectionTests
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
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Oct 31, 2012, 6:16:48 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class CharsReflectionTests {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(CharsReflectionTests.class.getName());


 /**
  * The CharsReflectionTests default constructor.
  */
  public CharsReflectionTests(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("CharsReflectionTests").append("").toString();
  }

  public static void main(final String[] args){
    try {
      Field value = String.class.getDeclaredField("value");
      value.setAccessible(true);
      value.set("hello!", "cheers".toCharArray());
      System.out.println("hello!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
