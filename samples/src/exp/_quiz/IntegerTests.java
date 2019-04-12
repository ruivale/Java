package exp._quiz;


public class IntegerTests {

  public IntegerTests() {
    int i1 = 1;
    Integer io = new Integer(i1);
    // line n1
    Integer ib1 = i1;
    Integer ib2 = 1;
    System.out.println((i1 == io) + " " + (ib1 == ib2));
  }

  public static void main(final String[] args) {
    final IntegerTests clazz = new IntegerTests();
  }
}
