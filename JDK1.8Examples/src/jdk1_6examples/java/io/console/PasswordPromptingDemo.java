package jdk1_6examples.java.io.console;

import java.io.Console;
import java.util.Arrays;

public class PasswordPromptingDemo {

  public static void main(String[] args) {
    Console console = System.console();

    if (console == null) {
      System.out.println("Console is not available");
      System.exit(1);
    }

    char[] password = "mustang".toCharArray();

    char[] passwordEntered = console.readPassword("Enter password: ");

    if (Arrays.equals(password, passwordEntered)) {
      System.out.println("\n Access granted \n");
      Arrays.fill(password, ' ');
      Arrays.fill(passwordEntered, ' ');
      System.out.println("OK ...");
    } else {
      System.out.println("Access denied");
      System.exit(1);
    }
  }
}
