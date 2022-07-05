package exp.strings;


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
public class SimpleStringIndexOf {

  static String STR_SLASH_SLASH = "//";

  public static void main(String[] args) {
    String s  = "udp://172.18.56.89";

    if(s != null && s.indexOf(STR_SLASH_SLASH) > 0){
      s = s.substring(
          s.indexOf(STR_SLASH_SLASH) + STR_SLASH_SLASH.length(),
          s.length());
    }

    /** @todo REMOVE THIS 07-02-2007 11:33 */
    System.out.println("s="+s+".");
  }
}
