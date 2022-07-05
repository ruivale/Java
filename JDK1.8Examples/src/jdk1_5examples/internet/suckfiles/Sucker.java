// %1142528112784:jdk1_5examples.internet.suckfiles%
package jdk1_5examples.internet.suckfiles;


import java.io.*;

import java.net.*;

import java.util.*;


/**
 * <p>
 * Title: JDK1.5 Examples
 * </p>
 *
 * <p>
 * Description: Examples for the Java5.
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company: ??????????
 * </p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class Sucker {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private final Stats  stats;

  /** .. */
  private final String outputFile;

  /** .. */
  private final URL url;

  static{
    System.getProperties().put("proxySet", "true");
    System.getProperties().put("proxyHost", "172.18.2.6");
    System.getProperties().put("proxyPort", "3000");

    Authenticator.setDefault(new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
          "c2334", "32padre".toCharArray());
      }
    });
  }


  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new Sucker object.
   *
   * @param path  Insert doc ...
   * @param outputFile  Insert doc ...
   *
   * @throws IOException  Insert doc ...
   */
  public Sucker(String path,
                String outputFile)
      throws IOException {
    this.outputFile = outputFile;
    System.out.println(new Date() + " Constructing Sucker");
    url = new URL(path);
    System.out.println(new Date() + " Connected to URL");
    stats = Stats.make(url);
  }

  /**
   * Creates a new Sucker object.
   *
   * @param path  Insert doc ...
   *
   * @throws IOException  Insert doc ...
   */
  public Sucker(String path)
      throws IOException {
    this(path,
         path.replaceAll(".*\\/",
                         ""));
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   *
   * @throws IOException  Insert doc ...
   */
  public static void main(String[] args)
      throws IOException {
    String file   = "http://www.javaspecialists.co.za/pics/TsinghuaClass.jpg";
    Sucker sucker = new Sucker(file);

    /**
     * Sucker sucker; switch (args.length) { case 1: sucker = new
     * Sucker(args[0]); break; case 2: sucker = new Sucker(args[0], args[1]);
     * break; default: usage(); return; }
     */
    sucker.downloadFile();
  }

  /**
   * Insert doc ...
   *
   * @throws IOException  Insert doc ...
   */
  private void downloadFile()
      throws IOException {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
        public void run() {
          stats.print();
        }
      },
                   1000,
                   1000);

    try {



      System.out.println(new Date() + " Opening Streams");

      InputStream  in  = url.openStream();
      OutputStream out = new FileOutputStream(outputFile);
      System.out.println(new Date() + " Streams opened");

      byte[] buf    = new byte[1024 * 1024];
      int    length;

      while((length = in.read(buf))!=-1) {
        out.write(buf,
                  0,
                  length);
        stats.bytes(length);
      }

      in.close();
      out.close();
    } finally {
      timer.cancel();
      stats.print();
    }
  }



  /**
   * Insert doc ...
   */
  private static void usage() {
    System.out.println("Usage: java Sucker URL [targetfile]");
    System.out.println("\tThis will download the file at the URL " +
                       "to the targetfile location");
    System.exit(1);
  }
}
