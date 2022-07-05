package jdk1_5examples.enums;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class UsePlanet {
  public UsePlanet() {
    double earthWeight = 175;//Double.parseDouble(args[0]);
    double mass = earthWeight / Planet.EARTH.surfaceGravity();
    for (Planet p : Planet.values()) {
      System.out.printf("Your weight on %s is %f%n", p, p.surfaceWeight(mass));
    }
  }

  public static void main(String[] args) {
    UsePlanet useplanet = new UsePlanet();
  }
}
