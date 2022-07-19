/**
 * <p>
 * Classname:  jdk18examples.patternmatching.Tanks
 * </p>
 * 
 * https://blogs.oracle.com/javamagazine/post/java-pattern-matching-guards-coverage-dominance?source=:em:nw:mt::::RC_WWMK200429P00043C0062:NSL400244550
 * 
 */

package jdk18examples.patternmatching;


import java.util.*;

enum Type { TOXIC, FLAMMABLE, NEUTRAL }

record Level(int percent) {
  Level {
    if(percent < 0 || percent > 100) {
      throw new IndexOutOfBoundsException(
        percent + " percent");
    }
  }
}

record Tank(Type type, Level level) {}



/**
 * <p>
 * Description:
 *    Since the code switches on Tank rather than Object, the final case Tank acts the same as a 
 *    default because it catches all Tank cases that don?t match any of the other patterns.
 * </p>
 */
public class Tanks {
  
  static String check(Tank tank) {
    return switch(tank) {
      case Tank t && t.type() == Type.TOXIC -> "Toxic: " + t;
      case Tank t && (t.type() == Type.TOXIC && t.level().percent() < 50) -> "Toxic, low: " + t;
      case Tank t && t.type() == Type.FLAMMABLE -> "Flammable: " + t;
      // Equivalent to "default":
      case Tank t -> "Other Tank: " + t;
    };
  }



  
  public static void main(String[] args) {
    List.of(
      new Tank(Type.TOXIC, new Level(49)),
      new Tank(Type.FLAMMABLE, new Level(52)),
      new Tank(Type.NEUTRAL, new Level(75))
    ).forEach(
      t -> System.out.println(check(t))
    );
  }
}
