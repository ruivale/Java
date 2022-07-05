package exp.strings;


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
public class TestStringEquals {
  public TestStringEquals() {
    String s1 = "";
    String s2 = null;

    if(!s1.equals(s2)){
      System.out.println("!=");
    }else{
      System.out.println("==");
    }
  }

  public static void main(String[] args) {
    TestStringEquals teststringequals = new TestStringEquals();
  }
}
