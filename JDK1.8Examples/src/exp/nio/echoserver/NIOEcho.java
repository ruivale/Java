package exp.nio.echoserver;


/*******************************************************************************************
        NIOEcho.java
        Simple echo server based on NIO Sockets.
        author PKWooster, Oct 2003 GPL license

        this class is self contained.
        startup is java NIOEcho -pport -eoff -dlevel
        port is port number
        off is the text off to throw data in the bit bucket
        level is lowest level message displayed
        default is NIOEcho -p5050 -d1
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;


public class NIOEcho {
  private boolean running; // true if the server is active
  private ServerSocket ss; // the listening socket
  private ServerSocketChannel sschan; // the listening channel
  private Selector selector; // the only selector
  private static int debugLevel = 1; // only print at this level and higher
  private boolean echo;

  // starts the server, binds to a port and runs the select
  private void start(int port, boolean eon) {
    echo = eon;
    int n = 0;
    Iterator it;
    SelectionKey key;
    Object att;
    int io;
    running = true;
    int j = 0;

    try {
      sschan = ServerSocketChannel.open();
      sschan.configureBlocking(false);
      ss = sschan.socket();
      ss.bind(new InetSocketAddress(port));
      selector = Selector.open();
      sschan.register(selector, SelectionKey.OP_ACCEPT);
    } catch (IOException ie) {
      fail(ie, "startup failed");
    }

    while (running) {
      // now we select any pending io
      try {
        n = selector.select();
      } catch (Exception e) { // select
        fail(e, "select failed");
      }
      dout(0, "select n=" + n);
      if (n == 0) {
        if (10 < j++) {
          fail(null, "loop detected");
        }
      } else {
        j = 0;
      }

      // process any selected keys
      Set selectedKeys = selector.selectedKeys();
      it = selectedKeys.iterator();
      while (it.hasNext()) {
        key = (SelectionKey) it.next();
        int kro = key.readyOps();
        dout(0, "kro=" + kro);
        if ((kro & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
          doRead(key);
        }
        if ((kro & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE) {
          doWrite(key);
        }
        if ((kro & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
          doAccept(key);
        }
        it.remove(); // remove the key
      }
    }
  }

  void doAccept(SelectionKey sk) {
    ServerSocketChannel sc = (ServerSocketChannel) sk.channel();
    dout(2, "accept");
    SocketChannel usc = null;
    ByteBuffer data;
    try {
      usc = sc.accept();
      usc.configureBlocking(false);
      data = ByteBuffer.allocate(8192);
      data.position(data.limit()); // looks like write complete
      usc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, data);
    } catch (IOException re) {
      fail(re, "registration error");
    }
  }

  void doRead(SelectionKey sk) {
    int len = 0;
    int wlen = 0;
    dout(1, "read");
    SocketChannel sc = (SocketChannel) sk.channel();
    ByteBuffer data = (ByteBuffer) sk.attachment();

    if (data.hasRemaining()) {
      fail(null, "stuck");
    }
    data.clear();
    try {
      len = sc.read(data);
    } catch (IOException e) {
      e.printStackTrace();
      len = -1;
    }
    if (len > 0) {
      if (echo) {
        data.flip();
        if (data.get(0) == -1) {
          len = -1; // first byte 255 requests close
        } else {
          try {
            wlen = sc.write(data);
          } catch (Exception e) {
            e.printStackTrace();
            len = -1;
            wlen = -1;
          }
          if (wlen < len) {
            dout(12, "write blocked");
            sk.interestOps(SelectionKey.OP_WRITE); // hung until we can write
          }
        }
      } else {
        data.position(data.limit()); // echo off looks like write complete
      }
    }
    if (len < 0) {
      close(sc);
    }
  }


  void doWrite(SelectionKey sk) {
    dout(12, "write ready");
    SocketChannel sc = (SocketChannel) sk.channel();
    ByteBuffer data = (ByteBuffer) sk.attachment();

    if (data.hasRemaining()) {
      try {
        sc.write(data);
      } catch (IOException e) {
        e.printStackTrace();
        close(sc);
      }
    }
    if (!data.hasRemaining()) {
      sk.interestOps(SelectionKey.OP_READ);
    }
  }

  void close(SocketChannel sc) {
    dout(2, "closing channel");
    try {
      sc.close();
    } catch (IOException ce) {
      fail(ce, "close failed");
    }
  }

  static public void main(String[] args) {
    int port = 5050;
    boolean echo = true;

    for (int i = 0; i < args.length; i++) {
      if (args[i].startsWith("-p")) {
        port = toInt(args[i].substring(2), 5050);
      } else {
        if (args[i].startsWith("-d")) {
          debugLevel = toInt(args[i].substring(2), 2);
        } else {
          if (args[i].startsWith("-e")) {
            echo = !args[i].substring(2).equals("off");
          }
        }
      }
    }
    System.out.println("debug level=" + debugLevel + " Listening on port=" +
                       port + " echo " + (echo ? "on" : "off"));
    new NIOEcho().start(port, echo);
  }

  void fail(Exception e, String s) {
    if (e != null) {
      e.printStackTrace();
    }
    if (s != null) {
      System.out.println(s);
    }
    System.exit(0);
  }

  // get an integer or the default value in er if not convertable
  private static int toInt(String s, int er) {
    int i;

    try {
      i = new Integer(s).intValue();
    } catch (NumberFormatException exc) {
      i = er;
    }
    return i;
  }

  // debug output method, display controlled by debugLevel
  private void dout(int level, String text) {
    if (level >= debugLevel) {
      System.out.println(level + ": " + text);
    }
  }
}
