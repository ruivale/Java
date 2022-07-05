package exp.internet;

import java.net.*;
import java.io.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class WebSiteReader {
  public static void main(String args[]) {
    String nextLine;
    URL url = null;
    URLConnection urlConn = null;
    InputStreamReader inStream = null;
    BufferedReader buff = null;
    try {
      System.setProperty("http.proxyHost","172.18.2.6");
      System.setProperty("http.proxyPort", "3000");

      // Create the URL obect that points
      // at the default file index.html
      url = new URL("http://www.yahoo.com");
      urlConn = url.openConnection();
      inStream = new InputStreamReader(
          urlConn.getInputStream());
      buff = new BufferedReader(inStream);

      // Read and print the lines from index.html
      while (true) {
        nextLine = buff.readLine();
        if (nextLine != null) {
          System.out.println(nextLine);
        } else {
          break;
        }
      }
    } catch (MalformedURLException e) {
      System.out.println("Please check the URL:" +
                         e.toString());
    } catch (IOException e1) {
      System.out.println("Can't read  from the Internet: " +
                         e1.toString());
    }
  }
}
