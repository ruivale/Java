package exp.nio.selector;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 */
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class Server4
    implements Runnable {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  private static int port = 9999;

  /** .. */
  private static Logger sm_logger = Logger.getLogger("Server4");

  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private CharsetDecoder m_decoder;

  /** .. */
  private CharsetEncoder m_encoder;

//--------------------------------------------------------------------

  /** .. */
  private Selector m_selector;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new Server4 object.
   *
   * @throws IOException  Insert doc ...
   */
  public Server4()
      throws IOException {
    ServerSocketChannel server = ServerSocketChannel.open();
    InetSocketAddress   isa = new InetSocketAddress(port);

    server.configureBlocking(false);
    server.socket()
          .bind(isa);

    m_selector = Selector.open();

    server.register(m_selector, SelectionKey.OP_ACCEPT);

//-----
    Charset utf8 = Charset.forName("UTF-8");
    m_decoder   = utf8.newDecoder();
    m_encoder   = utf8.newEncoder();
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  public static void main(String[] args)
      throws Exception {
    Server4 server = new Server4();
    Thread  thread = new Thread(server);
    thread.setDaemon(true);

    thread.start();

    thread.join();
  }

  /**
   * Insert doc ...
   */
  public void run() {
    int count = 0;

    try {
      ByteBuffer buffer = ByteBuffer.allocateDirect(2048);

//FileOutputStream fos = new FileOutputStream("server4.dat");
//FileChannel fc = fos.getChannel();
      while(m_selector.select()>0) {
        Set keys = m_selector.selectedKeys();

        for(Iterator itr = keys.iterator(); itr.hasNext();) {
          SelectionKey key = (SelectionKey)itr.next();

          itr.remove();

          if(key.isAcceptable()) {
            System.out.println("accept: " + (++count));

            ServerSocketChannel server = (ServerSocketChannel)key.channel();

            SocketChannel       channel = server.accept();
            channel.configureBlocking(false);

            channel.register(m_selector, SelectionKey.OP_READ);

          } else {
            SocketChannel channel = null;

            try {
              if(key.isReadable()) {
                channel = (SocketChannel)key.channel();

                int bytes = channel.read(buffer);

                if(bytes<=0) { // Linux does not throw IOException
                  channel.close(); // will also cancel key
                  System.out.println("connection closed " + count);
                } else {
                  buffer.flip();

                  //fc.write(buffer);
                  buffer.clear();
                }
              }
            } catch(IOException ioe) { // connection closed by client
              System.out.println("readable: " + ioe.getMessage());
              sm_logger.log(
                Level.INFO,
                ioe.getMessage(),
                ioe);

              Throwable cause = ioe.getCause();

              if(cause!=null) {
                System.out.println("cause: " + cause.getClass().getName() +
                  ": " + cause.getMessage());
              }

              channel.close(); // will also cancel key

              --count;
            }
          }
        }
      }
    } catch(IOException e) {
      System.out.println("run: " + e.getMessage());
      sm_logger.log(
        Level.SEVERE,
        e.getMessage(),
        e);
    } catch(Exception e) {
      System.out.println("run: " + e.getMessage());
      sm_logger.log(
        Level.SEVERE,
        e.getMessage(),
        e);
    }
  }
}
