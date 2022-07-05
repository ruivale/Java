package exp.nio.chat;

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

import java.net.*;
import java.io.*;

/**
 * describes a connection between a stream socket and a user
 * @author PKWooster
 * @version 1.0 June 15,2004
 */
public class StreamConnection
    implements Connection {
  private Socket sock;
  private BufferedReader in;
  private BufferedWriter out;
  private int state = Connection.CLOSED;
  private Thread recvThread;
  private Thread sendThread;
  private java.util.LinkedList sendQ = new java.util.LinkedList();
  private ConnectionUser cu;
  private String name;

  StreamConnection(Socket sock) {
    state = Connection.OPENING;
    this.sock = sock;
  }

  /**
   * links this connection to a user
   */
  public void attach(ConnectionUser cu) {
    this.cu = cu;
    name = "" + sock.getInetAddress() + ":" + sock.getPort();
    Functions.dout(12, "connection from " + name);
    try {
      in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

      recvThread = new Thread(new Runnable() {
        public void run() {
          doRecv();
        }
      }, "Recv." + name);
      sendThread = new Thread(new Runnable() {
        public void run() {
          doSend();
        }
      }, "Send." + name);
      sendThread.start();
      recvThread.start();
      state = Connection.OPENED;
      if (cu != null) {
        cu.stateChange(state);
      }
    } catch (IOException e) {
      e.printStackTrace();
      close();
      name = "";
    }

  }

  /**
   * send a character string out on the socket
   */
  public synchronized void send(String msg) {
    sendQ.add(msg);
    this.notify();
  }

  /**
   * close the connection and the socket
   */
  public void close() {
    if (state == Connection.OPENED) {
      state = Connection.CLOSING;
      try {
        sock.shutdownOutput();
      } catch (IOException ie) {
        ie.printStackTrace();
      }
      if (cu != null) {
        cu.stateChange(state);
      }
    } else if (state != Connection.CLOSED) {
      try {
        sock.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (sendThread.isAlive()) {
        sendThread.interrupt();
      }
      state = Connection.CLOSED;
    }
    if (cu != null) {
      cu.stateChange(state);
    }
  }

  /**
   * get the connection state
   */
  public int getState() {
    return state;
  }

  /**
   * get the connection name
   */
  public String getName() {
    return name;
  }

  /**
   * set the connection name
   */
  public void setName(String name) {
    this.name = name;
    try {
      recvThread.setName("recv." + name);
      sendThread.setName("send." + name);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * the main loop for the send thread
   */
  private void doSend() {
    String msg;
    try {
      while (null != (msg = popMessage())) {
        out.write(msg);
        out.flush();
      }
    } catch (InterruptedException ie) {} catch (IOException e) { // ignore interrupts
      e.printStackTrace();
    }
    Functions.dout(2, "Thread=" + Thread.currentThread().getName() + " ending");
  }

  /**
   * the main loop for the receive thread
   */
  private void doRecv() {
    String inbuf;

    while (0 != state) {
      try {
        inbuf = in.readLine();
      } catch (Exception e) {
        System.out.println(e);
        inbuf = null;
      }
      if (inbuf == null) {
        close();
      } else {
        System.out.println("received (" + inbuf + ") from user=" + name);
        cu.receive(inbuf + "\r\n");
      }
    }
    Functions.dout(2, "Thread=" + Thread.currentThread().getName() + " ending");
  }

  /**
   * pop a message off the send queue
   */
  private synchronized String popMessage() throws InterruptedException {
    String msg = null;
    while (state > Connection.CLOSED && sendQ.size() == 0) {
      this.wait();
    }
    if (state > Connection.CLOSED) {
      msg = (String) sendQ.remove(0);
    }
    return msg;
  }
}
