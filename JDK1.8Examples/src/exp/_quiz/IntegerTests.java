package exp._quiz;


public class IntegerTests {

  public IntegerTests() {

    int i1 = 1;
    Integer io = new Integer(i1);

    Integer ib1 = i1;
    Integer ib2 = 1;
    
    System.out.println((i1 == io) + " " + (ib1 == ib2));
    
    
    
    System.out.println("\n-----------------------------\n");
        
    
    Integer integer = 1;
    Integer integer3 = 1;    
    System.out.println("integer(1) equals integer3(1)? " + (integer == integer3) + "");
    
    integer = 130;
    integer3 = 130;    
    System.out.println("integer(130) equals integer3(130)? " + (integer == integer3) + "\n\n;-)\n\n");

  }

  public static void main(final String[] args) {
    final IntegerTests clazz = new IntegerTests();
  }
}
