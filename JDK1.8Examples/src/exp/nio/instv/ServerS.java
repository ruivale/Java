package exp.nio.instv;

import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.net.InetAddress;
import java.nio.ByteBuffer;
//import com.ent.stv.oper.codecs.ConsoleVideoListener;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.Iterator;
import java.util.Set;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.CharBuffer;

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
public class ServerS {
  /** The channels charset */
  public static final Charset charset = Charset.forName("ISO-8859-1");

  /**
   *
   */
  public ServerS() {

    try {
      // Create the server socket channel
      final ServerSocketChannel server = ServerSocketChannel.open();
      // nonblocking I/O
      server.configureBlocking(false);
      // host-port
      server.socket().bind(new java.net.InetSocketAddress(
          InetAddress.getLocalHost(), 7676));
      // Create the selector
      final Selector selector = Selector.open();
      // Recording server to selector (type OP_ACCEPT)
      server.register(selector, SelectionKey.OP_ACCEPT);

      int index = -1;

      // Server loop
      while (true) {
        index++;

        // Waiting for events
        selector.select();
        // Get keys
        final Set keys = selector.selectedKeys();
        final Iterator i = keys.iterator();

        // For each keys...
        while (i.hasNext()) {
          final SelectionKey key = (SelectionKey) i.next();

          // Remove the current key
          i.remove();

          // if isAccetable = true then a client required a connection
          if (key.isAcceptable()) {

            //System.out.println("key isAcceptable: "+key.toString()+".");

            // get client socket channel
            final SocketChannel client = server.accept();
            // Non Blocking I/O
            client.configureBlocking(false);
            // recording to the selector (reading)
            client.register(selector, SelectionKey.OP_READ);

            // if isReadable = true then the server is ready to read
          } else if (key.isReadable()) {

            //System.out.println("key isReadable: "+key.toString()+".");

            final SocketChannel client = (SocketChannel) key.channel();
            // Read byte coming from the client
            final ByteBuffer buffer = ByteBuffer.allocate(4096*2);

            try {
              int intBytesRead = client.read(buffer);

              if(intBytesRead == -1){ // End of stream ...

System.out.println("\n\nEND OF STREAM");

              }

              // Show bytes on the console
              buffer.flip();

              //CharsetDecoder decoder = charset.newDecoder();
              //CharBuffer charBuffer = decoder.decode(buffer);

              //System.out.println("RECEIVED: " + charBuffer.toString().trim() + ".");

System.out.println("RECEIVED ...");


              /*
                               {
                ByteBuffer buffer_ = null;

                buffer_ = ByteBuffer.wrap(buffer.array());
                CharsetDecoder decoder_ = charset.newDecoder();
                CharBuffer charBuffer_ = decoder_.decode(buffer_);
                buffer_.flip();
                client.write(buffer_);

               System.out.println("GW - Sended: "+charBuffer_.toString().trim()+".");
                               }
               */

            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) throws Exception {

    InetAddress ia = InetAddress.getByName("172.18.50.70");


//    (new Thread() {
//      public void run() {
//        new ServerS();
//      }
//    }).start();
//
//
//    try {
//      Thread.sleep(3000);
//    } catch (Exception ex) {
//
//    }

/*
    (new Thread() {
      public void run() {
        new ClientC("First", 3200);
      }
    }).start();


    (new Thread() {
      public void run() {
        new ClientC("Second", 4070);
      }
    }).start();
    (new Thread() {
      public void run() {
        new ClientC("Third", 6400);
      }
    }).start();
    (new Thread() {
      public void run() {
        new ClientC("Fourth", 2400);
      }
    }).start();
    (new Thread() {
      public void run() {
        new ClientC("Fifth", 5400);
      }
    }).start();
    (new Thread() {
      public void run() {
        new ClientC("Sixth", 7400);
      }
    }).start();
*/
  }
}




