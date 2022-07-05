package jdk1_6examples.java.io.console;


import java.io.Console;
import java.sql.SQLException;

public class ConsoleWithFormatting {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Console console = System.console();
    if (console == null) {
      System.err.println("sales: unable to obtain console");
      return;
    }

    console.printf("%s ", "string");
  }
}
