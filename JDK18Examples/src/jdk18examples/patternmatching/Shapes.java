/**
 * <p>
 * Classname:  jdk18examples.patternmatching.Shapes
 * </p>
 * 
 * https://blogs.oracle.com/javamagazine/post/java-pattern-matching-guards-coverage-dominance?source=:em:nw:mt::::RC_WWMK200429P00043C0062:NSL400244550
 * 
 */

package jdk18examples.patternmatching;


import java.util.*;



sealed interface Shape {
  double area();
}

record Circle(double radius) implements Shape {
  @Override public double area() {
    return Math.PI * radius * radius;
  }
}

record Rectangle(double side1, double side2)
  implements Shape {
  @Override public double area() {
    return side1 * side2;
  }
}

/**
 * <p>
 * Description:
 * Guards
 *    A guard allows you to refine the matching condition beyond simply matching on the type. It is 
 *    a test that appears after the type and &&. The guard can be any Boolean expression. If the 
 *    selector expression is the same as the type for the case and the guard evaluates to true, the 
 *    pattern matches.
 * 
 * </p>
 */
public class Shapes {
  
  static void classify(Shape s) {
    System.out.println(switch(s) {
      case Circle c && c.area() < 100.0 -> "Small Circle: " + c;
      case Circle c -> "Large Circle: " + c;
      case Rectangle r && r.side1() == r.side2() -> "Square: " + r;
      case Rectangle r -> "Rectangle: " + r;
    });
  }
  
  
  /***
   * Output:
   *    Small Circle: Circle[radius=5.0]
   *    Large Circle: Circle[radius=25.0]
   *    Square: Rectangle[side1=12.0, side2=12.0]
   *    Rectangle: Rectangle[side1=12.0, side2=15.0]
   * 
   * @param args 
   */
  public static void main(String[] args) {
    List.of(
      new Circle(5.0),
      new Circle(25.0),
      new Rectangle(12.0, 12.0),
      new Rectangle(12.0, 15.0)
    ).forEach(t -> classify(t));
  }
}
