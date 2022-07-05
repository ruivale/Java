package jdk1_6examples.java.io.console;



import java.io.Console;
import java.sql.SQLException;

public class ConsoleAndPassword {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Console console = System.console();
    if (console == null) {
      System.err.println("sales: unable to obtain console");
      return;
    }

    String password = new String (console.readPassword ("Enter password: "));
    System.out.println(password);
  }
}
