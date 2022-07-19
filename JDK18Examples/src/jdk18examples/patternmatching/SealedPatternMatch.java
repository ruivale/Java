/**
 * <p>
 * Classname:  jdk18examples.patternmatching.SealedPatternMatch
 * 
 * https://blogs.oracle.com/javamagazine/post/java-pattern-matching-guards-coverage-dominance?source=:em:nw:mt::::RC_WWMK200429P00043C0062:NSL400244550
 * 
 * </p>
 */

package jdk18examples.patternmatching;


import java.util.*;

sealed interface Transport {};
record Bicycle(String id) implements Transport {};
record Glider(int size) implements Transport {};
record Surfboard(double weight) implements Transport {};

/////////////////////////////////////////////////////////////////////////////
// If you uncomment this:
// record Skis(int length) implements Transport {};
// You get an error: "the switch expression
// does not cover all possible input values"
/////////////////////////////////////////////////////////////////////////////


/**
 * <p>
 * Description:
 * Coverage
 *    Pattern matching naturally guides you toward using the sealed keyword. This helps ensure that
 *    you?ve covered all possible types passed into the selector expression
 * 
 * 
 * The sealed interface Transport is implemented using record objects, which are automatically final. 
 * The switch covers all possible types of Transport, and if you add a new type, the compiler detects 
 * it and tells you that you haven?t exhaustively covered all possible patterns. But line [1] shows 
 * that there?s still one case that the compiler doesn?t insist you cover: null.
 * 
 * If you remember to explicitly add a case null, you?ll prevent the exception. But the compiler 
 * doesn?t help you here, possibly because that would affect too much existing switch code. 
 * </p>
 */
public class SealedPatternMatch {
  
  static String exhaustive(Transport t) {
    return switch(t) {
      case Bicycle b -> "Bicycle " + b.id();
      case Glider g -> "Glider " + g.size();
      case Surfboard s -> "Surfboard " + s.weight();
    };
  }



  /**
   * Output:
   *    Bicycle Bob
   *    Glider 65
   *    Surfboard 6.4
   *    Not exhaustive: java.lang.NullPointerException
   * 
   * @param args 
   */
  public static void main(String[] args) {
    List.of(
      new Bicycle("Bob"),
      new Glider(65),
      new Surfboard(6.4)
    ).forEach(
      t -> System.out.println(exhaustive(t))
    );
    try {
      exhaustive(null); // Always possible!  // [1]
    } catch(NullPointerException e) {
      System.out.println("Not exhaustive: " + e);
    }
  }  
  
}
