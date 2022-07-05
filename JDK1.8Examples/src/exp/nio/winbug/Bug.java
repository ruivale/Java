package exp.nio.winbug;


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

import java.util.*;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class Bug {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   *
   * @throws IOException  Insert doc ...
   * @throws RuntimeException  Insert doc ...
   */
  public static void main(String[] args)
      throws IOException {
    // a selector
    final Selector selector = Selector.open();

    // ServerSocketChannel listening at port 9800
    ServerSocketChannel serverSocket = ServerSocketChannel.open();
    serverSocket.configureBlocking(true);
    serverSocket.socket()
                .bind(new InetSocketAddress(9800));

            // "client" SocketChannel starts to connect (non-blocking) to port 9800
    SocketChannel clsocket = SocketChannel.open();
    clsocket.configureBlocking(false);
    clsocket.connect(new InetSocketAddress("localhost", 9800));

    // "server" SocketChannel endpoint is accepted, configured as non-blocking,
    // and registered in the selector. interested only in OP_READ
    SocketChannel svsocket = serverSocket.accept();
    svsocket.configureBlocking(false);

    SelectionKey key = svsocket.register(selector, SelectionKey.OP_READ);

    // "client" SocketChannel finishes connection attempt
    while(clsocket.finishConnect()==false) {
      ;
    }

    // let's make sure everybody is connected and that the server endpoint
    // is registered
    System.out.println("client endpoint is connected? = " +
      clsocket.isConnected());
    System.out.println("server endpoint is connected? = " +
      svsocket.isConnected());
    System.out.println("server endpoint is registered? = " +
      svsocket.isRegistered());

    // start a thread that will forever selector.select(1000) and print what's going on
    new Thread() {
        public void run() {
          ByteBuffer buffer = ByteBuffer.allocate(1);

          while(true) {
            try {
                // 1. select with a quick timeout
              selector.select();

              // 2. check the selected keys
              Set selected = selector.selectedKeys();

              if(selected.size()>0) {
                System.out.println("select(1000) selected this many keys: " +
                  selected.size());

                Iterator it = selected.iterator();

                while(it.hasNext()) {
                  SelectionKey key = (SelectionKey)it.next();

                  // 3. handle readable keys by trying to read a byte from them
                  if(key.isReadable()) {
                      //the channel accused as readable
                    SocketChannel readyChannel = (SocketChannel)key.channel();

                    //clear buffer so it has one byte remaining to read
                    buffer.clear();

                    //read one byte
                    int r = readyChannel.read(buffer);

                    //print result of the read
                    System.out.println(
                      "isReadable() key handled, bytes read = " + r);
                  }
                }
              } else {
                System.out.println("select(1000) did not select any keys.");
              }
            } catch(IOException ex) {
              throw new RuntimeException(ex);
            }
          }
        }
      }.start();

      // now the main thread will write a byte that will show up in the registered
      // endpoint...
      // first sleep a bit to demonstrate that the "selector.select(1000);" will
      // return some times without selecting any keys (correct behavior)
    System.out.println(
      "MAIN thread sleeping for 5 seconds while the select(1000) thread loops approximately 5 times");

    try {
      Thread.sleep(5000);
    } catch(Exception ex) {
      ;
    }

    // now send a byte from the "client" endpoint to the "server" endpoint
    // (the one registered for OP_READ). this will make the select() return a
    // key set with one key (the registered key) where key.isReadable() == true
    System.out.println(
      "MAIN thread sending a byte from client channel to the server (registered for OP_READ) channel.");

    ByteBuffer onebyte = ByteBuffer.allocate(1);
    onebyte.clear();
    onebyte.put((byte)0); // put a byte
    onebyte.flip();

    while(clsocket.write(onebyte)!=1) {
      ;
    }

    // sleep for 3 more seconds
    try {
      Thread.sleep(3000);
    } catch(Exception ex) {
      ;
    }

    // wake up the selector
    System.out.println("Waking up the selector.");
    selector.wakeup();

    // MAIN thread not needed anymore; BUG: observe select(timeout) always
    // returning with an isReadable() key, but when attempting to read, no
    // bytes are available (socketchannel.read() returns 0)
    try {
      Thread.sleep(100000000);
    } catch(Exception ex) {
      ;
    }
  }
}
