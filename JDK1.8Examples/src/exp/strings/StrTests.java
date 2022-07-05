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
public class StrTests {

  String s = "Eth$172.18.56.69;10|";
  String s2 = "172.18.56.69";
  String s3 = "10|";

  public StrTests() {
    if(s.indexOf(s2) > -1 && s.indexOf(s3) > -1){
      System.out.println("FOUND ...");
    }else{
      System.out.println("NOT FOUND");
    }
  }

  public static void main(String[] args) {
    StrTests strtests = new StrTests();
  }
}
