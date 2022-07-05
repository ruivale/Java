package jdk1_6examples.console;


import java.io.Console;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
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
public class MyConsoleTests {
  public static void main(String[] args) {

    /***
    Console console = System.console();
    if (console != null) {
      String formatString = "%1$4s %2$10s %3$10s%n";
      console.printf(formatString, "Idx", "A", "B");
      console.printf(formatString, "1", "10", "100");
      console.printf(formatString, "2", "20", "200");
      console.printf(formatString, "3", "30", "300");
      console.printf(formatString, "4", "40", "400");
      String name = console.readLine("[Please Provide Your Name]: ");
      char[] passdata = console.readPassword("[Please Input Your Password]: ");
      Scanner scanner = new Scanner(console.reader());
      int value = 0;
      while (value != 99) {
        console.printf("Please input a value between 0 and 100.");
        value = scanner.nextInt();
      }

      PrintWriter out = console.writer();
      out.println("Test regular writing!");
    } else {
      throw new RuntimeException("Can't run w/out a console!");
    }
    /**/
  }
}
