package exp.servers;


import java.net.*;
import java.io.*;
import javax.swing.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MySyslogDeamon implements Runnable{

  public final static String FOCUS = "focus";
  public final static String STOP = "stop";
  public final static String START = "start";
  private static int PORT = 514;
  private DatagramSocket ds = null;
  private DatagramPacket dp = null;
  private String msgReceived = null;
  private static boolean CONTINUE_EXECUTION = true;
  private static byte[] buffer = new byte[1024];



  /**
   * Used to start the PInt
   */
  public MySyslogDeamon() {
    try {
      this.ds = new DatagramSocket(this.PORT);
      this.dp = new DatagramPacket(buffer, buffer.length);
      new Thread(this, "wait").start();

    } catch (Exception se) {
        se.printStackTrace();
    }
  }

  /**
   *
   */
  public void run() {
    if (Thread.currentThread().getName().equals("wait")) {
      while (CONTINUE_EXECUTION) {
        try {

          System.out.println("Waiting:");
          ds.receive(dp);
          msgReceived = new String(dp.getData(), 0, dp.getLength());

System.out.println("MSG="+msgReceived+".");

        } catch (Exception e) {
          e.printStackTrace();
          new Thread(this, "wait").start();
        }
      }
    }
  }


  /**
   *
   */
  public static void main(String[] args) {
      MySyslogDeamon PIntListener1 = new MySyslogDeamon();
  }
}
