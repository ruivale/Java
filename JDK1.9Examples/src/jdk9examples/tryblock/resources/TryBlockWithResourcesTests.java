package jdk9examples.tryblock.resources;

import java.net.Socket;


/**
 *
 * @author 2334
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
