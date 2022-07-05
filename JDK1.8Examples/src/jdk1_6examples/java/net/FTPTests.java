package jdk1_6examples.java.net;

import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;


/**
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
public class FTPTests {
  public FTPTests() throws Exception {

    SocketAddress addr = new InetSocketAddress("172.18.2.6", 3000);
    Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
    URL url = new URL("ftp://ftp.ua.pt");
    URLConnection conn = url.openConnection(proxy);
    Object obj = conn.getContent();

    if(obj != null){
      System.out.println("Contents ok");

      if (obj != null && obj instanceof InputStream) {
        BufferedReader br =
            new BufferedReader(new InputStreamReader((InputStream)obj));
        String strLine;

        while((strLine = br.readLine()) != null){
          System.out.println(strLine);
        }
      }


    }else{
      System.out.println("no content at all");
    }

  }

  public static void main(String[] args){
    try {
      FTPTests ftptests = new FTPTests();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
