package exp.strings;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class ASCIIToHexa {
  //~ Constructors ///////////////////////////////////////////////////////////
  /**
   * Creates a new ASCIIToHexa object.
   */
  public ASCIIToHexa() {
  }

  //~ Methods ////////////////////////////////////////////////////////////////
  /**
   * DOC
   *
   * @param ascii
   *
   * @return
   */
  private static String asciiToHex(String ascii) {
    char[] chars     = ascii.toCharArray();
    String hexString = "";

    for(int i = 0; i<chars.length; i++) {
      int asciiVal = (int)chars[i];
      hexString += Integer.toHexString(asciiVal);
    }
    return hexString;
  }

  /**
   * DOC
   *
   * @param args
   */
  public static void main(String[] args) {
    ASCIIToHexa ASCIIToHexa1 = new ASCIIToHexa();
    String      str        = "00AC12380B2328";
    String      strAddress = "2328";
    String      strIP      = "172.18.56.11";


    int[]       ints = { 172, 18, 56, 11, 9000 };
    for(int i = 0; i<ints.length; i++) {
      System.out.println(ints[i]+"->"+ASCIIToHexa1.intToHexa(ints[i])+".");
    }


    /*
    System.out.println("strAddress= "+ ASCIIToHexa1.toAddressString(strAddress)+".");
    */
    /*
        System.out.println(
          "ASCIIToHexa1.asciiToHex(str): "+ASCIIToHexa1.asciiToHex(strIP)+".");
    */
    /*
    char[] chars = { 172, 18, 56, 11};
    for(int i = 0; i<chars.length; i++) {
      System.out.println(
        "char("+chars[i]+"): "+ASCIIToHexa1.toHexStringByte(chars[i])+".");
    }

    char[] _chars = { 9000};
    for(int i = 0; i<_chars.length; i++) {
      System.out.println(
        "char("+_chars[i]+"): "+ASCIIToHexa1.toHexStringBytes(_chars[i])+".");
    }
    */
    /*
    ASCIIToHexa1.printHexa(str);
    */
    /*
    ASCIIToHexa1.printHexa("AC12380B");
    System.out.println("-----------------");
    ASCIIToHexa1.printHexaASCII("90");
    */
    /*
    ASCIIToHexa1.hexaToBinary("23");
    */
    /*
    ASCIIToHexa1.hexaToString("");
    */
  }

  /**
   * DOC
   *
   * @param input
   *
   * @return
   */
  private String toAddressString(String input) {
    String emailAddresses = input;

    //emailAddresses = "0x736D616C6C6170754069782E6E6574636F6D2E636F6D";
    String   address     = null;

    int      len         = emailAddresses.length();

    String[] arrEmailAdd = new String[len/2];

    for(int i = 0; i<(len/2); i++) {
      arrEmailAdd[i] = emailAddresses.substring(2*i, (2*i)+2);
    }

    char[] addressChars = new char[arrEmailAdd.length];

    for(int i = 0; i<arrEmailAdd.length; i++) {
      addressChars[i] = (char)Integer.parseInt(arrEmailAdd[i], 16);

    }

    address = new String(addressChars);

    //end of if
    return address;

  }

  /**
   * DOC
   *
   * @param c
   *
   * @return
   */
  private String toHexStringByte(char c) {
    String rep;
    String temp;
    int    low  = c&0x00;
    int    high = c&0xFF;

    //int    low  = c&0x00FF;
    //int    high = c&0xFF00;
    temp = Integer.toHexString(low);
    if(temp.length()==1) {
      temp = "0"+temp;
    }
    rep    = temp;
    temp   = Integer.toHexString(high);
    if(temp.length()==1) {
      temp = "0"+temp;
    }


    //rep += temp;
    // If you want a space between bytes
    //rep += " " + temp;
    return rep;
  }

  /**
   * DOC
   *
   * @param c
   *
   * @return
   */
  private String toHexStringBytes(char c) {
    String rep;
    String temp;
    int    low  = c&0x00FF;
    int    high = c&0xFF00;

    temp = Integer.toHexString(low);
    if(temp.length()==1) {
      temp = "0"+temp;
    }
    rep    = temp;
    temp   = Integer.toHexString(high);
    if(temp.length()==1) {
      temp = "0"+temp;
    }


    rep += temp;

    // If you want a space between bytes
    //rep += " " + temp;
    return rep;
  }

  /**
   * DOC
   *
   * @param hex
   *
   * @return
   */
  private String hexaToBinary(String hex) {
    int z = Integer.parseInt(hex, 16);  //if that does work ...
    return Integer.toBinaryString(z);
  }

  /**
   * DOC
   *
   * @param strHexa
   *
   * @return
   */
  private int hexaToInt(String strHexa) {
    return Integer.parseInt(strHexa, 16);
  }

  /**
   * DOC
   *
   * @param strHexa
   */
  private void hexaToString(String strHexa) {
    char c = 0x23;
    System.out.println(c);
  }

  /**
   * DOC
   *
   * @param intValue
   *
   * @return
   */
  private String intToHexa(int intValue) {
    return Integer.toHexString(intValue);
  }

  /**
   * DOC
   *
   * @param str
   */
  private void printHexa(String str) {
    String code = str;  //"FF74AEE3FA825627E1AA58E29D6863C148C735";

    int[]  ints = new int[code.length()/2];

    for(int i = 0; i<code.length(); i += 2) {
      ints[i/2] = (int)(Integer.parseInt(
          code.substring(i, i+2),
          16));

      System.out.println(ints[i/2]);

    }
  }

  /**
   * DOC
   *
   * @param str
   */
  private void printHexaASCII(String str) {
    String code  = str;  //"FF74AEE3FA825627E1AA58E29D6863C148C735";

    byte[] bytes = new byte[code.length()/2];

    for(int i = 0; i<code.length(); i += 2) {
      bytes[i/2] = (byte)(Integer.parseInt(
          code.substring(i, i+2),
          16));

      System.out.println(bytes[i/2]);

    }
  }
}
