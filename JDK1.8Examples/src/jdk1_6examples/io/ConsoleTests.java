package jdk1_6examples.io;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public class ConsoleTests {
  static final int MIN_PASSWORD_LENGTH = 8;

  public static void main(final String[] args) {
    char[] password;
    do {
      password = System.console().readPassword(
          "Enter password (minimum of %d characters): ", MIN_PASSWORD_LENGTH);
    } while (password.length < MIN_PASSWORD_LENGTH);
  }

}
