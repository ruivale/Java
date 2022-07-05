package jdk1_5examples.annotations;


import java.lang.reflect.*;


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
public class RunAnnotationTests {
  public static void main(String[] args)
      throws Exception {
    final String strTestClass = "jdk1_5examples.annotations.Foo";

    int passed = 0, failed = 0;
    for (Method m : Class.forName(strTestClass).getMethods()) {
      if (m.isAnnotationPresent(Test.class)) {
        try {
          m.invoke(null);
          passed++;
        } catch (Throwable ex) {
          System.out.printf("Test %s failed: %s %n", m, ex.getCause());
          failed++;
        }
      }
    }
    System.out.printf("Passed: %d, Failed %d%n", passed, failed);
  }
}
