/**
 * <p>
 * Classname:  jdk18examples.patternmatching.Planets
 * </p>
 */

package jdk18examples.patternmatching;

enum CelestialBody {
  MERCURY, VENUS, EARTH, MARS, JUPITER,
  SATURN, URANUS, NEPTUNE, PLUTO
}

/**
 * <p>
 * Description:
 * 
 * If a case requires multiple statements or expressions, put them inside a curly brace enclosed block.
 * 
 * Note that when you?re producing a result from a multiline case expression, you need to use the 4
 * yield keyword even though the arrow syntax is involved.
 * 
 * </p>
 */
public class Planets {

  public static String classify(CelestialBody b) {
    var result = switch(b) {
      case  MERCURY, VENUS, EARTH,
            MARS, JUPITER,
            SATURN, URANUS, NEPTUNE -> {
              System.out.print("A planet: ");
              yield b.toString();
            }
      case  PLUTO -> {
              System.out.print("Not a planet: ");
              yield b.toString();
            }
    };
    return result;
  }
  
  
  
  /**
   * Output:
   *    A planet: MARS
   *    Not a planet: PLUTO
   * 
   * 
   * @param args 
   */
  public static void main(String[] args) {
    System.out.println(classify(CelestialBody.MARS));
    System.out.println(classify(CelestialBody.PLUTO));
  }
  
}
