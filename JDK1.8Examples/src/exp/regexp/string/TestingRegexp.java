package exp.regexp.string;

import java.util.regex.*;

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
public class TestingRegexp {
  public TestingRegexp() {
    String r = "\\S.*._.*._.*._.*";//"\\w.*._.*._.*._.*";
    String[] ss = {
      "??_CAM Track2_20161215_014522-000.avi",
      "Station 9_CAM Track2_20161215_014522-000.avi",
      "Stat ion 9_CAM Track2_20161215_014522-000.avi",
      "fi_c_20161215_014522-000.avi",
      "i_c_20161215_014522-000.avi",
      "f i_c_20161215_014522-000.avi",
      " _c_20161215_014522-000.avi",
      "_c_20161215_014522-000.avi",
    };
//    String r = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}:\\d+:\\d+";
//    String s = "111.111.111.111:2222:333";

    for(String s:  ss){
      boolean b = Pattern.matches(r, s.subSequence(0, s.length()));
      System.out.println(s + " matches (" + r + ") ? "+b+".");
    }

  }

  public static void main(String[] args) {
    TestingRegexp t = new TestingRegexp();
  }
}
