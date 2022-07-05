package jdk1_6examples.cookies.retrieval;


import java.io.*;
import java.net.*;
import java.util.*;

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
public class FetchCookies {
  public static void main(String args[])
      throws Exception {
    Console console = System.console();
    /**
    if (args.length == 0) {
      System.err.println("URL missing");
      System.exit( -1);
    }
    /**/
    String urlString = "http://www.sun.com";//args[0];
    CookieManager manager = new CookieManager();
    manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
    CookieHandler.setDefault(manager);
    URL url = new URL(urlString);
    URLConnection connection = url.openConnection();
    Object obj = connection.getContent();
    url = new URL(urlString);
    connection = url.openConnection();
    obj = connection.getContent();
    CookieStore cookieJar = manager.getCookieStore();
    List<HttpCookie> cookies = cookieJar.getCookies();
    for (HttpCookie cookie : cookies) {
      console.printf("Cookie: %s%n", cookie);
    }
  }
}
