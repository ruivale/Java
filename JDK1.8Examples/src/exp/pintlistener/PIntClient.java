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
public class PIntClient {
  private static int PORT = 8888;
  private DatagramSocket ds = null;
  private DatagramPacket dp = null;
  private InetAddress server = null;

  /**
   *
   */
  public PIntClient() {
    try{

      server = InetAddress.getByName("localhost");

      String s = "Pode começar a PInt!";

      ds = new DatagramSocket();
      ds.setSoTimeout(10000);
      dp = new DatagramPacket(s.getBytes(), s.getBytes().length, server, PORT);

System.out.println("CLIENT: A enviar ...");

      ds.send(dp);

System.out.println("CLIENT: Enviou!");

    }catch(UnknownHostException uhe){
      uhe.printStackTrace();
    }catch(SocketException se){
      se.printStackTrace();
    }catch(IOException ioe){
      ioe.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  /**
   *
   */
  public static void main(String[] args) {
    PIntClient PIntClient1 = new PIntClient();
  }
}