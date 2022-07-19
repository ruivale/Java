/**
 * <p>
 * Classname:  jdk18examples.patternmatching.SwitchExpression
 * </p>
 * 
 * https://blogs.oracle.com/javamagazine/post/java-switch-expression-arrow-case-null?source=:em:nw:mt::::RC_WWMK200429P00043C0062:NSL400244550
 * 
 */

package jdk18examples.patternmatching;

import java.util.*;


/**
 * <p>
 * Description:
 *    Historically, switch has always been a statement, and statements don?t generate a result. 
 *    JDK 14 allows switch to also be an expression, and when used in this way, switch produces a result.
 * 
 * 
 * With the old-style colons, as seen above in colon(), you use the new yield keyword to return 
 * results from the switch. Notice that when you use yield, a break is not necessary. In fact, 
 * if you add a break you?ll get an error at compile time, flagging the attempt to break out of 
 * a switch expression.
 * 
 * If you try to use yield inside a switch statement, the compiler produces an error message that 
 * says you used yield outside of a switch expression.
 * 
 * In switch expressions, arrow() has the same effect as colon(), but the syntax can be cleaner, 
 * more compact, and more readable

* </p>
 */
public class SwitchExpression {

  static int colon(String s) {
    var result = switch(s) {
      case "i": yield 1;
      case "j": yield 2;
      case "k": yield 3;
      default:  yield 0;
    };
    return result;
  }
  static int arrow(String s) {
    var result = switch(s) {
      case "i" -> 1;
      case "j" -> 2;
      case "k" -> 3;
      default  -> 0;
    };
    return result;
  }
  
  
  
  /**
   * Output:
   *    i 1 1
   *    j 2 2
   *    k 3 3
   *    z 0 0
   * 
   * 
   * @param args 
   */
  public static void main(String[] args) {
    for(var s: new String[]{"i", "j", "k", "z"})
      System.out.format(
        "%s %d %d%n", s, colon(s), arrow(s));
  }
  
}
