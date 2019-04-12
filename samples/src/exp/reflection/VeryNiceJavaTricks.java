/**
 * <p>
 * Classname: exp.reflection.VeryNiceJavaTricks
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


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Oct 31, 2012, 6:19:00 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class VeryNiceJavaTricks {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(VeryNiceJavaTricks.class.getName());

  static {
    undertheWood();
  }

  private static void undertheWood(){
    try {
      Field value = String.class.getDeclaredField("value");
      value.setAccessible(true);
      value.set("hello!", "cheers".toCharArray());

    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      Field value = Integer.class.getDeclaredField("value");
      value.setAccessible(true);
      value.set(42, 43);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


 /**
  * The VeryNiceJavaTricks default constructor.
  */
  public VeryNiceJavaTricks(){
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("VeryNiceJavaTricks").append("").toString();
  }

  public static void main(final String[] args){
    System.out.println();
    final Map<Integer, String> meaningOfLife = new HashMap<Integer, String>(1);
    meaningOfLife.put(42, "The 42th Meaning of Life");
    meaningOfLife.put(43, "The 43th Meaning of Life");
    System.out.println("The value for the element \"42\" is: "+meaningOfLife.get(42));
    System.out.println("What?");

    System.out.println();

    System.out.print("Printing \"hello\": ");
    System.out.println("hello!");
    System.out.println("cheers? WTF ...");


    try {
      System.out.println("\nAnd using reflection to create a new class without invoking its constructors?");
      final ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
      final Constructor objDef = Object.class.getDeclaredConstructor();
      final Constructor intConstr = rf.newConstructorForSerialization(MyClass.class, objDef);
      final MyClass mc = (MyClass) intConstr.newInstance();
      System.out.println("\tmc = " + mc.toString());
      System.out.println("\t"+mc.getClass());
      System.out.println("The \"i\" SHOULD be 42, no?");


      System.out.println("\n\n");

    } catch (Exception e) {
      e.printStackTrace();
    }


    System.out.println("Nice no? ;-)\n");
    System.out.println("\n\n");
  }


  public class MyClass {
    private int i = 42;

    public MyClass() {
      System.out.println("Constructor() called");
    }

    public MyClass(int i) {
      System.out.println("Constructor("+i+") called");
    }

    public String toString() {
      return "MyClass i=" + i;
    }
  }
}
