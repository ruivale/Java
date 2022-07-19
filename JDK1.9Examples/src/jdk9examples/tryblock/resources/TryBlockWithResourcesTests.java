package jdk9examples.tryblock.resources;

import java.net.Socket;


/**
 * Effectively final variables in try-with-resources
 *    The original try-with-resources requires that all managed variables be defined within the 
 *    resource specification header (the parenthesized list after try). For some reason, this was 
 *    considered by the Java team to be, at times, somewhat awkward. JDK 9 added the ability to 
 *    define these variables before the try, if those variables are either explicitly or effectively 
 *    final.
 */
public class TryBlockWithResourcesTests {

  /**
   *
   */
  public TryBlockWithResourcesTests() {
    try {
      a();

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   *
   * @return
   * @throws Exception
   */
  boolean a() throws Exception {
    Socket s = new Socket();

    try (s) {
    }

    return s.isClosed();
  }

  public static void main(final String[] args) {
    final TryBlockWithResourcesTests clazz = new TryBlockWithResourcesTests();
  }
}
