/**
 * <p>
 * Classname:  jdk18examples.patternmatching.Dominance
 * </p>
 * 
 * https://blogs.oracle.com/javamagazine/post/java-pattern-matching-guards-coverage-dominance?source=:em:nw:mt::::RC_WWMK200429P00043C0062:NSL400244550
 * 
 */

package jdk18examples.patternmatching;


import java.util.*;

sealed interface Base {}
record Derived() implements Base {}

/**
 * <p>
 * Description:
 * Dominance
 *    The order of the case statements in a switch can be important because if the base type appears
 *    first, it dominates anything appearing afterwards.
 * 
 * The base type Base is in last place, at line [1]?and that?s where it should be. But if you move 
 * that line up, the base type will appear before case Derived, which would mean that the switch 
 * would never be able to test for Derived because any derived class would then be captured by case 
 * Base. If you try this experiment, the compiler reports an error: this case label is dominated by 
 * a preceding case label. * 
 * </p>
 */
public class Dominance {

  static String test(Base base) {
    return switch(base) {
      case Derived d -> "Derived";
      case Base b -> "B";            // [1]
    };
  }  
  
}

