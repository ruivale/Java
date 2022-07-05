package exp.jar;


import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.text.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ListJarContents {
  public ListJarContents() {
  }

  private static void listjar(String name) throws IOException {
    System.out.println("Jar: " + name);
    JarFile jar = new JarFile(name);


    Enumeration e = jar.entries();
    while (e.hasMoreElements()) {
      listInfo( (JarEntry) e.nextElement());
    }

    System.out.println();

  }


  private static void listInfo(JarEntry entry) {
    System.out.println("\t" + entry.getName());
  }

  public static void main(String args[]) {
      try {
        listjar("D:/JBProjects/GISTV/lib/IgStv.jar");
      } catch (IOException e) {
        System.err.println("Problems listing jar!");
      }

    for (int i = 0, n = args.length; i < n; i++) {
      try {
        listjar(args[i]);
      } catch (IOException e) {
        System.err.println("Problems reading: " + args[i]);
      }
    }
  }

}
