package exp.nio.intronio;

import java.nio.channels.*;
import java.nio.*;

import java.util.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

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
public class Server
    implements IntroNIOInt {
  /**
   *
   */
  public Server() {
    try {
      // Create the server socket channel
      ServerSocketChannel server = ServerSocketChannel.open();
      // nonblocking I/O
      server.configureBlocking(false);
      // host-port 8000
      server.socket().bind(new java.net.InetSocketAddress(HOST, PORT));
      System.out.println("active server on port "+PORT+".");
      // Create the selector
      Selector selector = Selector.open();
      // Recording server to selector (type OP_ACCEPT)
      server.register(selector, SelectionKey.OP_ACCEPT);

      // Infinite server loop
      for (int k=1;k>0 ;k++ ) {

if((k % 1000)==0)
  System.out.println(""+k);

        // Waiting for events
        selector.select();
        // Get keys
        Set keys = selector.selectedKeys();
        Iterator i = keys.iterator();

        // For each keys...
        while (i.hasNext()) {
          SelectionKey key = (SelectionKey) i.next();

          // Remove the current key
          i.remove();

          // if isAccetable = true
          // then a client required a connection
          if (key.isAcceptable()) {

//System.out.println("key.isAcceptable(): "+key.toString()+".");

            // get client socket channel
            SocketChannel client = server.accept();
            // Non Blocking I/O
            client.configureBlocking(false);
            // recording to the selector (reading)
            client.register(selector,
                            SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            continue;
          }

          // if isReadable = true
          // then the server is ready to read
          if (key.isReadable()) {

//System.out.println("key.isReadable(): "+key.toString()+".");

            SocketChannel client = (SocketChannel) key.channel();

            // Read byte coming from the client
            int BUFFER_SIZE = 1024;
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            try {
              if(client.read(buffer) < 0){
                client.close();

                System.out.println("\n\nSERVER SOCKET CLOSED\n\n ");

              }
            } catch (Exception e) {
              // client is no longer active
              e.printStackTrace();
              continue;
            }

            // Show bytes on the console
            buffer.flip();
            Charset charset = Charset.forName("ISO-8859-1");
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            System.out.println(charBuffer.toString());
            continue;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    (new Thread() {
      public void run() {
        Server server = new Server();
      }
    }).start();

    try {
      Thread.sleep(4000);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    (new Thread() {
      public void run() {
        new Client(1, 1200);
      }
    }).start();
    (new Thread() {
      public void run() {
        new Client(2, 2000);
      }
    }).start();
    (new Thread() {
      public void run() {
        new Client(3,750);
      }
    }).start();
  }
}








