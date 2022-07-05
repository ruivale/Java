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
public class MultiThreadedServer
    implements Runnable {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  private static int port = 9999;

  /** .. */
  private static Logger sm_logger = Logger.getLogger("MultiThreadedServer");

  //~ Instance fields //////////////////////////////////////////////////////////

//--------------------------------------------------------------------

  /** .. */
  private SocketChannel m_channel;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new MultiThreadedServer object.
   *
   * @param channel  Insert doc ...
   *
   * @throws IOException  Insert doc ...
   */
  public MultiThreadedServer(SocketChannel channel)
      throws IOException {
    m_channel = channel;
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
    ServerSocketChannel server = ServerSocketChannel.open();
    InetSocketAddress   isa = new InetSocketAddress(port);

    server.socket().bind(isa);

    int count = 0;

    while(true) {
      SocketChannel channel = server.accept();

      System.out.println("accept: " + (++count));

      MultiThreadedServer worker = new MultiThreadedServer(channel);
      Thread              thread = new Thread(worker);

      thread.setDaemon(true);

      thread.start();
    }
  }

  /**
   * Insert doc ...
   */
  public void run() {
    ByteBuffer buffer = ByteBuffer.allocateDirect(2048);

    int        bytes = 0;

    try {
      while((bytes = m_channel.read(buffer))>0) {
        buffer.flip();

        // process buffer
        buffer.clear();
      }

      System.out.println("connection closed");

      m_channel.close();
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
