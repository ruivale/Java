package exp.for_.cicle;


/**
 * AHAHAHAHAHAHHAHAHAHAHAHAHAH ;-)
 */
public class TestForCicle {
  public TestForCicle() {
    for (int i = 0; i < 3; i++) {
      System.out.println("i="+i+". [i++]");
    }
    for (int i = 0; i < 3; ++i) {
      System.out.println("i="+i+". [++i]");
    }

    int j = 0;
    System.out.println("j="+j+", j++="+(j++)+", j="+j+".");
    j = 0;
    System.out.println("j="+j+", ++j="+(++j)+", j="+j+".");
    j = 0;
    System.out.println("j="+j+", j--="+(j--)+", j="+j+".");
    j = 0;
    System.out.println("j="+j+", --j="+(--j)+", j="+j+".");
  }

  public static void main(String[] args) {
    TestForCicle testforcicle = new TestForCicle();
  }
}
