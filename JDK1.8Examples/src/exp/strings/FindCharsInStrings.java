package exp.strings;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class FindCharsInStrings {

  public FindCharsInStrings() {

    String mode = "2x2";

    String strRows = mode.substring(0,mode.indexOf("x"));
    String strColums = mode.substring(mode.indexOf("x")+1, mode.length());

    System.out.println("strRows: "+strRows+", strColums: "+strColums+".");


  }
  public static void main(String[] args) {
    FindCharsInStrings findCharsInStrings1 = new FindCharsInStrings();
  }
}