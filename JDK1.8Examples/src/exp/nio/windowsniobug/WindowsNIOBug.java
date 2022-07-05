package exp.nio.windowsniobug;

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
import java.util.*;
import java.nio.channels.*;
import java.io.*;
import java.net.*;

/**
 * <p>Title: Windows NIO bug test case</p>
 * <p>Description: This is a simple program to run to test the windows NIO 
 * problem with Selector.select() when its selecting for OP_ACCEPT on a 
 * ServerSocketChannel and the network cable is unplugged. Simply run
 * the program on Windows XP and unplug the network cable when instructed. </p>
 * <p>Copyright: Copyright (c) 2003 Heroix Corp.</p>
 * <p>Company: Heroix Corp</p>
 * @author Jonthan Hess ( xxxxx@xxxxx )
 * @version 1.0
 */

public class WindowsNIOBug
    implements Runnable {
  /** The connection selector for the server socket.  This is used to find connections */
  private Selector connectionSelector;

  /**The server socket that is listening for connections */
  private ServerSocketChannel ssc;

  /**The socket address that we are listening on.  This is an argument to the constructor*/
  private SocketAddress address;

  public WindowsNIOBug() {
    address = new InetSocketAddress(8987);
    System.out.println("Opening socket on port 8987");
    try {
      ssc = ServerSocketChannel.open();
      this.connectionSelector = Selector.open();

      this.ssc.configureBlocking(false);
      this.ssc.socket().bind(address);
      this.ssc.register(this.connectionSelector, SelectionKey.OP_ACCEPT);
    } catch (IOException ex) {
      System.out.println("Error opening the socket on port 8987");
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println("Creating new instance of WindowsNioBug");
    WindowsNIOBug bug = new WindowsNIOBug();
    Thread t = new Thread(bug);
    t.start();

    System.out.println("Waiting 3 seconds, then attempting to connect");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException ex) {
    }
    System.out.println("Now attemting to connect");
    try {
      Socket s = new Socket();
      s.connect(bug.address);
      s.close();
      System.out.println("Done connecting");
    } catch (IOException ex1) {
      System.out.println("Error while connecting to server socket");
      ex1.printStackTrace();
    }
    System.out.println("Now, unplug your network cable and wait about 15 seconds.  The loop in listenForConnections() will spin infinately around Selector.select()");
    System.out.println("This is not the behavoir");
  }

  public void run() {
    System.out.println(
        "Listening for connections.  Telnet to port 8987 to prove that it's working");
    listenForConnections();
  }

  /**
   * Listens for connections on the server socket and hands them off to
   * handleConnection(SocketChanel sc) when a connection is received.
   */
  private void listenForConnections() {
    System.out.println("listenForConnections");
    try {
      while (true) {
        // this next operation is the one that is broken
        int keys = connectionSelector.select(); // select all connections that need to be serviced, blocks until service requiered

        System.out.println(
            "<accept loop>  connectionSelector.select() has returned " + keys);
        System.out.println("<accept loop> ssc.isOpen() " + ssc.isOpen());
        System.out.println("<accept loop> ssc.isRegistered() " +
                           ssc.isRegistered());
        System.out.println("<accept loop> ssc.isBlocking() " + ssc.isBlocking());

        // iterate through available connections
        for (Iterator i = connectionSelector.selectedKeys().iterator();
             i.hasNext(); ) {
          SelectionKey key = (SelectionKey) i.next();
          i.remove();
          System.out.println("<accept loop> Selected key: " + key);

          ServerSocketChannel readyServer = (ServerSocketChannel) key.
              channel();
          SocketChannel sc = readyServer.accept(); // this is non-blocking, returns null if none are ready

          if (sc != null) {
            System.out.println("<accept loop> The connection was good, closing");
            sc.close();
          }

        }
      }
    } catch (IOException ex) {
      System.out.println("<accept loop> IOException while handling connections");
      ex.printStackTrace();
    } finally {
      System.out.println(
          "<accept loop> exiting AcceptConnectionThread.listenForConnections");
    }
  }

}
