package exp.pintlistener;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.net.*;
import java.io.*;

/**
 *
 *
 */
public class PIntListener implements Runnable {
  private final static String STOP = "stop";
  private final static String START = "start";
  private static int PORT = 8888;
  private static byte[] buffer = new byte[128];
  private DatagramSocket ds = null;
  private DatagramPacket dp = null;
  private String msgReceived = null;
  private static boolean PINT_ALREADY_OPEN = false;
  private static boolean CONTINUE_EXECUTION = true;
//  private static com.ent.PInt.PInt pint = null;

  /**
   *
   */
  public PIntListener(String[] args) {

    try {

      // Closing everything
      if (args[0].equals(STOP)) {
        sendExitRequest();

        // Proceding
      }
      else if (args[0].equals(START)) {

        ds = new DatagramSocket(PORT);
        dp = new DatagramPacket(buffer, buffer.length);

        new Thread(this, "wait").start();
      }
    } catch (SocketException se) {
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

System.out.println("SERVER: A escuta ...");

          ds.receive(dp);

          msgReceived = new String(dp.getData(), 0, dp.getLength());

System.out.println("SERVER: " + dp.getAddress() + " at port " + dp.getPort() + " says " + msgReceived);

          if (msgReceived.equals(STOP)) {
            this.CONTINUE_EXECUTION = false;

System.out.println("SERVER: Stoping the listener!");

          } else if (!this.PINT_ALREADY_OPEN) {

System.out.println("SERVER: Opening the PInt!");

            //pint = new com.ent.PInt.PInt();

            this.PINT_ALREADY_OPEN = true;

            new Thread(this, "testpint").start();

          }


        } catch (Exception e) {
          e.printStackTrace();
          new Thread(this, "wait").start();
        }

      }




    } else if (Thread.currentThread().getName().equals("testpint")) {

//      while (pint.isVisible()) {
//        try {
//          Thread.sleep(1000);
//        } catch (Exception ex) {
//          ex.printStackTrace();
//          this.PINT_ALREADY_OPEN = false;
//        }
//      }
//      this.pint = null;
      this.PINT_ALREADY_OPEN = false;

System.out.println("SERVER: PInt closed!");

    }
  }


  /**
   *
   */
  private void sendExitRequest() {

    try {

      InetAddress server = InetAddress.getByName("localhost");
      DatagramSocket _ds = new DatagramSocket();
      _ds.setSoTimeout(0);
      DatagramPacket _dp = new DatagramPacket(STOP.getBytes(), STOP.getBytes().length, server, PORT);

      System.out.println("SERVER: A tentar parar!");

      _ds.send(_dp);

      System.out.println("SERVER: Acho que parou!");

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   *
   */
  public static void main(String[] args) {
    PIntListener PIntListener1 = new PIntListener(args);
  }
}
