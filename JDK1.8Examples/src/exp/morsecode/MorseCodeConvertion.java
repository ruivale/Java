package exp.morsecode;


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
public class MorseCodeConvertion {

  public static final String A = ".-";
  public static final String B = "-...";
  public static final String C = "-.-.";
  public static final String D = "-..";
  public static final String E = ".";
  public static final String F = "..-.";
  public static final String G = "--.";
  public static final String H = "....";
  public static final String I = "..";
  public static final String J = ".---";
  public static final String K = "-.-";
  public static final String L = ".-..";
  public static final String M = "--";
  public static final String N = "-.";
  public static final String O = "---";
  public static final String P = ".--.";
  public static final String Q = "--.-";
  public static final String R = ".-.";
  public static final String S = "...";
  public static final String T = "-";
  public static final String U = "..-";
  public static final String V = "...-";
  public static final String W = ".--";
  public static final String X = "-..-";
  public static final String Y = "-.--";
  public static final String Z = "--..";

  public static final String _1 = ".----";
  public static final String _2 = "..---";
  public static final String _3 = "...--";
  public static final String _4 = "....-";
  public static final String _5 = ".....";
  public static final String _6 = "-....";
  public static final String _7 = "--...";
  public static final String _8 = "---..";
  public static final String _9 = "----.";
  public static final String _0 = "-----";


  /**
   *
   */
  private MorseCodeConvertion() {
    try {
      byte[] b = new byte[128];
      System.out.print("Convert to morse or chars? [morse] ");
      System.in.read(b);
      String strValue = new String(b).trim();

      boolean mustConvertToMorse = false;

      if(strValue != null &&
         (strValue.equals("") ||
          strValue.equals("morse") ||
          strValue.equals("m"))){
        mustConvertToMorse = true;
      }

      b = new byte[4096];
      System.out.println("Enter info to convert: ");
      System.in.read(b);
      strValue = new String(b).trim();


      if(mustConvertToMorse){
        System.out.println("Morse=" + fromCharsToMorse(strValue));
      }else{
        System.out.println("Chars=" + fromMorseToChars(strValue));
      }


    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  /**
   *
   * @param strToConvert String
   * @return String
   */
  public static String fromMorseToChars(final String strToConvert){
    String strChars = null;


    return strChars;
  }

  /**
   *
   * @param strToConvert String
   * @return String
   */
  public static String fromCharsToMorse(final String strToConvert){
    String strInMorse = null;

    String[] strs = new String[strToConvert.length()];

    for (int i = 0; i < strs.length; i++) {
      strs[i] = getMorse(strToConvert.toLowerCase().charAt(i));
    }

    return strInMorse;
  }

  /**
   * getMorse
   *
   * @param c char
   * @return String
   */
  private static String getMorse(final char c) {
    return "";
  }

  public static void main(String[] args) {
    MorseCodeConvertion morsecodeconvertion = new MorseCodeConvertion();
  }
}












