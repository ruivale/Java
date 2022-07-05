package exp.strings.tokenizer;

import java.util.*;
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
public class TestSTokenizer {
  public TestSTokenizer() {
    String s = "21908||100000|180|0|1";
    System.out.println("S: "+s);

    StringTokenizer st = new StringTokenizer(s, "|", true);

    System.out.println("Nbr of tokens? "+st.countTokens());

    while(st.hasMoreTokens()){
      System.out.println("->"+st.nextToken()+".");
    }

  }

  public static void main(String s[]){
    new TestSTokenizer();
  }
}
