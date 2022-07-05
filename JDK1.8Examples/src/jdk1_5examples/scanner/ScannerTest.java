package jdk1_5examples.scanner;


import java.util.Scanner;
import java.io.*;

/**
 *
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
 *
 * File logfile.txt:
 * -----------------
 *     entry 2006 01 11 1043 meeting Smith, John
 *     exit 2006 01 11 1204 Smith, John
 *     entry 2006 01 11 1300 work Eubanks, Brian
 *     exit 2006 01 11 2120 Eubanks, Brian
 *     alarm 2006 01 11 2301 fire This was a drill
 *
 *  The output of the program is:
 *     2006/1/11@1043 entry meeting: Smith, John
 *     2006/1/11@1204 exit Smith, John
 *     2006/1/11@1300 entry work: Eubanks, Brian
 *     2006/1/11@2120 exit Eubanks, Brian
 *     2006/1/11@2301 alarm fire: This was a drill
 *
 */
public class ScannerTest {
  public static void main(String[] args)
      throws IOException {
    Scanner scanner = new Scanner(new FileReader("logfile.txt"));
    while (scanner.hasNext()) {
      String type = scanner.next();
      int year = scanner.nextInt();
      int month = scanner.nextInt();
      int day = scanner.nextInt();
      int time = scanner.nextInt();
      System.out.printf("%d/%d/%d@%d", year, month, day, time);
      if (type.equals("entry")) {
        String purpose = scanner.next();
        String restOfLine = scanner.nextLine().trim();
        System.out.printf(" entry %s: %s%n", purpose, restOfLine);
      } else if (type.equals("exit")) {
        String exitName = scanner.nextLine().trim();
        System.out.printf(" exit %s%n", exitName);
      } else if (type.equals("alarm")) {
        String alarmType = scanner.next();
        String comment = scanner.nextLine().trim();
        System.out.printf(" alarm %s: %s%n", alarmType, comment);
      } else {
        throw new IllegalArgumentException();
      }
    }
    scanner.close();
  }
}
