package exp.objsequal;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ObjsEqualTests {
  public ObjsEqualTests() {
    Object obj = null;
    System.out.println(obj instanceof Object);
   }

  public static void main(String[] args) {
    new ObjsEqualTests();

    if ("String".toString() == "String") {
      System.out.println("Equal");
    } else {
      System.out.println("Not Equal");
    }

    int i = new Integer('1').intValue();
    System.out.println("i=" + i);
  }
}
