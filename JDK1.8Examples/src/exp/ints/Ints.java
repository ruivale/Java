package exp.ints;

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
public class Ints {
  public Ints() {
    int i = 0;
    try {
      i = Integer.parseInt("10000000");
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("i="+i+".");

  }

  public static void main(String[] args) {
    //Ints ints = new Ints();

    int millis = 15780;

    int isa = (int)((millis/1000)*2.2);
    System.out.println("isa=" + isa);

    int isa2 = (int)((isa*1000)/2.2);
    System.out.println("isa2=" + isa2);

  }
}
