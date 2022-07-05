package exp.badcode;


public class BadCode {
  public static void calculationWithPrint() {
    double someValue = 0D;
    for (int i = 0; i < 10000; i++) {
      System.out.println(someValue = someValue + i);
    }
  }

  public static void calculationWithOutPrint() {

    double someValue = 0D;
    for (int i = 0; i < 10000; i++) {
      someValue = someValue + i;
    }

  }

  public static void main(String[] n) {
    long before = System.currentTimeMillis();
    //BadCode.calculationWithOutPrint();
    BadCode.calculationWithPrint();
    long after = System.currentTimeMillis();

    System.out.println("1st took "+(after-before)+" secs.");

    before = System.currentTimeMillis();
    BadCode.calculationWithOutPrint();
    //BadCode.calculationWithPrint();
    after = System.currentTimeMillis();
    System.out.println("2nd took "+(after-before)+" secs.");
  }
}
