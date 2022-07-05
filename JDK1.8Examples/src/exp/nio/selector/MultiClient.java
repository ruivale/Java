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
import java.io.*;

import java.net.*;

import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class MultiClient
    implements Runnable {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private ByteBuffer     m_buffer;

  /** .. */
  private CharsetDecoder m_decoder;

  /** .. */
  private CharsetEncoder m_encoder;

  /** .. */
  private SocketChannel m_channel;

//--------------------------------------------------------------------

  /** .. */
  private String m_id;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new MultiClient object.
   *
   * @param address  Insert doc ...
   * @param id  Insert doc ...
   *
   * @throws InterruptedException  Insert doc ...
   * @throws IOException  Insert doc ...
   */
  public MultiClient(
    InetSocketAddress address,
    String            id)
      throws InterruptedException, IOException {
    m_id = id;

    Charset charset = Charset.forName("UTF-8");

    m_decoder   = charset.newDecoder();
    m_encoder   = charset.newEncoder();

    m_channel = SocketChannel.open();
    m_channel.connect(address);

    if(id.equals("0")) {
      Socket socket = m_channel.socket();
      System.out.println("SO_SNDBUF=" + socket.getSendBufferSize() +
        ",SO_TIMEOUT=" + socket.getSoTimeout() + ",SO_KEEPALIVE=" +
        socket.getKeepAlive());
    }

    byte[] buf = new byte[1024]; // bufsize = 1K
    Arrays.fill(buf, (byte)m_id.charAt(0));

    m_buffer = ByteBuffer.allocateDirect(1024);
    m_buffer.put(buf);
    m_buffer.flip();

    Thread.currentThread()
          .sleep(50L);
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

    //if(args.length<1) {
      //System.out.println("usage: java MultiClient number [host]");
      //System.exit(1);
    //}

    int               number = 4;//Integer.parseInt(args[0]);
    String            host = /*(args.length==2)
      ? args[1]
      : */"localhost";

    Thread[]          threads = new Thread[number];

    InetSocketAddress address = new InetSocketAddress(host, 9999);

    for(int i = 0; i<number; i++) {
      MultiClient client = new MultiClient(address,
          Integer.toString(i));

      threads[i] = new Thread(client);
      threads[i].setDaemon(true);
    }

    for(int i = 0; i<number; i++) {
      threads[i].start();
    }

    for(int i = 0; i<number; i++) {
      threads[i].join();
    }
  }

  /**
   * Insert doc ...
   */
  public void run() {
    System.out.print(m_id);

    try {
      while(true) {
        m_channel.write(m_buffer);
        m_buffer.rewind();

        Thread.currentThread()
              .sleep(1000L);
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(InterruptedException ie) {
      System.err.println(ie.toString());
    }
  }
}
