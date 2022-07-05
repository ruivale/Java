package jdk1_5examples.threads.pools;


import java.util.concurrent.*;
import java.io.*;
import java.net.*;

/**
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
public class ReliableWebServer {
  private static final int NBR_OF_THREADS = 10;
  private static final int PORT = 80;
  Executor pool = Executors.newFixedThreadPool(NBR_OF_THREADS);

  /**
   *
   */
  public ReliableWebServer() {
    java.util.concurrent.ThreadPoolExecutor l;
    try{
      ServerSocket socket = new ServerSocket(PORT);
      while (true) {
        final Socket conn = socket.accept();
        Runnable r = new Runnable() {
          public void run() {
            System.out.println("AH!AH!AH!");
          }
        };
        pool.execute(r);
      }
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ReliableWebServer reliablewebserver = new ReliableWebServer();
  }
}
