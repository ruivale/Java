package exp.nio.instv;

import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.Iterator;
import java.util.Set;

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
public class ClientC {
  private boolean boolClientContinue = true;

  /**
   *
   */
  public ClientC(String name, int intWait){
    try {
      Thread.sleep(intWait);

      // The client
      // Create client SocketChannel
      final SocketChannel client = SocketChannel.open();

      // nonblocking I/O
      client.configureBlocking(false);

      // Connection to host port 8000
      client.connect(new java.net.InetSocketAddress(
          "172.18.50.175",
          7676));

      // Create selector
      final Selector selector = Selector.open();

      // Record to selector (OP_CONNECT type)
      final SelectionKey clientKey = client.register(selector,SelectionKey.OP_CONNECT);


      // Waiting for the connection
      while (selector.select(500) > 0) {


        // Get keys
        final Set keys = selector.selectedKeys();
        final Iterator i = keys.iterator();

        // For each key...
        while (i.hasNext()) {
          final SelectionKey key = (SelectionKey) i.next();

          // Remove the current key
          i.remove();

          // Get the socket channel held by the key
          final SocketChannel channel = (SocketChannel) key.channel();

          // Attempt a connection
          if (key.isConnectable()) {
            // Connection OK
            //System.out.println("CLIENT - Server Found");

            // Close pendent connections
            if (channel.isConnectionPending()) {
              channel.finishConnect();
            }

            // Write continuously on the buffer
            ByteBuffer buffer = null;
            String s = name + " - " + key.toString();

            for (int j = 0; j < 3; j++) {

              try {
                Thread.sleep(intWait);
              } catch (Exception ex) {
                ;
              }

              buffer = ByteBuffer.wrap(s.getBytes());
              channel.write(buffer);
              buffer.clear();
              s = s +";"+ j;
            }

            /*
             buffer = ByteBuffer.wrap(strEndConnectionPattern.getBytes());
                           channel.write(buffer);
                           buffer.clear();

                           {
              // Wainting for the DISCONNECT sent by the server
              System.out.println("##### - Client reading ...");
              channel.read(buffer);
              // Show bytes on the console
              buffer.flip();
              final CharsetDecoder decoder = charset.newDecoder();
              final CharBuffer charBuffer = decoder.decode(buffer);
              System.out.println("##### - The " + str2Send +
                                 " has terminated it's operations. buffer=" +
                                 charBuffer.toString() + ".");
                           }
             */

            System.out.println("Finish connect "+name+".");
            channel.finishConnect();

          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
