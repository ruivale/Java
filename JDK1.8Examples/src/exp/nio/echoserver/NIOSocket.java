package exp.nio.echoserver;


/*******************************************************************************************
        NIOSocket.java
        support for a single character based socket
        author PKWooster, Oct 2003 GPL license
 */
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.io.*;
import java.net.*;
import java.nio.charset.*;


//-----------------------------------------------------------------------------
// client NIO socket support
//

public class NIOSocket
    implements NIOControllable, NMReceiver {
  SocketChannel sch;
  Charset charset;
  CharsetEncoder encoder;
  CharsetDecoder decoder;
  ByteBuffer recvBuffer = null;
  ByteBuffer sendBuffer = null;
  StringBuffer recvString = new StringBuffer();
  StringBuffer sendString = new StringBuffer();
  String crlf = "\r\n";
  int recvScanned = 0;
  boolean atEof;
  int bufsz;
  SelectionKey key;
  NIOController controller;

  int interest = 0;
  boolean sendReady;

  NIOTest client; // a reference to our client
  String name = "";

  int state = NIOControllable.CLOSED;

  boolean signedOn = false; // another indication of state
  String address; // our partners IP address
  int port; // our partners port

  //===========================================================================
  // public methods

  // construct a client socket connecting the client to address a,port p
  public NIOSocket(NIOTest c, String a, int p, NIOController nc) {
    client = c;
    address = a;
    port = p;
    name = a + ":" + p;
    controller = nc;
  }

  // start the client socket
  public boolean start() {
    state = NIOControllable.OPENING; // opening
    key = null;
    // connect to address and port
    try {
      sch = SocketChannel.open();
      bufsz = 8192;
      charset = Charset.forName("ISO-8859-1");
      decoder = charset.newDecoder();
      encoder = charset.newEncoder();
      recvBuffer = ByteBuffer.allocate(bufsz);
      atEof = false;
      NMessage om = new NMessage(this, null, NMessage.OPEN);
      controller.invoke(om); // this will enroll us
    } catch (Exception e) {
      return fail(e, "Connection failed to=" + name);
    }
    dout(1, "connecting");
    return true;
  }

  public void requestClose() {
    NMessage om = new NMessage(this, null, NMessage.CLOSE);
    controller.invoke(om); // this will enroll us
  }

  public void send(String txt) {
    NMessage om = new NMessage(this, txt, NMessage.DATA);
    controller.invoke(om); // this will enroll us
  }

  // close this remote user
  public void close() {
    state = NIOControllable.CLOSED;
    try {
      sch.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    key = null;
    client.fromRemote(false); // closed as far as the client is concerned
  }

  // return address and port
  public String getAddress() {
    Socket s = sch.socket();
    return s.getInetAddress() + ":" + s.getPort();
  }

  //======================================================================
  // public method required by the NMReceiver interface
  // this is running in the NIOController's thread
  public void runNMessage(NMessage nm) {
    switch (nm.type) {
    case NMessage.OPEN:
      enroll();
      break;

    case NMessage.CLOSE:
      doRequestClose();
      break;

    case NMessage.DATA:
      doSend((String) nm.data);
      break;
    }
    showInterest();
  }

  //======================================================================
  // public methods required by the NIOControllable interface
  // this runs in the NIOController's thread
  public void processSelection(SelectionKey sk) {
    int kro = sk.readyOps();
    dout(0, "kro=" + kro);
    if ((kro & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT) {
      doConnect();
    }
    if ((kro & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
      doRead();
    }
    if ((kro & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE) {
      doWriteReady();
    }
    showInterest(); // safe to call this directly here
  }

  public int getState() {
    return state;
  }


  //============================================================================
  // private methods


  //--------------------------------------------------------------------
  // utilities

  //  set interestOps
  private void showInterest() {
    if (key != null) {
      int i = key.interestOps();
      if (i != interest) {
        dout(12, "changing interest to " + interest);
        key.interestOps(interest);
      }
    }
  }

  // enroll our channel
  private void enroll() {
    try {
      sch.connect(new InetSocketAddress(address, port));
      if (sch.isConnected()) {
        reportConnect();
      } else {
        if (sch.isConnectionPending()) {
          interest = SelectionKey.OP_CONNECT;
        } else {
          interest = 0;
        }
      }
      key = controller.enroll(this, sch, interest);
    } catch (Exception e) {
      fail(e, "Connection failed to=" + name);
    } // unexpected connect failure
  }

  // report failure to client and close
  private boolean fail(Exception e, String str) {
    if (str != null) {
      System.out.println(str);
      client.fromRemote("!! " + str);
    }
    if (e != null) {
      e.printStackTrace();
    }
    close();
    return false;
  }

  private void dout(int level, String text) {
    NIOTest.dout(level, text);
  }


  //--------------------------------------------------------------------
  // connection support

  private void doConnect() {
    dout(1, "finishing connection");
    try {
      sch.finishConnect();
    } catch (IOException e) {
      fail(null, "Connection failed to=" + name);
    }
    reportConnect();
  }


  private void reportConnect() {
    dout(1, "connected");
    state = NIOControllable.OPENED;
    interest = SelectionKey.OP_READ + SelectionKey.OP_WRITE;
    client.fromRemote("!! Connected to=" + name);
    client.fromRemote(true); // opened
  }

  // send an IAC character to request a close from NIOEcho
  private boolean doRequestClose() {
    if (flush()) { // force out any data
      ByteBuffer b = ByteBuffer.allocate(1);
      b.put((byte) - 1); // send char 255
      b.flip();
      try {
        sch.write(b);
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
      return true;
    } else {
      return false;
    }
  }


  //--------------------------------------------------------------------
  // transmission support

  // send out text string
  private void doSend(String msg) {
    dout(1, "send");
    setWriteInterest(writeLine(msg));
  }


  private void doWriteReady() {
    dout(1, "write ready");
    setWriteInterest(flush());
  }

  // set the read or read+write interest
  private void setWriteInterest(boolean w) {
    if (w) {
      interest = SelectionKey.OP_READ;
    } else {
      interest = SelectionKey.OP_WRITE | SelectionKey.OP_READ;
    }
  }

  private boolean writeLine(String dat) {
    sendString.append(dat);
    sendString.append(crlf);
    return flush();
  }

  private boolean write(String dat) {
    char ch = ' ';
    boolean b;
    int len;

    sendString.append(dat);
    if (!writeBuf()) {
      return false; // blocked
    }
    len = sendString.length();
    if (len > 0) {
      ch = sendString.charAt(len - 1);
    }
    if (ch == '\n' || ch == '\r' || len > bufsz) {
      return flush();
    } else {
      return true;
    }
  }

  // flush out any pending output, returns true if all data sent
  public boolean flush() {
    if (!writeBuf()) {
      return false; // blocked
    }
    if (sendString.length() > 0) {
      stringToBuf();
      return writeBuf();
    }
    return true;
  }

  private void stringToBuf() {
    CharBuffer cb = CharBuffer.wrap(sendString);
    try {
      sendBuffer = encoder.encode(cb);
    } catch (Exception e) {
      e.printStackTrace();
    }
    sendString = new StringBuffer(bufsz);
  }

  private boolean writeBuf() {
    if (sendBuffer != null) { // buffer is already empty
      int n = 0;
      try {
        n = sch.write(sendBuffer);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (n < sendBuffer.limit()) {
        dout(12, "short write n=" + n);
        sendBuffer.position(n);
        return false;
      } else {
        sendBuffer = null;
      }
    }
    return true;
  }

  //--------------------------------------------------------------------
  // reception support

  // receive a string
  private void doRead() {
    String dat;

    while (null != (dat = readLine())) {
      client.fromRemote(dat); // send it back to the client
    }
    if (atEof) {
      close();
    }
  }


  private String readLine() {
    String s = null;
    fill();
    int len = recvString.length();
    int n = firstNL();
    if (n == -1) {
      if (atEof && 0 < len) {
        s = recvString.substring(0);
        n = len;
      }
    } else {
      s = recvString.substring(0, n);
      if (len > n + 1 && recvString.charAt(n) == '\r' &&
          recvString.charAt(n + 1) == '\n') {
        n += 2;
      } else {
        n++;
      }
    }

    if (n > 0) {
      recvString.delete(0, n);
      recvScanned = 0;
    }
    return s;
  }

  public boolean fill() {
    CharBuffer buf;
    int len = 0;

    recvBuffer.clear();
    try {
      len = sch.read(recvBuffer);
    } catch (Exception e) {
      e.printStackTrace();
      atEof = true;
    }
    recvBuffer.flip();

    if (len > 0) {
      dout(1, "read " + len + " bytes");
      try {
        buf = decoder.decode(recvBuffer);
        dout(0, "received=" + buf);
        recvString.append(buf);
      } catch (Exception e) {
        e.printStackTrace();
        atEof = true;
      }
    } else {
      if (len < 0) {
        atEof = true;
      }
    }
    return atEof;
  }

  // scan recvString for first new line
  private int firstNL() {
    while (recvScanned < recvString.length()) {
      char ch = recvString.charAt(recvScanned);
      if (ch == '\r' || ch == '\n') {
        return recvScanned;
      }
      recvScanned++;
    }
    return -1; // no cr or lf
  }
}

