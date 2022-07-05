package exp.bits;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class HexByte {

  public static void main(String[] args) {
    char h[]={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'e', 'f'};

    byte b = 1;

    b <<= 7;

System.out.println("b="+b+".");

//    System.out.println("b=0x"+h[(b >> 2) & 0x0f] + h[b & 0x0f]);

  }
}