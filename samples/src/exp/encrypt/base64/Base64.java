package exp.encrypt.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author 2334
 */
public class Base64 {

  /**
   *
   * @param bytes
   * @return
   */
  public static String encode(byte[] bytes) {
    return new BASE64Encoder().encode(bytes);
  }

  /**
   *
   * @param string
   * @return
   */
  public static byte[] decode(String string) {
    try {
      return new BASE64Decoder().decodeBuffer(string);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public static void main(String[] args) {
    final String sToday = "Hoje está a xobere!";
    final String sTodayEnc = "SG9qZSBlc3ThIGEgeG9iZXJlIQ==";//Base64.encode(sToday.getBytes());
    final String sTodayDec = new String(Base64.decode(sTodayEnc));
    System.out.println(sToday+" -> "+sTodayEnc +" -> "+sTodayDec);
  }
}