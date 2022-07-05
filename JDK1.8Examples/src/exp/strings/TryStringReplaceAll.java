package exp.strings;


import java.util.regex.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class TryStringReplaceAll {
  String s = "hojr REPLACE_A oituux REPLACE_B";

  public TryStringReplaceAll() {
    System.out.println("s="+s+".");
    s = s.replaceAll("REPLACE_A", "a");
    System.out.println("s="+s+".");
    s = s.replaceAll("REPLACE_B", "bb");
    System.out.println("s="+s+".");

  }
  public static void main(String[] args) {
    TryStringReplaceAll tryStringReplaceAll1 = new TryStringReplaceAll();
  }
}