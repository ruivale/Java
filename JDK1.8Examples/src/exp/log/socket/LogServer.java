package exp.log.socket;


import javax.net.ssl.*;
import javax.net.*;
import java.io.*;
import java.net.*;

public class LogServer {
  private static final int PORT_NUM = 5000;

  private static final String S_CHARSET = "ISO-8859-1";
  //private static final String S_CHARSET = "US-ASCII";

  public static void main(String args[]) {
    ServerSocketFactory serverSocketFactory = ServerSocketFactory.getDefault();
    ServerSocket serverSocket = null;

    try {
      serverSocket =
        serverSocketFactory.createServerSocket(PORT_NUM);

    } catch (IOException ignored) {
      System.err.println("Unable to create server");
      System.exit(-1);
    }
    System.out.println("LogServer running on port:"+ PORT_NUM);

    while (true) {
      Socket socket = null;
      try {
        socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(
          new InputStreamReader(is, S_CHARSET));

        java.nio.charset.Charset c;

        String line = null;
        while ((line = br.readLine()) != null) {
          System.out.println(line);
        }
      } catch (IOException exception) {
        // Just handle next request.
      } finally {
        if (socket != null) {
          try {
            socket.close();
          } catch (IOException ignored) {
          }
        }
      }
    }
  }
}
