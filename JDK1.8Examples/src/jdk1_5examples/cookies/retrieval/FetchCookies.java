package jdk1_5examples.cookies.retrieval;


import java.io.*;
import java.net.*;
import java.util.*;

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
 */
public class FetchCookies {
  public static void main(String args[])
      throws Exception {
    if (args.length == 0) {
      System.err.println("URL missing");
      System.exit( -1);
    }
    String urlString = args[0];
    //CookieHandler.setDefault(new ListCookieHandler());
    URL url = new URL(urlString);
    URLConnection connection = url.openConnection();
    Object obj = connection.getContent();
    url = new URL(urlString);
    connection = url.openConnection();
    obj = connection.getContent();
  }
}
