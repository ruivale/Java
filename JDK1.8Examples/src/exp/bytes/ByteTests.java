package exp.bytes;

import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
public class ByteTests {
  public ByteTests() {
    int intValue = 1992;
    ByteBuffer bb = ByteBuffer.allocate(4);
    bb.putInt(0, intValue);
    byte[] byte_array = bb.array();
    //byte second_least_significant = (byte) ( (intValue >>> 8) & 0xFF);
    System.out.println("--");
    for (int i = 0; i < byte_array.length; i++) {
      System.out.print(byte_array[i]);
    }
    System.out.println("\n--");

  }

  /**
   * Returns a byte array containing the two's-complement representation of the integer.<br>
   * The byte array will be in big-endian byte-order with a fixes length of 4
   * (the least significant byte is in the 4th element).<br>
   * <br>
   * <b>Example:</b><br>
   * <code>intToByteArray(258)</code> will return { 0, 0, 1, 2 },<br>
   * <code>BigInteger.valueOf(258).toByteArray()</code> returns { 1, 2 }.
   * @param integer The integer to be converted.
   * @return The byte array of length 4.
   *
     private byte[] intToByteArray(final int integer) {
   int byteNum = (40-Integer.numberOfLeadingZeros(integer<0? ~integer: integer))/8;
    byte[] byteArray = new byte[4];

    for (int n = 0; n < byteNum; n++) {
      byteArray[3 - n] = (byte) (integer >>> (n * 8));
    }

    return (byteArray);
     }
   */

  /**
   * returns a byte array of length 4
   *
   * @param i int
   * @return byte[]
   */
  private static byte[] intToDWord(int i) {
    byte[] dword = new byte[4];
    dword[0] = (byte) (i & 0x00FF);
    dword[1] = (byte) ( (i >> 8) & 0x000000FF);
    dword[2] = (byte) ( (i >> 16) & 0x000000FF);
    dword[3] = (byte) ( (i >> 24) & 0x000000FF);
    return dword;
  }
  private static void testIntToWord(int _i){
    try {
      byte[] bs = ByteTests.intToDWord(_i);
      System.out.println("testIntToWord");
      for (int i = 0; i < bs.length; i++) {
        System.out.print(bs[i]);
      }
      System.out.println("\n-------\n");
    } catch (Exception ex) {

    }
  }

  /**
   *
   * @param integer int
   * @return byte[]
   * @throws IOException
   */
  private static byte[] intToByteArray(final int integer) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(bos);
    dos.writeInt(integer);
    dos.flush();
    return bos.toByteArray();
  }
  private static void testIntToByteArray(int _i){
    try {
      byte[] bs = ByteTests.intToByteArray(_i);
      System.out.println("intToByteArray");
      for (int i = 0; i < bs.length; i++) {
        System.out.print(bs[i]);
      }
      System.out.println("\n-------\n");
    } catch (Exception ex) {

    }
  }

  private static void integerToByte(int x){
    Integer in = new Integer(x);
    byte b = in.byteValue();
    System.out.println(b);

  }

  /**
   *
   * @param l long
   */
  public static byte[] convertLongToBytes(long l){
    byte[] bytes = new byte[8];
    for (int i = 7; i >= 0; i--) {
        bytes[i] = (byte) (l & 0xFF);
        l = l >>> 8;
    }
    return bytes;
  }

  /**
   *
   * @param bytes byte[]
   */
  public static long convertBytesToLong(byte bytes[]){
    long l2 = 0;
    for (int i = 0; i < 8; i++) {
      long shifted = l2 << 8;
      l2 = shifted | (bytes[i] & 0xFF);
    }
    return l2;
  }

  /**
   *
   * @param l long
   */
  public static byte[] convertIntToBytes(int intValue){
    byte[] bytes = new byte[4];
    for (int i = 3; i >= 0; i--) {
        bytes[i] = (byte) (intValue & 0xFF);
        intValue = intValue >>> 8;
    }
    return bytes;
  }

  /**
   *
   * @param bytes byte[]
   */
  public static int convertBytesToInt(byte bytes[]){
    int i2 = 0;
    for (int i = 0; i < 4; i++) {
      int shifted = i2 << 8;
      i2 = shifted | (bytes[i] & 0xFF);
    }
    return i2;
  }



  public static void main(String[] args) {
    //ByteTests.testIntToByteArray(126876);
    //ByteTests.integerToByte(12065);

    int i = 1234564;
    byte[] bs = ByteTests.convertIntToBytes(i);
    System.out.println("i= "+i+".");
    for (int j = 0; j < bs.length; j++) {
      System.out.print(bs[j]);
    }
    int returned = ByteTests.convertBytesToInt(bs);

    System.out.println("\nReturned= "+returned+".");

  }
}


















