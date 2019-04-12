package exp.forcicle;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TestForWithinFor {
  public TestForWithinFor() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 10; j++) {
        if(j==5){
          System.out.println("  j="+j+".");
          break;
        }
      }
      System.out.println("i="+i+".");
    }
  }
  public static void main(String[] args) {
    TestForWithinFor testForWithinFor1 = new TestForWithinFor();
  }

}
