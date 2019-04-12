package exp.string.ascii;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TransformAscii2Dec {
  public static void main(String[] args) {
    String msg = "Burder fucker!";
    //byte[] bytesMsg = msg.getBytes();
    System.out.println("######################################");
    for (int i = 0; i < msg.length(); i++) {
      System.out.print((int)msg.charAt(i)+", ");
    }
    System.out.println("\n######################################");
  }
}
