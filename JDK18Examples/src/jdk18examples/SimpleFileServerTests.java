package jdk18examples;

import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public final class SimpleFileServerTests {

  public static void main(String[] args) {
    try {
      var server = 
        SimpleFileServer.createFileServer(
          new InetSocketAddress(8080), 
          Path.of("/some/path"), 
          OutputLevel.VERBOSE);
      
      server.start();

    } catch (Exception e) {
      e.printStackTrace();;
    }
  }
}
