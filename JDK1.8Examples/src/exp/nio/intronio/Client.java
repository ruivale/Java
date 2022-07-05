package exp.nio.intronio;

import java.nio.channels.*;
import java.nio.*;

import java.util.*;

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
public class Client
    implements IntroNIOInt {
  public Client(int id, int sleep) {
    try {
      // Create client SocketChannel
      SocketChannel client = SocketChannel.open();

      // nonblocking I/O
      client.configureBlocking(false);

      // Connection to host port 8000
      client.connect(new java.net.InetSocketAddress(HOST, PORT));

      // Create selector
      Selector selector = Selector.open();

      // Record to selector (OP_CONNECT type)
      SelectionKey clientKey = client.register(selector,
                                               SelectionKey.OP_CONNECT);

      // Waiting for the connection
      while (selector.select() > 0) {

        // Get keys
        Set keys = selector.selectedKeys();
        Iterator i = keys.iterator();

        // For each key...
        while (i.hasNext()) {
          SelectionKey key = (SelectionKey) i.next();

          // Remove the current key
          i.remove();

          // Get the socket channel held by the key
          SocketChannel channel = (SocketChannel) key.channel();

          // Attempt a connection
          if (key.isConnectable()) {

            // Connection OK
            System.out.println("Server Found");

            // Close pendent connections
            if (channel.isConnectionPending()) {
              channel.finishConnect();
            }

            // Write continuously on the buffer
            ByteBuffer buffer = null;
            for (int j=0;j<3 ; j++) {

              try {
                Thread.sleep(sleep);
              } catch (Exception ex) {
                ex.printStackTrace();
              }

              buffer =
                  ByteBuffer.wrap(
                      new String("Client " + id + ".").getBytes());
              channel.write(buffer);
              buffer.clear();
            }

            System.out.println("Client finishing");
            channel.finishConnect();
            channel.close();


          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String a[]){
    new Client(12, 1000);
  }
}
