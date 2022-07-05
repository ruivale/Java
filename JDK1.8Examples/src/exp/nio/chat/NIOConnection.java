package exp.nio.chat;

/**
 * describes a connection between an NIO selection key and a user
 * @author PKWooster
 * @version 1.1 June 18,2004
 */

/*
 * versionhistory
 *  version 1.0 - initial
 *  version 1.1 - support for client
 *   - opening and closing states
 *   - graceful shutdown
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

/**
 * describes a connection between an NIO selection key and a user
 * @author PKWooster
 * @version 1.1 June 18,2004
 */

/*
 * versionhistory
 *  version 1.0 - initial
 *  version 1.1 - support for client
 *   - opening and closing states
 *   - graceful shutdown
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class NIOConnection
    implements Connection {
  private SelectionKey sk;
  private ConnectionUser cu;
  private int state;
  private LinkedList sendQ = new LinkedList();

  private CharsetEncoder encoder;
  private CharsetDecoder decoder;
  private ByteBuffer recvBuffer = null;
  private ByteBuffer sendBuffer = null;
  private StringBuffer recvString = new StringBuffer();
  private String crlf = "\r\n";
  private boolean writeReady = false;
  private String name = "";

  /**
   * construct a NIOConnection from a selection key
   */
  NIOConnection(SelectionKey sk) {
    SocketChannel sch = (SocketChannel) sk.channel();
    if (sch.isConnected()) { // connected immediatedly if local on *nix
      sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
      state = Connection.OPENED;
    } else if (sch.isConnectionPending()) {
      sk.interestOps(SelectionKey.OP_CONNECT);
      state = Connection.OPENING;
    }
    this.sk = sk; // link this to the key
    sk.attach(this);

    Charset charset = Charset.forName("ISO-8859-1");
    decoder = charset.newDecoder();
    encoder = charset.newEncoder();
    recvBuffer = ByteBuffer.allocate(8196);
  }

  /**
   * attach a connection user to this connection
   */
  public void attach(ConnectionUser cu) {
    this.cu = cu;
  }

  /**
   * process a connect complete selection
   */
  public void doConnect() {
    SocketChannel sc = (SocketChannel) sk.channel();
    try {
      sc.finishConnect();
      sk.interestOps(SelectionKey.OP_WRITE); // select OP_WRITE
      Functions.dout(2, "connect complete");
      state = Connection.OPENED;
      if (cu != null) {
        cu.stateChange(state);
      }
    } catch (IOException e) {
      e.printStackTrace();
      closeComplete();
    }
  }

  /**
   * process a read ready selection
   */
  public void doRead() {
    SocketChannel sc = (SocketChannel) sk.channel();
    if (sc.isOpen()) {
      int len;
      recvBuffer.clear();
      try {
        len = sc.read(recvBuffer);
      } catch (IOException e) {
        e.printStackTrace();
        len = -1;
      } // error look like eof
      Functions.dout(1, "read len=" + len);

      if (len > 0) {
        recvBuffer.flip();
        CharBuffer buf = null;
        try {
          buf = decoder.decode(recvBuffer);
        } catch (Exception ce) { // convert bytes to chars
          ce.printStackTrace();
          len = -1;
        }
        toUser(buf);
      }
      if (len < 0) {
        closeComplete();
      }
    } else {
      System.out.println("read closed");
    }
  }

  /*
   * split up received data and forward it to the user
   */
  private void toUser(CharBuffer buf) {
    if (buf != null) {
      int i = 0;
      int j = 0;
      recvString.append(buf); // as a string buffer
      int z = recvString.length();
      while (i < z) {
        char c = recvString.charAt(i);
        if (c == '\r' || c == '\n') {
          i++;
          if (c == '\r' && i < z && '\n' == recvString.charAt(i)) {
            i++;
          }
          cu.receive(recvString.substring(j, i)); // to user
          j = i + 1; // start of next
        } else {
          i++;
        }
      }
      if (j < z) {
        recvString.delete(0, j); // drop front of string buffer
      } else {
        recvString = new StringBuffer();
      }
    }
  }

  /**
   * process a write ready selection
   */
  public void doWrite() {
    Functions.dout(12, "write ready");
    sk.interestOps(SelectionKey.OP_READ); // deselect OP_WRITE
    writeReady = true; // write is ready
    if (sendBuffer != null) {
      write(sendBuffer); // may have a partial write
    }
    writeQueued(); // write out rest of queue
  }

  /**
   * queue up a text string to send and try to send it out
   */
  public void send(String text) {
    sendQ.add(text); // first put it on the queue
    writeQueued(); // write all we can from the queue
  }

  /*
   * attempt to send all queued data
   */
  private void writeQueued() {
    while (writeReady && sendQ.size() > 0) { // now process the queue
      String msg = (String) sendQ.remove(0);
      write(msg); // write the string
    }
  }

  /*
   * convert a text string to a byte buffer and send it
   */
  private void write(String text) {
    try {
      ByteBuffer buf = encoder.encode(CharBuffer.wrap(text));
      write(buf);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * write out a byte buffer
   */
  private void write(ByteBuffer data) {
    SocketChannel sc = (SocketChannel) sk.channel();
    if (sc.isOpen()) {
      int len = 0;

      if (data.hasRemaining()) {
        try {
          len = sc.write(data);
        } catch (IOException e) {
          e.printStackTrace();
          closeComplete();
        }
      }
      if (data.hasRemaining()) { // write would have blocked
        Functions.dout(12, "write blocked");
        writeReady = false;
        sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE); // select OP_WRITE
        sendBuffer = data; // save the partial buffer
      } else {
        sendBuffer = null;
      }
    } else {
      Functions.dout(12, "write closed");
    }
  }

  /*
   * close the connection and its socket
   */
  public void close() {
    if (state != Connection.CLOSED) {
      SocketChannel sc = (SocketChannel) sk.channel();
      if (sc.isOpen()) {
        if (state == Connection.OPENED) { // open attempt graceful shutdown
          Functions.dout(2, "shutting down");
          state = Connection.CLOSING;
          Socket sock = sc.socket();
          try {
            sock.shutdownOutput();
          } catch (IOException se) {
            Functions.dout(12, "shutdown failed");
            se.printStackTrace();
          }
          if (cu != null) {
            cu.stateChange(state);
          }
        } else {
          closeComplete();
        }
      } else {
        Functions.dout(12, "already closed");
      }
    }
  }

  // called internally if already closing or closed by partner
  private void closeComplete() {
    Functions.dout(2, "closing channel");
    try {
      sk.interestOps(0);
      SocketChannel sc = (SocketChannel) sk.channel();
      if (sc != null && sc.isOpen()) {
        sc.close();
      }
      sk.selector().wakeup();
      sk.attach(null);
    } catch (IOException ce) {
      Functions.fail(ce, "close failed");
    }
    state = Connection.CLOSED;
    if (cu != null) {
      cu.stateChange(state);
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String nm) {
    name = nm;
  }

  public int getState() {
    return state;
  }
}
