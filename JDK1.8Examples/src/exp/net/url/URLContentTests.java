package exp.net.url;

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
public class URLContentTests {
  public URLContentTests() {
    try {
      final URL url = new URL("http://172.18.56.69/cgi-bin/list.cgi?UID=39971&HTML=01_07_010.html&TEMP=1193310778906");
      final Object obj = url.getContent();

      if(obj != null){
        System.out.println("Contents not null. Object:"+obj+".");

        if(obj instanceof InputStream){
          System.out.println("\tContents obj is a InputStream.");

          final InputStream is = (InputStream)obj;
          final byte[] bytes = new byte[is.available()];
          is.read(bytes);
          final String strContents = new String(bytes);

          System.out.println("Contents=\n" + strContents+"\n");
        }

      }else{
        System.out.println("Contents ARE NULL!");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    URLContentTests urlcontenttests = new URLContentTests();
  }
}
