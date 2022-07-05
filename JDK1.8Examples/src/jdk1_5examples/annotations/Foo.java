package jdk1_5examples.annotations;


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
public class Foo {
  @Test public static void m1() {}

  public static void m2() {}

  @Test public static void m3() {
    throw new RuntimeException("Boom");
  }

  public static void m4() {}

  @Test public static void m5() {}

  public static void m6() {}

  @Test public static void m7() {
    throw new RuntimeException("Crash");
  }

  public static void m8() {}
}
