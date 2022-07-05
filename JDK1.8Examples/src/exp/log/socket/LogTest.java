package exp.log.socket;


import java.io.IOException;
import java.util.logging.*;


public class LogTest {
  private static Logger logger = Logger.getAnonymousLogger();

  public static void main(String argv[]) throws IOException {
    Handler handler = new SocketHandler("localhost", 5000);
    handler.setFormatter(new SimpleFormatter());
    logger.addHandler(handler);
    logger.log(Level.SEVERE, "Hello, World camião, carroça ...");
    logger.log(Level.INFO, "Welcome Home");
  }
}
